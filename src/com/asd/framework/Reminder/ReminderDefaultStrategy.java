package com.asd.framework.Reminder;


public class ReminderDefaultStrategy {
	
	public boolean send(Integer receiverId, String text) {
//		db.saveReminder(receiverId.getId(), text, LocalDateTime.now(), ReminderStatus.NEW);
		return true;
	};

}
