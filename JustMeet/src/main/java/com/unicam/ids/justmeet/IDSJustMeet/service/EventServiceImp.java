package com.unicam.ids.justmeet.IDSJustMeet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.unicam.ids.justmeet.IDSJustMeet.model.Event;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;
import com.unicam.ids.justmeet.IDSJustMeet.repository.EventRepository;
import com.unicam.ids.justmeet.IDSJustMeet.repository.UserRepository;

@Service
public class EventServiceImp implements EventService {

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository; 
	
	@Override
	public void saveEvent(Event event) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    User user = userRepository.findByEmailLike(email);
	    int id = user.getId();
	    event.setCreator(id);
		event.setStatus("APERTO");
		eventRepository.save(event);
	}

	
	@Override
	public boolean isEventTitleAlreadyPresent(Event event) {
		boolean isEventTitleAlreadyExists = false;
		Event existingEvent = eventRepository.findByTitle(event.getTitle());
		// If event is found in database, then then event title already exists.
		if(existingEvent != null){
			isEventTitleAlreadyExists = true; 
		}
		return isEventTitleAlreadyExists;
	}

	@Override
	public List<Event> findAll() {
		return eventRepository.findAllAvaiableEvents();
	}

	@Override
	public void deleteEvent(int id) {
		eventRepository.deleteById(id);
		
	}
	
	@Override
	public void deleteMyEvent(int id) {
		eventRepository.deleteById(id);
		
	}

	@Override
	public List<Event> findByTitle(String title) {
		return eventRepository.findByTitleLike("%"+title+"%");
	}

	@Override
	public List<Event> findByCreator(int creator) {
		return eventRepository.findCreator(creator);
	}

	@Override
	public void closeMyEvent(int id) {
		Event event = eventRepository.findEventById(id);
		event.setStatus("CHIUSO");
		eventRepository.save(event);
	}

	@Override
	public void openMyEvent(int id) {
		Event event = eventRepository.findEventById(id);
		event.setStatus("APERTO");
		eventRepository.save(event);
		
	}


	@Override
	public Event findById(int id) {
		return eventRepository.findEventById(id);
	}
	

}
