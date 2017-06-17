package com.asd.framework.Reminder;

import java.time.LocalDateTime;

public abstract class Reminder {

	private Integer participantId;
	private String text;
	private LocalDateTime date;
	private ReminderStatus status;

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
	
	public abstract void send();

}
