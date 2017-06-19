package com.asd.framework.Reminder;

public class ReminderSMSStrategy extends ReminderDefaultStrategy {

	private String smsgateway;
	private String username;
	private String password;

	public ReminderSMSStrategy(String smsgateway, String username, String password) {
		this.smsgateway = smsgateway;
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean send(Long receiverId, String text) {
		//Connect to SMS Gateway
		return true;
	}

	public String getSmsgateway() {
		return smsgateway;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
