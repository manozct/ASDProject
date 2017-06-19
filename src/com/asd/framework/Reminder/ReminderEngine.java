package com.asd.framework.Reminder;

public class ReminderEngine {

	private ReminderDefaultStrategy r;

	public ReminderEngine() {
		r = new ReminderDefaultStrategy();
	}
	
	public void setEmailDelivery(String smtp, String username, String password) {
		r = new ReminderEmailStrategy(smtp, username, password);
	}
	
	public void setSMSDelivery(String smsgateway, String username, String password) {
		r = new ReminderSMSStrategy(smsgateway, username, password);
	}
	
	public boolean send(Long receiverId, String text) {
		return r.send(receiverId, text);
	}

}
