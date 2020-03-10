package com.unicam.ids.justmeet.IDSJustMeet.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_group" )
public class UserGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_group_id")
	private int id;
	
	@NotNull(message = "Id del creatore del gruppo")
	@Column(name = "user_group_id_creator")
	private int id_creator;
	
	@NotNull(message = "Inserisci il nome del Gruppo!")
	@Column(name = "user_group_name")
	private String name ;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_creator() {
		return id_creator;
	}

	public void setId_creator(int id_creator) {
		this.id_creator = id_creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
