package com.concretepage.dao;
import java.util.List;

import com.concretepage.entity.Category;
import com.concretepage.entity.Country;
import com.concretepage.entity.UserProfile;
import com.concretepage.entity.UserProfileEntity;
public interface IUserProfileDAO {
	void addUserProfile(UserProfile userProfile);
	List<UserProfile> getAllUserProfiles();
    UserProfile getUserProfileById(int id);     
    UserProfile updateUserProfile(UserProfile userProfile);
    void deleteUserProfile(int id);
    List<UserProfile> getUserProfileByEmail(String emailAddress);
    UserProfile loginUserProfile(UserProfile userProfile);    
    // Lookups
    List<Country> getCountries();
    List<UserProfileEntity> getUserProfileEntities();
    List<Category> getCategories();
}
 