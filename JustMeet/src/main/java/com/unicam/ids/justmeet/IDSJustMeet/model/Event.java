package com.unicam.ids.justmeet.IDSJustMeet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="event")
public class Event {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="event_id")
	private int id;
	
	@NotNull(message = "Id cratore evento non inserito!")
	@Column(name = "id_creator")
	private int creator;
	
	@NotNull(message="Inserisci il Titolo dell'Evento!")
	@Column(name="event_title")
	private String title;
	
	@NotNull(message="Scegli la categoria dell'Evento")
	@Column(name="event_category")
	private String category;
	
	@NotNull(message="Inserisci il luogo0 dell'evento")
	@Column(name="event_place")
	private String place;
	
	@NotNull(message="Inserisci una descizione per l'Evento")
	@Column(name="event_description")
	private String description;
	
	@NotNull(message="Scegli una data per l'evento")
	@Column(name="event_date")
	private String date;
	
	@NotNull(message="Inserisci l'ora di inizio dell'evento")
	@Column(name="event_start_hour")
	private String starthour;
	
	@NotNull(message="Inserisci l'ora del termine dell'evento")
	@Column(name="event_end_hour")
	private String endhour;
	
	@Column(name="event_max_partecipants")
	private int maxpartecipants;
	
	@Column(name="event_status")
	private String status;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStarthour() {
		return starthour;
	}

	public void setStarthour(String starthour) {
		this.starthour = starthour;
	}

	public String getEndhour() {
		return endhour;
	}

	public void setEndhour(String endhour) {
		this.endhour = endhour;
	}

	public int getMaxpartecipants() {
		return maxpartecipants;
	}

	public void setMaxpartecipants(int maxpartecipants) {
		this.maxpartecipants = maxpartecipants;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
