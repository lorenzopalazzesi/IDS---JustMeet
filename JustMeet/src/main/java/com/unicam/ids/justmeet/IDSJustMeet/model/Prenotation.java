package com.unicam.ids.justmeet.IDSJustMeet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prenotation {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="prenotation_id")
	private int id ;
	
	@Column(name="prenotation_id_event")
	private int idevent;
	
	@Column(name="prenotation_id_user")
	private int iduser;
	
	@Column(name="prenotation_event_titile")
	private String titleEvent;
	
	@Column(name="prenotation_event_description")
	private String descriptionEvent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdevent() {
		return idevent;
	}

	public void setIdevent(int idevent) {
		this.idevent = idevent;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getTitleEvent() {
		return titleEvent;
	}

	public void setTitleEvent(String titleEvent) {
		this.titleEvent = titleEvent;
	}

	public String getDescriptionEvent() {
		return descriptionEvent;
	}

	public void setDescriptionEvent(String descriptionEvent) {
		this.descriptionEvent = descriptionEvent;
	}
	
	
}
