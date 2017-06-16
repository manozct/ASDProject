package com.asd.framework.Appointment;

import java.time.LocalDateTime;
import java.util.LinkedList;

import com.asd.framework.Tool.Tool;

public class Appointment {
	Long Id;
	AppointmentState state;
	Integer appointerId;
	Integer appointeeId;
	LinkedList<Integer> participants;
	LinkedList<Tool> tools;
	LocalDateTime start;
	LocalDateTime end;
	LinkedList<Message> messages;
	public Appointment(Long Id, Integer appointerId, Integer appointeeId, LocalDateTime start, LocalDateTime end) {
		this.appointerId = appointerId;
		this.appointeeId = appointeeId;
		this.start = start;
		this.end = end;
		this.state = AppointmentState.NEW;
		this.Id = Id;
	}
	public void changeState(AppointmentState state) {
		this.state = state;
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
	public void addTool(Tool tool) {
		this.tools.add(tool);
	}
	public void removeTool(Tool tool) {
		this.tools.remove(tool);
	}
	public void newMessage(Message message) {
		this.messages.add(message);
	}
	public AppointmentState getState() {
		return state;
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
	public LinkedList<Tool> getTools() {
		return tools;
	}
	public LinkedList<Message> getMessages() {
		return messages;
	}
}
