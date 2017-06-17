package com.asd.framework.Reminder;

public class ReminderEmail extends Reminder {

	public ReminderEmail(Integer participantId, String text) {
		super(participantId, text);
	}

	@Override
	public void send() {
		//Send email using Sendgrid
	}

}
