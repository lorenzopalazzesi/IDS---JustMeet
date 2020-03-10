package com.unicam.ids.justmeet.IDSJustMeet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unicam.ids.justmeet.IDSJustMeet.model.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

	@Query("SELECT s from Subscribe s WHERE s.idsignedup=?1")
	public List<Subscribe> findByUserSignedUpId(int idsignedup);
	
	@Query("SELECT s from Subscribe s WHERE s.idgroup=?1")
	public List<Subscribe> findByIdGroup(int idgroup);
	
	public Subscribe findSubscribeById(int id);
}
