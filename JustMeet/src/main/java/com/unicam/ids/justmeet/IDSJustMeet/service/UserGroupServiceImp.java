package com.unicam.ids.justmeet.IDSJustMeet.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.model.UserGroup;
import com.unicam.ids.justmeet.IDSJustMeet.repository.UserGroupRepository;
import com.unicam.ids.justmeet.IDSJustMeet.repository.UserRepository;

@Service
public class UserGroupServiceImp implements UserGroupService {

	@Autowired
	UserGroupRepository userGroupRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void saveGroup(UserGroup userGroup) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    User user = userRepository.findByEmailLike(email);
	    int id = user.getId();
	    userGroup.setId_creator(id);
	    userGroupRepository.save(userGroup);
	}

	@Override
	public boolean isGroupNameAlreadyPresent(UserGroup userGroup) {
		boolean isGroupNameAlreadyPresent = false;
		UserGroup existingUserGroupName = userGroupRepository.findByName(userGroup.getName());
		// If userGroup name is found in database, then then user already exists.
		if(existingUserGroupName != null){
			isGroupNameAlreadyPresent = true; 
		}
		return isGroupNameAlreadyPresent;
	}

	@Override
	public List<UserGroup> findByCreator(int idCreator) {
		return userGroupRepository.findByIdCreator(idCreator);
	}

	@Override
	public List<UserGroup> findByName(String name) {
		return userGroupRepository.findByNameLike("%"+name+"%");
	}

	@Override
	public UserGroup findById(int id) {
		return userGroupRepository.findByIdLike(id);
	}

	@Override
	public List<UserGroup> findAll() {
		return userGroupRepository.findAll();
	}

	@Override
	public void deleteGroup(int id) {
		userGroupRepository.deleteById(id);
		
	}	

}
