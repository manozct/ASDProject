package com.asd.framework.Appointment;

import java.time.LocalDateTime;

import com.asd.framework.Participant.Participant;

public class AppointmentMessage {
	String text;
	Participant author;
	LocalDateTime date;

	public AppointmentMessage(String text, Participant author) {
		this.text = text;
		this.author = author;
		this.date = LocalDateTime.now();
	}

	public String getText() {
		return text;
	}

	public Participant getAuthor() {
		return author;
	}

	public LocalDateTime getDate() {
		return date;
	}

}
