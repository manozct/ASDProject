package com.asd.framework.Person;

import java.time.LocalDate;
import java.util.LinkedList;

import com.asd.framework.Appointment.Appointment;
import com.asd.framework.Calendar.KeyList;
import com.asd.framework.Reminder.Reminder;

public class Person {

	Long Id;
	String name;
	LocalDate birthdate;
	Long lon;
	Long lat;
	
	public Person(Long Id, String name) {
		this.name = name;
		this.Id = Id;
	}
	
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public void setLocation(Long lon, Long lat) {
		this.lon = lon;
		this.lat = lat;
	}
	
	public Long getLongitude() {
		return lon;
	}
	
	public Long getLatitude() {
		return lat;
	}
	
	public String getName() {
		return name;
	}
	
	public Long getId() {
		return Id;
	}
	
	public LinkedList<Reminder> getReminders() {
//		DbConnection db = DbConnection.getCOnnection();
//		return db.getReminders(this.Id, ReminderStatus.NEW);
		return null;
	}
	
	public KeyList<Appointment> getAppointments() {
		return null;
	}
	
}
