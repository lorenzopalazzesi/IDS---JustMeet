package com.unicam.ids.justmeet.IDSJustMeet.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.unicam.ids.justmeet.IDSJustMeet.model.Event;

@Service
public interface EventService {

	/**
	 * 
	 * @param id
	 */
	public void deleteEvent(int id);
	/**
	 * 
	 * @param event
	 */
	public void saveEvent(Event event);
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public boolean isEventTitleAlreadyPresent(Event event);
	
	/**
	 * 
	 * @return
	 */
	public List<Event> findAll();
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	public List<Event> findByTitle(String title);
	
	/**
	 * 
	 * @param idcreator
	 * @return
	 */
	public List<Event> findByCreator(int idcreator);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Event findById(int id);
	
	/**
	 * Metodo che permette di eliminare un evento da un utente che ha creato lo stesso
	 * @param id l'id dell'evento da eliminare
	 */
	public void deleteMyEvent(int id);
	
	/**
	 * 
	 * @param id
	 */
	public void closeMyEvent(int id);
	
	/**
	 * 
	 * @param id
	 */
	public void openMyEvent(int id);
	
	
}
