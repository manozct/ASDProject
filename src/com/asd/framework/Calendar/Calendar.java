package com.asd.framework.Calendar;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.asd.framework.Appointment.Appointment;
import com.asd.framework.DatabaseConnection.DbConnection;
import com.asd.framework.Participant.Participant;

public class Calendar {
	
	private static Calendar instance = null;
	private Map<Long,Appointment> appointments;
	private DbConnection db;
	
	synchronized public static Calendar getInstance() {
		if (instance == null)
			instance = new Calendar();
		return instance;
	}

	private Calendar() {
		db = new DbConnection();
//		appointments = db.readAppointments();
	}
	
	public HashMap<Integer,Participant> getAppointableParticipants() {
//		return db.readAppointableParticipants();
		return null;
	}
	
	public long addAppointment(Integer appointerId, Integer appointeeId, LocalDateTime date) {
		long Id = 0;
		while (Id==0||appointments.get(Id)!=null)
			Id = Math.round(Math.random()*1000000);
		Appointment appointment = new Appointment(Id, appointerId, appointeeId, date);
//		db.saveAppointment(appointment);
		appointments.put(Id, appointment);
		return Id;
	}
	
	public Appointment getAppointment(Long Id) {
		return appointments.get(Id);
	}
	
	public boolean removeAppointment(Long Id) {
//		if (db.removeAppointment(Id)) {
			appointments.remove(Id);
			return true;
//		} else return false;
	}

}
