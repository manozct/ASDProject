package com.asd.framework.Appointment;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.asd.framework.DatabaseConnection.Db.DbAccess;

public class Appointment {
	private Long Id;
	private AppointmentStatus status;
	private Long appointerId;
	private Long appointeeId;
	private LocalDateTime start;
	private LocalDateTime end;
	private LinkedList<AppointmentMessage> messages;

	public Appointment(Long Id, Long appointerId, Long appointeeId, LocalDateTime start, LocalDateTime end) {
		this.appointerId = appointerId;
		this.appointeeId = appointeeId;
		this.start = start;
		this.end = end;
		this.status = AppointmentStatus.NEW;
		this.Id = Id;
	}

	public void changeStatus(AppointmentStatus status) {
		this.status = status;
	}

	public void changeDate(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	public void setMessages(LinkedList<AppointmentMessage> messages) {
		this.messages = messages;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public Long getId() {
		return Id;
	}

	public Long getAppointerId() {
		return appointerId;
	}

	public Long getAppointeeId() {
		return appointeeId;
	}

	public LocalDateTime getStartTime() {
		return start;
	}

	public LocalDateTime getEndTime() {
		return end;
	}

	public LinkedList<AppointmentMessage> getMessages() {
		return messages;
	}
	
	public boolean addMessage(Long authorId, String text) {
		try {
			LocalDateTime date = LocalDateTime.now();
			AppointmentMessage message = new AppointmentMessage(authorId, text, date);
            Map<String,String> values=new HashMap<>();
            values.put("appointmentId", this.getId().toString());
            values.put("authorId", authorId.toString());
            values.put("date", String.valueOf(date.toEpochSecond(ZoneOffset.UTC)));
            values.put("text", text);
			DbAccess.table("appointment_messages").values(values).insert();
			messages.add(message);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}
}
