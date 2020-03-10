package com.unicam.ids.justmeet.IDSJustMeet.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unicam.ids.justmeet.IDSJustMeet.model.FeedBack;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {

	@Query("SELECT u from FeedBack u WHERE u.idreviewed =?1 ")
	public List<FeedBack> findByUserId(int idreviewed);

	
}
