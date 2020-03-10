package com.unicam.ids.justmeet.IDSJustMeet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unicam.ids.justmeet.IDSJustMeet.model.UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Integer>{

	public UserGroup findByName(String name);
	
	public List<UserGroup> findByNameLike(String name);

	@Query("SELECT u from UserGroup u WHERE u.id_creator =?1 ")
	public List<UserGroup> findByIdCreator(int id_creator);

	public UserGroup findByIdLike(int id);

}
