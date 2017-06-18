package com.asd.framework.Appointment;

import java.time.LocalDateTime;

public class AppointmentMessage {
	String text;
	Long authorId;
	LocalDateTime date;

	public AppointmentMessage(Long authorId, String text, LocalDateTime date) {
		this.text = text;
		this.authorId = authorId;
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public Long getAuthor() {
		return authorId;
	}

	public LocalDateTime getDate() {
		return date;
	}

}
