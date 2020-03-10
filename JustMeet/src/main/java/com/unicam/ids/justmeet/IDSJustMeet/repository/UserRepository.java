package com.unicam.ids.justmeet.IDSJustMeet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.unicam.ids.justmeet.IDSJustMeet.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByIdLike(int id);

	public User findByEmailLike(String email);


	public List<User> findByNameLike(String name);
	
	@Query("update User u set u.name = :name WHERE u.id = :id")
    void setUserName(@Param("id") int id, @Param("name") String name);
}
