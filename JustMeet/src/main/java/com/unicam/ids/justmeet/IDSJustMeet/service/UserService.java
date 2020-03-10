package com.unicam.ids.justmeet.IDSJustMeet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unicam.ids.justmeet.IDSJustMeet.model.User;

@Service
public interface UserService  {

	/**
	 * 
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * 
	 * @param id
	 */
	public void deleteUser(int id);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public boolean isUserAlreadyPresent(User user);

	/**
	 * 
	 * @return
	 */
	public List<User> findAll();

	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<User> findByName(String name);

	/**
	 * 
	 * @param email
	 * @return
	 */
	public User findByEmail(String email);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public User findById(int id);

}
