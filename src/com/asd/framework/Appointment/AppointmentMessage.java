package com.asd.framework.Appointment;

import java.time.LocalDateTime;

import com.asd.framework.Person.Person;

public class AppointmentMessage {
	String text;
	Person author;
	LocalDateTime date;

	public AppointmentMessage(String text, Person author) {
		this.text = text;
		this.author = author;
		this.date = LocalDateTime.now();
	}

	public String getText() {
		return text;
	}

	public Person getAuthor() {
		return author;
	}

	public LocalDateTime getDate() {
		return date;
	}

}
