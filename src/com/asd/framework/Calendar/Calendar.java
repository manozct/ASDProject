package com.asd.framework.Calendar;

import java.time.LocalDateTime;
import java.util.LinkedList;

import com.asd.framework.Appointment.Appointment;
import com.asd.framework.Appointment.AppointmentStatus;
import com.asd.framework.Appointment.WaitingAppointment;
import com.asd.framework.DatabaseConnection.Db.DbConnection;
import com.asd.framework.Participant.Participant;
import com.asd.framework.Participant.Reminder;

public class Calendar {

	private static Calendar instance = null;
	private KeyList<Appointment> appointments;
	private KeyList<WaitingAppointment> waitinglist;
	private DbConnection db;

	private int defaultDuration = 30;


	synchronized public static Calendar getInstance() {
		if (instance == null)
			instance = new Calendar();
		return instance;
	}

	private Calendar() {
		db = DbConnection.getCOnnection();
		// appointments = db.readAppointments();
		// read other config such as defaultDuration from DB too
	}


	public HashMap<Integer,Participant> getAppointableParticipants() {
	return db.readAppointableParticipants();
		return null;
	}

	public long addAppointment(Integer appointerId, Integer appointeeId, LocalDateTime date) {
	public long addAppointment(Integer appointerId, Integer appointeeId, LocalDateTime start) {
		return addAppointment(appointerId, appointeeId, start, start.plusMinutes(defaultDuration));
	}

	public long addAppointment(Integer appointerId, Integer appointeeId, LocalDateTime start, LocalDateTime end) {
		for (Appointment m : appointments) {
			if (m.getAppointeeId() == appointeeId) {
				LocalDateTime s = m.getStartTime();
				LocalDateTime e = m.getEndTime();
				if ((start.isAfter(s) && start.isBefore(e)) || (end.isAfter(s) && end.isBefore(e))) {
					return 0;
				}
			}
		}

		long Id = 0;
		while (Id == 0 || appointments.get(Id) != null)
			Id = Math.round(Math.random() * 1000000);
		Appointment appointment = new Appointment(Id, appointerId, appointeeId, start, end);
		// db.saveAppointment(appointment);
		appointments.add(Id, appointment);
		// They can be removed letting client create reminders
		addReminder(appointerId, "Your appointment is created, it will be approved soon");
		addReminder(appointeeId, "You have new appointment. Please, review and approve it.");
		return Id;
	}

	public Appointment getAppointment(Long Id) {
		return appointments.get(Id);
	}


	public boolean removeAppointment(Long Id) {
//		if (db.removeAppointment(Id)) {
			appointments.remove(Id);

	public boolean approveAppointment(Long Id) {
		Appointment appointment = appointments.get(Id);
		appointment.changeState(AppointmentStatus.APPROVED);
		addReminder(appointment.getAppointerId(), "Your appointment is approved");
//		return (db.saveAppointment(Id, appointment));
		return true;
	}

	public boolean cancelAppointment(Long Id) {
		Appointment appointment = appointments.remove(Id);
		appointment.changeState(AppointmentStatus.CANCELLED);
		addReminder(appointment.getAppointerId(), "Your appointment is cancelled");
		// if (db.saveAppointment(Id, appointment)) {
			for (WaitingAppointment wa : waitinglist)
				wa.update(this, appointment);

			return true;
		// } else return false;
	}

	public long addWaitingAppointment(Integer appointerId, Integer appointeeId, LocalDateTime start, LocalDateTime end) {
		long Id = 0;
		while (Id == 0 || waitinglist.get(Id) != null)
			Id = Math.round(Math.random() * 1000000);
		WaitingAppointment appointment = new WaitingAppointment(Id, appointerId, appointeeId, start, end);
		// db.saveWaitingAppointment(appointment);
		waitinglist.add(Id, appointment);
		addReminder(appointerId, "Your appointment is moved to waiting list!");
		return Id;
	}

	public boolean removeWaitingAppointment(Long Id) {
		// if (db.removeWaitingAppointment(Id)) {
		waitinglist.remove(Id);
		return true;
		// } else return false;
	}

	public LinkedList<Participant> getAppointableParticipants() {
//		 return db.readParticipants(ParticipantStatus.APPOINTABLE);
		return null;
	}

	public LinkedList<Participant> getParticipants() {
//		 return db.readParticipants(ParticipantStatus.NORMAL);
		return null;
	}

	public void addReminder(Integer participantId, String text) {
//		db.saveReminder(new Reminder(participantId, text));
	}
	
}
