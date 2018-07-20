package com.concretepage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import com.concretepage.entity.Category;
import com.concretepage.entity.Country;
import com.concretepage.entity.UserProfile;
import com.concretepage.entity.UserProfileEntity;
import com.concretepage.service.IUserProfileService;

@Controller
@RequestMapping("profile")
public class UserProfileController {

	@Autowired
	private IUserProfileService userProfileService;

	@GetMapping("getUserprofile/{id}")
	public ResponseEntity<UserProfile> getUserprofileById(
			@PathVariable("id") Integer id) {
		UserProfile userProfile = userProfileService.getUserProfileById(id);
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
	}

	@GetMapping("userprofiles")
	public ResponseEntity<List<UserProfile>> userprofiles() {
		List<UserProfile> list = userProfileService.getAllUserProfiles();
		return new ResponseEntity<List<UserProfile>>(list, HttpStatus.OK);
	}
	
	@GetMapping("counteries")
	public ResponseEntity<List<Country>> counteries() {
		List<Country> list = userProfileService.getCountries();
		return new ResponseEntity<List<Country>>(list, HttpStatus.OK);
	}
	
	@GetMapping("entities")
	public ResponseEntity<List<UserProfileEntity>> entities() {
		List<UserProfileEntity> list = userProfileService.getUserProfileEntities();
		return new ResponseEntity<List<UserProfileEntity>>(list, HttpStatus.OK);
	}
	
	@GetMapping("categories")
	public ResponseEntity<List<Category>> categories() {
		List<Category> list = userProfileService.getCategories();
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}

	@PostMapping("addUserProfile")
	public ResponseEntity<UserProfile> addUserProfile(@RequestBody UserProfile userProfile, UriComponentsBuilder builder) {		
		HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;
		if (userProfile.getEmailId() != null && userProfile.getEmailId().length() > 0 && userProfile.getLogonPassword() != null && userProfile.getLogonPassword().length() > 0) {
			
			List<UserProfile> userProfileByEmail = userProfileService.getUserProfileByEmail(userProfile.getEmailId());
			if (userProfileByEmail != null && userProfileByEmail.size() > 0) {
				// User Exists
				userProfile=null;
				httpStatus = HttpStatus.EXPECTATION_FAILED;				
			} else {
				// Create User
				userProfileService.addUserProfile(userProfile);				
				httpStatus = HttpStatus.CREATED;				
			}
		} else {
			// Invalid Request if email not found in request
			userProfile=null;
			httpStatus = HttpStatus.BAD_REQUEST;			
		}
		return new ResponseEntity<UserProfile>(userProfile, httpStatus);
	}
	
	@PostMapping("loginUserProfile")
	public ResponseEntity<UserProfile> loginUserProfile(@RequestBody UserProfile userProfile) {				
		return new ResponseEntity<UserProfile>(userProfileService.loginUserProfile(userProfile), HttpStatus.OK);
	}
	
	@PostMapping("updateUserProfile")
	public ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfile userProfile) {				
		return new ResponseEntity<UserProfile>(userProfileService.updateUserProfile(userProfile), HttpStatus.OK);
	}
}