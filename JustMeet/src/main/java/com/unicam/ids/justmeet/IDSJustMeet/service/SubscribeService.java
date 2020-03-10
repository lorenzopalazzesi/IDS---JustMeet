package com.unicam.ids.justmeet.IDSJustMeet.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.unicam.ids.justmeet.IDSJustMeet.model.Subscribe;
import com.unicam.ids.justmeet.IDSJustMeet.model.UserGroup;

@Service
public interface SubscribeService {

	/**
	 * 
	 * @param subscrive
	 * @param userGroup
	 */
	public void addSubscribe(Subscribe subscrive , UserGroup userGroup);
	
	/**
	 * 
	 * @param idsignedup
	 * @return
	 */
	public List<Subscribe> findByIdSignedUp(int idsignedup);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Subscribe findById(int id);
	
	/**
	 * 
	 * @param id
	 */
	public void delteMySubscribe(int id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Subscribe> findByIdGroup(int id);
}
