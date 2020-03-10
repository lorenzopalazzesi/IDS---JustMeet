package com.unicam.ids.justmeet.IDSJustMeet.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unicam.ids.justmeet.IDSJustMeet.model.Prenotation;

@Repository
public interface PrenotationRepository extends JpaRepository<Prenotation, Integer> {

	@Query("SELECT p from Prenotation p  WHERE p.iduser =?1 ")
	public List<Prenotation> findByUserId(int iduser);
	
	public Prenotation findPrenotationById(int id);
}
