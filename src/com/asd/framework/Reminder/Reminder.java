package com.asd.framework.Reminder;

import java.time.LocalDateTime;

public class Reminder {
	String text;
	LocalDateTime date;
	ReminderStatus status;
	public Reminder(String text, LocalDateTime date, ReminderStatus status) {
		this.text = text;
		this.date =  date;
		this.status = status;
	}
	public String getText() {
		return text;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public ReminderStatus getStatus() {
		return status;
	}
}
