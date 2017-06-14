package com.asd.framework.Appointment;

import java.time.LocalDateTime;
import java.util.LinkedList;

import com.asd.framework.Participant.Participant;
import com.asd.framework.Tool.Tool;

public class Appointment {
	AppointmentState state;
	Participant appointer;
	Participant appointee;
	LinkedList<Participant> participants;
	LinkedList<Tool> tools;
	LocalDateTime date;
	LinkedList<Message> messages;
	public Appointment(Participant appointer, Participant appointee, LocalDateTime date) {
		this.appointer = appointer;
		this.appointee = appointee;
		this.date = date;
		this.state = AppointmentState.NEW;
	}
	public void changeState(AppointmentState state) {
		this.state = state;
	}
	public void changeDate(LocalDateTime date) {
		this.date = date;
	}
	public void addParticipant(Participant participant) {
		this.participants.add(participant);
	}
	public void removeParticipant(Participant participant) {
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
	public Participant getAppointer() {
		return appointer;
	}
	public Participant getAppointee() {
		return appointee;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public LinkedList<Participant> getParticipants() {
		return participants;
	}
	public LinkedList<Tool> getTools() {
		return tools;
	}
	public LinkedList<Message> getMessages() {
		return messages;
	}
}
