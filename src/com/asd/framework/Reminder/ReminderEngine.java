package com.asd.framework.Reminder;

public class ReminderEngine {

	private ReminderDefaultStrategy strategy;

	public ReminderEngine() {
		strategy = new ReminderDefaultStrategy();
	}
	
	public void setEmailDelivery(String smtp, String username, String password) {
		strategy = new ReminderEmailStrategy(smtp, username, password);
	}
	
	public void setSMSDelivery(String smsgateway, String username, String password) {
		strategy = new ReminderSMSStrategy(smsgateway, username, password);
	}
	
	public boolean send(Long receiverId, String text) {
		return strategy.send(receiverId, text);
	}

}
