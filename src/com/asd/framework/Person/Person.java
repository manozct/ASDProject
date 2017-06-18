package com.asd.framework.Person;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;

import com.asd.framework.Appointment.Appointment;
import com.asd.framework.Appointment.AppointmentMessage;
import com.asd.framework.Appointment.AppointmentStatus;
import com.asd.framework.Appointment.WaitingAppointment;
import com.asd.framework.Calendar.KeyList;
import com.asd.framework.DatabaseConnection.Db.DbAccess;
import com.asd.framework.Reminder.Reminder;
import com.asd.framework.Reminder.ReminderStatus;

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
		LinkedList<Reminder> reminders = new LinkedList<>();
		try {
			ResultSet rs = DbAccess.table("reminders").select("text","date","status")
					.where("receiverId",Id.toString()).get();
			while (rs.next()) {
				Reminder a = new Reminder(rs.getString(1),LocalDateTime.ofEpochSecond(rs.getLong(2), 0, ZoneOffset.UTC),
						ReminderStatus.valueOf(rs.getString(3)));
				reminders.add(a);
			}
		} catch (Exception e) {
		}
		return reminders;
	}
	
	public KeyList<Appointment> getAppointments() {
		KeyList<Appointment> appointments = new KeyList<>();
		try {
			ResultSet rs = DbAccess.table("appointments").select("recordId","status","appointee","start","end")
					.where("appointer",Id.toString()).orWhere("appointee",Id.toString()).get();
			while (rs.next()) {
				Long Id = rs.getLong(1);
				AppointmentStatus status = AppointmentStatus.valueOf(rs.getString(2)); 
				Appointment a = new Appointment(Id,this.getId(),rs.getLong(3),
						LocalDateTime.ofEpochSecond(rs.getLong(4), 0, ZoneOffset.UTC),
						LocalDateTime.ofEpochSecond(rs.getLong(5), 0, ZoneOffset.UTC));
				a.changeStatus(status);
				ResultSet rs1 = DbAccess.table("appointment_messages").select("authorId","text","date")
						.where("appointmentId",Id.toString()).get();
				LinkedList<AppointmentMessage> messages = new LinkedList<>();
				while (rs1.next())
					messages.add(new AppointmentMessage(rs1.getLong(1), rs1.getString(2), 
							LocalDateTime.ofEpochSecond(rs1.getLong(3), 0, ZoneOffset.UTC)));
				a.setMessages(messages);
				appointments.add(Id, a);
			}
		} catch (Exception e) {
		}
		return appointments;
	}
	
	public KeyList<WaitingAppointment> getWaitingAppointments() {
		KeyList<WaitingAppointment> appointments = new KeyList<>();
		try {
			ResultSet rs = DbAccess.table("waitinglist").select("recordId","appointee","start","end")
					.where("appointer",Id.toString()).orWhere("appointee",Id.toString()).get();
			while (rs.next()) {
				Long Id = rs.getLong(1);
				WaitingAppointment a = new WaitingAppointment(Id,this.getId(),rs.getLong(2),
						LocalDateTime.ofEpochSecond(rs.getLong(3), 0, ZoneOffset.UTC),
						LocalDateTime.ofEpochSecond(rs.getLong(4), 0, ZoneOffset.UTC));
				appointments.add(Id, a);
			}
		} catch (Exception e) {
		}
		return appointments;
	}
	
}
