package com.unicam.ids.justmeet.IDSJustMeet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.unicam.ids.justmeet.IDSJustMeet.model.Subscribe;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.model.UserGroup;
import com.unicam.ids.justmeet.IDSJustMeet.repository.SubscribeRepository;
import com.unicam.ids.justmeet.IDSJustMeet.repository.UserRepository;

@Service
public class SubscribeServiceImp implements SubscribeService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SubscribeRepository subscriveRepository;
	
	@Override
	public void addSubscribe(Subscribe subscrive, UserGroup userGroup) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    User currentuser = userRepository.findByEmailLike(email);
	    int id = currentuser.getId();
	    subscrive.setIdsignedup(id);
	    subscrive.setIdgroup(userGroup.getId());
	    subscrive.setNamegroup(userGroup.getName());
	    subscrive.setEmailuserSignedup(userRepository.findByIdLike(id).getEmail());
	    subscrive.setNameuserSignedup(userRepository.findByIdLike(id).getName());
	    subscrive.setSurnameuserSignedup(userRepository.findByIdLike(id).getLastName());
	    subscriveRepository.save(subscrive);
	}

	@Override
	public List<Subscribe> findByIdSignedUp(int idsignedup) {
		return subscriveRepository.findByUserSignedUpId(idsignedup);
	}

	@Override
	public Subscribe findById(int id) {
		return subscriveRepository.findSubscribeById(id);
	}

	@Override
	public void delteMySubscribe(int id) {
		subscriveRepository.deleteById(id);
		
	}

	@Override
	public List<Subscribe> findByIdGroup(int id) {
		return subscriveRepository.findByIdGroup(id);
	}

}
