package com.concretepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IUserProfileDAO;
import com.concretepage.entity.Category;
import com.concretepage.entity.Country;
import com.concretepage.entity.UserProfile;
import com.concretepage.entity.UserProfileEntity;

@Service
public class UserProfileService implements IUserProfileService {
	@Autowired
	private IUserProfileDAO userProfileDAO;

	public List<UserProfile> getAllUserProfiles() {		
		return userProfileDAO.getAllUserProfiles();
	}

	public UserProfile getUserProfileById(int id) {		
		return userProfileDAO.getUserProfileById(id);
	}

	public UserProfile updateUserProfile(UserProfile userProfile) {		
		return userProfileDAO.updateUserProfile(userProfile);
	}

	public void deleteUserProfile(int id) {	
		userProfileDAO.deleteUserProfile(id);
	}

	public void addUserProfile(UserProfile userProfile) {
		userProfileDAO.addUserProfile(userProfile);		
	}

	@Override
	public List<UserProfile> getUserProfileByEmail(String emailAddress) {
		return userProfileDAO.getUserProfileByEmail(emailAddress);
	}

	@Override
	public UserProfile loginUserProfile(UserProfile userProfile) {		
		return userProfileDAO.loginUserProfile(userProfile);
	}

	@Override
	public List<Country> getCountries() {		
		return userProfileDAO.getCountries();
	}

	@Override
	public List<UserProfileEntity> getUserProfileEntities() {		
		return userProfileDAO.getUserProfileEntities();
	}

	@Override
	public List<Category> getCategories() {		
		return userProfileDAO.getCategories();
	}
	
	
}
