package com.unicam.ids.justmeet.IDSJustMeet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.unicam.ids.justmeet.IDSJustMeet.model.Event;
import com.unicam.ids.justmeet.IDSJustMeet.model.Prenotation;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.repository.PrenotationRepository;
import com.unicam.ids.justmeet.IDSJustMeet.repository.UserRepository;

@Service
public class PrenotationServiceImp implements PrenotationService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PrenotationRepository prenotationRepository;

	@Override
	public void addPrenotation(Prenotation prenotation, Event event) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    User currentuser = userRepository.findByEmailLike(email);
	    int id = currentuser.getId();
		prenotation.setIduser(id);
		prenotation.setIdevent(event.getId());
		prenotation.setTitleEvent(event.getTitle());
		prenotation.setDescriptionEvent(event.getDescription());
		event.setMaxpartecipants(event.getMaxpartecipants()-1);
	    prenotationRepository.save(prenotation);	
	}

	@Override
	public List<Prenotation> findByUser(int prenotation_id_user) {
		return prenotationRepository.findByUserId(prenotation_id_user);
	}

	@Override
	public void deleteMyPrenotation(int id) {
		prenotationRepository.deleteById(id);
	}

	@Override
	public Prenotation findById(int id) {
		return prenotationRepository.findPrenotationById(id);
	}
	
	

}
