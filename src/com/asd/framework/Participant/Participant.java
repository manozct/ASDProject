package com.asd.framework.Participant;

import java.time.LocalDate;
import java.util.LinkedList;

public class Participant {

	Long Id;
	boolean appointable;
	String name;
	LocalDate birthdate;
	
	public Participant(String name, boolean appointable) {
		this.name = name;
		this.appointable = appointable;
		long Id = 0;
//		DbConnection db = new DbConnection();
//		while (Id==0||db.getParticipant(Id)!=null)
//			Id = Math.round(Math.random()*1000000);
		this.Id = Id;
	}
	
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public boolean isAppointable() {
		return appointable;
	}
	
	public String getName() {
		return name;
	}
	
	public LinkedList<Reminder> getReminders() {
//		DbConnection db = DbConnection.getCOnnection();
//		return db.getReminders(this.Id, ReminderStatus.NEW);
		return null;
	}
	
}
