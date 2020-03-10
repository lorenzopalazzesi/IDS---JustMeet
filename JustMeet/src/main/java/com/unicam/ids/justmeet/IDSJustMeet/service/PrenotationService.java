package com.unicam.ids.justmeet.IDSJustMeet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.unicam.ids.justmeet.IDSJustMeet.model.Event;
import com.unicam.ids.justmeet.IDSJustMeet.model.Prenotation;

@Service
public interface PrenotationService {

	/**
	 * 
	 * @param prenotation
	 * @param event
	 */
	public void addPrenotation(Prenotation prenotation , Event event);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Prenotation findById(int id);
	/**
	 * 
	 * @param prenotation_id_user
	 * @return
	 */
	public List<Prenotation> findByUser(int prenotation_id_user);
	
	/**
	 * 
	 * @param id
	 */
	public void deleteMyPrenotation(int id);
}
