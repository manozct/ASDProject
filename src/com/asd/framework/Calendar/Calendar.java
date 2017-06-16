package com.asd.framework.Calendar;

import java.time.LocalDateTime;
import java.util.HashMap;
import com.asd.framework.Appointment.Appointment;
import com.asd.framework.DatabaseConnection.DbConnection;
import com.asd.framework.Participant.Participant;
import com.asd.framework.Utils.KeyList;

public class Calendar {
	
	private static Calendar instance = null;
	private KeyList<Appointment> appointments;
	private DbConnection db;
	private int defaultDuration = 30;
	
	synchronized public static Calendar getInstance() {
		if (instance == null)
			instance = new Calendar();
		return instance;
	}

	private Calendar() {
		db = new DbConnection();
//		appointments = db.readAppointments();
//		read other config such as defaultDuration from DB too 
	}
	
	public HashMap<Integer,Participant> getAppointableParticipants() {
//		return db.readAppointableParticipants();
		return null;
	}
	
	public long addAppointment(Integer appointerId, Integer appointeeId, LocalDateTime start) {
		return addAppointment(appointerId, appointeeId, start, start.plusMinutes(defaultDuration));
	}
	
	public long addAppointment(Integer appointerId, Integer appointeeId, LocalDateTime start, LocalDateTime end) {

		for (Appointment m: appointments) {
			LocalDateTime s = m.getStartTime();
			LocalDateTime e = m.getEndTime();
			if ((start.isAfter(s) && start.isBefore(e)) || (end.isAfter(s) && end.isBefore(e))) {
				return 0;
			}
		}

		long Id = 0;
		while (Id==0||appointments.get(Id)!=null)
			Id = Math.round(Math.random()*1000000);
		Appointment appointment = new Appointment(Id, appointerId, appointeeId, start, end);
//		db.saveAppointment(appointment);
		appointments.add(Id, appointment);
		return Id;
	}
	
	public Appointment getAppointment(Long Id) {
		return appointments.get(Id);
	}
	
	public boolean cancelAppointment(Long Id) {
//		if (db.removeAppointment(Id)) {
			appointments.remove(Id);
			return true;
//		} else return false;
	}

}
