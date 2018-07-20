package com.concretepage.dao;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.concretepage.entity.Category;
import com.concretepage.entity.Country;
import com.concretepage.entity.UserProfile;
import com.concretepage.entity.UserProfileEntity;
@Transactional
@Repository
public class UserProfileDAO implements IUserProfileDAO{
	@PersistenceContext	
	private EntityManager entityManager;			
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> getAllUserProfiles() {		
		String hql = "FROM UserProfile";
		return (List<UserProfile>) entityManager.createQuery(hql).getResultList();
	}
	public UserProfile getUserProfileById(int id) {
		return entityManager.find(UserProfile.class, id);		
	}
	public UserProfile updateUserProfile(UserProfile userProfile) {		
		UserProfile existingUserProfile = getUserProfileByEmail(userProfile.getEmailId()).get(0);
		existingUserProfile.setPhoneNo(userProfile.getPhoneNo());
		existingUserProfile.setAddress1(userProfile.getAddress1());		
		existingUserProfile.setAddress2(userProfile.getAddress2());
		entityManager.flush();
		return existingUserProfile;
	}
	public void deleteUserProfile(int id) {
		entityManager.remove(getUserProfileById(id));		
	}
	public void addUserProfile(UserProfile userProfile) {
		userProfile.setRegistrationDate(new Date());	
		String logonPassword = userProfile.getLogonPassword();
		byte[] encodeBase64 = Base64.encodeBase64(logonPassword.getBytes());
		userProfile.setLogonPassword(new String(encodeBase64));		
		entityManager.persist(userProfile);		
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> getUserProfileByEmail(String emailAddress){
		return entityManager.createQuery("FROM UserProfile where emailId = :emailAddress")
                .setParameter("emailAddress", emailAddress).getResultList();
	}
	@Override
	public UserProfile loginUserProfile(UserProfile userProfile) {	
	List<UserProfile> userProfiles = getUserProfileByEmail(userProfile.getEmailId());
	if(userProfiles.size()>0){
		UserProfile userProfileFromDb = getUserProfileByEmail(userProfile.getEmailId()).get(0);			
		if(userProfileFromDb!=null && userProfileFromDb.getLogonPassword()!=null && 
		   userProfile.getLogonPassword()!=null &&
		   userProfile.getLogonPassword().equals(new String(Base64.decodeBase64(userProfileFromDb.getLogonPassword())))){
		   userProfileFromDb.setIsAuthenticated(true);
		   return userProfileFromDb;
		}
		else		
			return null;
		}else{
			return null;
		}		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Country> getCountries() {
		String hql = "FROM Country";
		return (List<Country>) entityManager.createQuery(hql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserProfileEntity> getUserProfileEntities() {
		String hql = "FROM UserProfileEntity";
		return (List<UserProfileEntity>) entityManager.createQuery(hql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() {
		String hql = "FROM Category";
		return (List<Category>) entityManager.createQuery(hql).getResultList();
	}
}
