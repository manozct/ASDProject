package com.asd.framework.Appointment;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Appointment {
	private Long Id;
	private AppointmentStatus status;
	private Integer appointerId;
	private Integer appointeeId;
	private LinkedList<Integer> participants;
	private LocalDateTime start;
	private LocalDateTime end;
	private LinkedList<AppointmentMessage> messages;

	public Appointment(Long Id, Integer appointerId, Integer appointeeId, LocalDateTime start, LocalDateTime end) {
		this.appointerId = appointerId;
		this.appointeeId = appointeeId;
		this.start = start;
		this.end = end;
		this.status = AppointmentStatus.NEW;
		this.Id = Id;
	}

	public void changeState(AppointmentStatus status) {
		this.status = status;
	}

	public void changeDate(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	public void addParticipant(Integer participant) {
		this.participants.add(participant);
	}

	public void removeParticipant(Integer participant) {
		this.participants.remove(participant);
	}

	public void newMessage(AppointmentMessage message) {
		this.messages.add(message);
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public Long getId() {
		return Id;
	}

	public Integer getAppointerId() {
		return appointerId;
	}

	public Integer getAppointeeId() {
		return appointeeId;
	}

	public LocalDateTime getStartTime() {
		return start;
	}

	public LocalDateTime getEndTime() {
		return end;
	}

	public LinkedList<Integer> getParticipants() {
		return participants;
	}

	public LinkedList<AppointmentMessage> getMessages() {
		return messages;
	}
}
