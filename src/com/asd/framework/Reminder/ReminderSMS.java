package com.asd.framework.Reminder;

public class ReminderSMS extends Reminder {

	public ReminderSMS(Integer participantId, String text) {
		super(participantId, text);
	}

	@Override
	public void send() {
		//Connect to SMS Gateway
	}

}
