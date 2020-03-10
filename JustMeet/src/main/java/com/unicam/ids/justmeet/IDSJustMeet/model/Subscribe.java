package com.unicam.ids.justmeet.IDSJustMeet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="group_registration")
public class Subscribe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_registration_id")
	private int id ;
	
	@Column(name = "group_id")
	private int idgroup;
	
	@Column(name = "group_name")
	private String namegroup;
	
	@Column(name = "user_id")
	private int idsignedup;
	
	@Column(name = "user_email")
	private String emailuserSignedup;
	
	@Column(name = "user_name")
	private String nameuserSignedup;
	
	@Column(name = "user_surname")
	private String surnameuserSignedup;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdgroup() {
		return idgroup;
	}

	public void setIdgroup(int idgroup) {
		this.idgroup = idgroup;
	}

	public int getIdsignedup() {
		return idsignedup;
	}

	public void setIdsignedup(int idsignedup) {
		this.idsignedup = idsignedup;
	}

	public String getNamegroup() {
		return namegroup;
	}

	public void setNamegroup(String namegroup) {
		this.namegroup = namegroup;
	}

	public String getEmailuserSignedup() {
		return emailuserSignedup;
	}

	public void setEmailuserSignedup(String emailuserSignedup) {
		this.emailuserSignedup = emailuserSignedup;
	}

	public String getNameuserSignedup() {
		return nameuserSignedup;
	}

	public void setNameuserSignedup(String nameuserSignedup) {
		this.nameuserSignedup = nameuserSignedup;
	}

	public String getSurnameuserSignedup() {
		return surnameuserSignedup;
	}

	public void setSurnameuserSignedup(String surnameuserSignedup) {
		this.surnameuserSignedup = surnameuserSignedup;
	}
	
	
	
	
}
