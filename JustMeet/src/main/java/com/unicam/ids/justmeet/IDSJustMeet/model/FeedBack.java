package com.unicam.ids.justmeet.IDSJustMeet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="feedback")
public class FeedBack {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="feedback_id")
	private int id;
	
	@NotNull(message = "Id recensore nullo!")
	@Column(name = "id_reviewer")
	private int idreviewer;
	
	@NotNull(message = "Id recensito nullo!")
	@Column(name = "id_reviewed")
	private int idreviewed;
	
	@NotNull(message = "Email recensore nullo!")
	@Column(name = "email_reviewer")
	private String emailrewiever;
	
	@NotNull(message = "Feedback non presente!")
	@Column(name = "feedback_desc")
	private String feedback;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdreviewer() {
		return idreviewer;
	}

	public void setIdreviewer(int idreviewer) {
		this.idreviewer = idreviewer;
	}

	public int getIdreviewed() {
		return idreviewed;
	}

	public void setIdreviewed(int idreviewed) {
		this.idreviewed = idreviewed;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getEmailrewiever() {
		return emailrewiever;
	}

	public void setEmailrewiever(String emailrewiever) {
		this.emailrewiever = emailrewiever;
	}
	
	
}
