package com.asd.framework.Reminder;

public class Reminder {

	private ReminderDefaultStrategy r;

	public Reminder() {
		r = new ReminderDefaultStrategy();
	}
	
	public void setEmailDelivery(String smtp, String username, String password) {
		r = new ReminderEmailStrategy(smtp, username, password);
	}
	
	public void setSMSDelivery(String smsgateway, String username, String password) {
		r = new ReminderSMSStrategy(smsgateway, username, password);
	}
	
	public boolean send(Integer receiverId, String text) {
		return r.send(receiverId, text);
	}

}