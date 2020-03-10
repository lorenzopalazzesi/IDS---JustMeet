package com.unicam.ids.justmeet.IDSJustMeet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.unicam.ids.justmeet.IDSJustMeet.model.FeedBack;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.repository.FeedBackRepository;
import com.unicam.ids.justmeet.IDSJustMeet.repository.UserRepository;

@Service
public class FeedBackServiceImp implements FeedBackService {

	@Autowired
	FeedBackRepository feedBackRepository;
	
	@Autowired
	UserRepository userRepository;
	
	

	@Override
	public void addFeedBack(FeedBack feedback, User user) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    User currentuser = userRepository.findByEmailLike(email);
	    int id = currentuser.getId();
		feedback.setIdreviewer(id);
		feedback.setIdreviewed(user.getId());
		feedback.setEmailrewiever(email);
		feedBackRepository.save(feedback);	
	}



	@Override
	public List<FeedBack> findByUser(int id_reviewed) {
		return feedBackRepository.findByUserId(id_reviewed);
	}

	
}
