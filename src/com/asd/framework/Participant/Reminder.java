package com.asd.framework.Participant;

import java.time.LocalDateTime;

public class Reminder {

	Integer participantId;
	String text;
	LocalDateTime date;
	ReminderStatus status;

	public Reminder(Integer participantId, String text) {
		this.participantId = participantId;
		this.text = text;
		this.date = LocalDateTime.now();
		this.status = ReminderStatus.NEW;
	}

	public String getText() {
		return text;
	}

	public LocalDateTime getDate() {
		return date;
	}

}
