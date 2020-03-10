package com.unicam.ids.justmeet.IDSJustMeet.service;

import java.util.Arrays;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.unicam.ids.justmeet.IDSJustMeet.model.Role;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.repository.RoleRepository;
import com.unicam.ids.justmeet.IDSJustMeet.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {


	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

	
	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFICATO");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		boolean isUserAlreadyExists = false;
		User existingUser = userRepository.findByEmailLike(user.getEmail());
		// If user is found in database, then then user already exists.
		if(existingUser != null){
			isUserAlreadyExists = true; 
		}
		return isUserAlreadyExists;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findByName(String name) {
		return userRepository.findByNameLike("%"+name+"%");
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmailLike(email);
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public User findById(int id) {
		return userRepository.findByIdLike(id);
	}


}
