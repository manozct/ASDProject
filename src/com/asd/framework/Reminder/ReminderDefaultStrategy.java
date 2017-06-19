package com.asd.framework.Reminder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import com.asd.framework.DatabaseConnection.Db.DbAccess;

public class ReminderDefaultStrategy {
	
	public boolean send(Long receiverId, String text) {
		try {
            Map<String,String> values=new HashMap<>();
            values.put("receiverId", receiverId.toString());
            values.put("text", text);
            values.put("date", String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
            values.put("status", ReminderStatus.NEW.name());
			DbAccess.table("reminders").values(values).insert();
			return true;
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
			return false;
		}
	}

}
