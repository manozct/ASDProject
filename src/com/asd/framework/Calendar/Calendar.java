package com.asd.framework.Calendar;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.asd.framework.Appointment.Appointment;
import com.asd.framework.Appointment.AppointmentStatus;
import com.asd.framework.Appointment.WaitingAppointment;
import com.asd.framework.DatabaseConnection.Db.DbAccess;
import com.asd.framework.Participant.Participant;
import com.asd.framework.Reminder.Reminder;

public class Calendar {

	private static Calendar instance = null;
	private KeyList<Appointment> appointments;
	private KeyList<WaitingAppointment> waitinglist;
	private int defaultDuration = 30;

	synchronized public static Calendar getInstance() {
		if (instance == null)
			instance = new Calendar();
		return instance;
	}

	private Calendar() {
		
		readAppointmentsFromDB();

	}

	public long addAppointment(Integer appointerId, Integer appointeeId, LocalDateTime start) {
		return addAppointment(appointerId, appointeeId, start, start.plusMinutes(defaultDuration));
	}

	public long addAppointment(Integer appointerId, Integer appointeeId, LocalDateTime start, LocalDateTime end) {
		Long sn = start.toEpochSecond(ZoneOffset.UTC);
		Long en = end.toEpochSecond(ZoneOffset.UTC);
		for (Appointment m : appointments) {
			if ((m.getAppointeeId() == appointeeId) && (m.getStatus() != AppointmentStatus.CANCELLED)) {
				Long s = m.getStartTime().toEpochSecond(ZoneOffset.UTC);
				Long e = m.getEndTime().toEpochSecond(ZoneOffset.UTC);
				if ((sn>=s && sn<=e) || (en>=s && en<=e)) {
					return 0;
				}
			}
		}
		long Id = 0;
		while (Id == 0 || appointments.get(Id) != null)
			Id = Math.round(Math.random() * 1000000);
		Appointment appointment = new Appointment(Id, appointerId, appointeeId, start, end);
		saveAppointmentToDB(appointment);
		appointments.add(Id, appointment);
		// Reminder reminder = new Reminder();
		// reminder.setEmailDelivery(smtp, username, password);
		// reminder.send(appointerId, "Your appointment is created, it will be
		// approved soon");
		// reminder.send(appointeeId, "You have new appointment. Please, review
		// and approve it.");
		return Id;
	}

	public Appointment getAppointment(Long Id) {
		return appointments.get(Id);
	}

	public boolean approveAppointment(Long Id) {
		Appointment appointment = appointments.get(Id);
		appointment.changeState(AppointmentStatus.APPROVED);
		// addReminder(appointment.getAppointerId(), "Your appointment is
		// approved");
		// return (db.saveAppointment(Id, appointment));
		return true;
	}

	public boolean cancelAppointment(Long Id) {
		Appointment appointment = appointments.remove(Id);
		appointment.changeState(AppointmentStatus.CANCELLED);
		// addReminder(appointment.getAppointerId(), "Your appointment is
		// cancelled");
		// if (db.saveAppointment(Id, appointment)) {
		for (WaitingAppointment wa : waitinglist)
			wa.update(this, appointment);
		return true;
		// } else return false;
	}

	public long addWaitingAppointment(Integer appointerId, Integer appointeeId, LocalDateTime start,
			LocalDateTime end) {
		long Id = 0;
		while (Id == 0 || waitinglist.get(Id) != null)
			Id = Math.round(Math.random() * 1000000);
		WaitingAppointment appointment = new WaitingAppointment(Id, appointerId, appointeeId, start, end);
		// db.saveWaitingAppointment(appointment);
		waitinglist.add(Id, appointment);
		// addReminder(appointerId, "Your appointment is moved to waiting
		// list!");
		return Id;
	}

	public boolean removeWaitingAppointment(Long Id) {
		// if (db.removeWaitingAppointment(Id)) {
		waitinglist.remove(Id);
		return true;
		// } else return false;
	}

	public LinkedList<Participant> getAppointableParticipants() {
		// return db.readParticipants(ParticipantStatus.APPOINTABLE);
		return null;
	}

	public LinkedList<Participant> getParticipants() {
		// return db.readParticipants(ParticipantStatus.NORMAL);
		return null;
	}

	public void addReminder(Reminder reminder) {
		// reminder.send(db);
	}
	
	public void readAppointmentsFromDB() {
		appointments = new KeyList<>();
		try {
			ResultSet rs = DbAccess.table("appointments").select("recordId","status","appointer","appointee","start","end").get();
			while (rs.next()) {
				Long Id = rs.getLong(1);
				AppointmentStatus status = AppointmentStatus.valueOf(rs.getString(2)); 
				Appointment a = new Appointment(Id,rs.getInt(3),rs.getInt(4),
						LocalDateTime.ofEpochSecond(rs.getLong(5), 0, ZoneOffset.UTC),
						LocalDateTime.ofEpochSecond(rs.getLong(6), 0, ZoneOffset.UTC));
				a.changeState(status);
				appointments.add(Id, a);
			}
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
		}
	}
	
	public void saveAppointmentToDB(Appointment appointment) {
		try {
            Map<String,String> values=new HashMap<>();
            values.put("recordId", appointment.getId().toString());
            values.put("status", appointment.getStatus().name());
            values.put("appointer", appointment.getAppointerId().toString());
            values.put("appointee", appointment.getAppointeeId().toString());
            values.put("start", String.valueOf(appointment.getStartTime().toEpochSecond(ZoneOffset.UTC)));
            values.put("end", String.valueOf(appointment.getEndTime().toEpochSecond(ZoneOffset.UTC)));
			DbAccess.table("appointments").values(values).insert();
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
		}
	};


}
