package com.asd.framework.Reminder;


public class ReminderEmailStrategy extends ReminderDefaultStrategy {

	private String smtp;
	private String username;
	private String password;
	
	public ReminderEmailStrategy(String smtp, String username, String password) {
		this.smtp = smtp;
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean send(Long receiverId, String text) {
		//Connect to smtp server
		return true;
	}

	public String getSmtp() {
		return smtp;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
