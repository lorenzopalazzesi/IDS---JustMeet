package com.unicam.ids.justmeet.IDSJustMeet.service;


import java.util.List;

import org.springframework.stereotype.Service;
import com.unicam.ids.justmeet.IDSJustMeet.model.UserGroup;

@Service
public interface UserGroupService {

	/**
	 * 
	 * @param userGroup
	 */
	public void saveGroup(UserGroup userGroup);

	/**
	 * 
	 * @param userGroup
	 * @return
	 */
	public boolean isGroupNameAlreadyPresent(UserGroup userGroup);
	
	/**
	 * 
	 * @return
	 */
	public List<UserGroup> findAll();
	
	/**
	 * 
	 * @param idCreator
	 * @return
	 */
	public List<UserGroup> findByCreator(int idCreator);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<UserGroup> findByName(String name);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public UserGroup findById(int id);
	
	/**
	 * 
	 * @param id
	 */
	public void deleteGroup(int id);
}
