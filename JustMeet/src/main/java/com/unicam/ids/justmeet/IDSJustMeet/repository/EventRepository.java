package com.unicam.ids.justmeet.IDSJustMeet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unicam.ids.justmeet.IDSJustMeet.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

	@Query("SELECT e from Event e WHERE e.status ='APERTO'")
	public List<Event> findAllAvaiableEvents();
	
	@Query("SELECT e from Event e WHERE e.status ='APERTO' AND e.title=title AND e.maxpartecipants!='0'")
	public List<Event> findByTitleLike(String title);

	public Event findByTitle(String title);

	public Event findEventById(int id);
	
	@Query("SELECT e from Event e WHERE e.creator =?1 ")
	public List<Event> findCreator(int creator);

	
}
