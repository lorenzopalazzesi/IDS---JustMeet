package com.unicam.ids.justmeet.IDSJustMeet.service;




import java.util.List;

import org.springframework.stereotype.Service;

import com.unicam.ids.justmeet.IDSJustMeet.model.FeedBack;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;

@Service
public interface FeedBackService {

	/**
	 * 
	 * @param feedback
	 * @param user
	 */
	public void addFeedBack(FeedBack feedback, User user);
	
	/**
	 * 
	 * @param user_id
	 * @return
	 */
	public List<FeedBack> findByUser(int user_id);
}
