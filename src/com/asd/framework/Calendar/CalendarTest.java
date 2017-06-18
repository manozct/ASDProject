package com.asd.framework.Calendar;

import java.time.LocalDateTime;

import com.asd.framework.Appointment.Appointment;
import com.asd.framework.Appointment.WaitingAppointment;
import com.asd.framework.DatabaseConnection.Db.DatabaseType;
import com.asd.framework.DatabaseConnection.Db.DbConnection;
import com.asd.framework.Person.Person;
import com.asd.framework.Reminder.Reminder;

public class CalendarTest {

	public static void main(String[] args) {

		try {
			
			// User initializes DB connection
			DbConnection.getCOnnection(DatabaseType.MySql, "localhost", 3306, "mts", "root", "1234");
			
			// Initializing reminder manager
			Reminder reminder = new Reminder();
			//reminder.setEmailDelivery(smtp, username, password);
						
			// New calendar. All existing appointments and waitinglist is loaded from DB
			Calendar calendar = Calendar.getInstance();

			// Debugging
			System.out.println("-- Init --");
			System.out.println("-- Current appointments --");
			for (Appointment a:calendar.getAppointments())
				System.out.println(a.getId());
			System.out.println("-- Current waitinglist --");
			for (WaitingAppointment wa:calendar.getWaitingList())
				System.out.println(wa.getId());
			System.out.println("");

			// Sample user IDs
			KeyList<Person> staff = calendar.getStaffList();
			Long appointerId = 3L;
			Long appointeeId = 5L;

			// New appointment. If can not be added, change preferred date
			LocalDateTime preferred_start = LocalDateTime.of(2017,06,24,10,00);
			LocalDateTime preferred_end = LocalDateTime.of(2017,06,24,10,30);
			Appointment new_appointment = calendar.addAppointment(appointerId, appointeeId, preferred_start, preferred_end);
			if (new_appointment != null) {
				reminder.send(appointerId, "Your appointment is created, it will be approved soon");
				reminder.send(appointeeId, "You have new appointment. Please, review and approve it.");
			} else {
				System.out.println("Can not add new appointment. It could be overlapping!");
			}

			// Appointment get approved by appointee (e.g doctor)
			new_appointment.addMessage(appointeeId, "Got it");
			calendar.approveAppointment(new_appointment);
			reminder.send(appointerId, "Your appointment is approved");
			
			// New waiting appointment. Its dates are selected intentionally to overlap with previous
			LocalDateTime preferred_start1 = LocalDateTime.of(2017,06,24,10,10);
			LocalDateTime preferred_end1 = LocalDateTime.of(2017,06,24,10,20);
			WaitingAppointment waiting_appointment = calendar.addWaitingAppointment(appointerId, appointeeId, preferred_start1, preferred_end1);
			if (waiting_appointment == null) {
				reminder.send(appointerId, "Your appointment is in waiting list!");
			} else {
				System.out.println("Can not create waiting appointment. Could be DB error!");
			}

			// Debugging
			System.out.println("-- New appointment and new waiting appointment --");
			System.out.println("-- Current appointments --");
			for (Appointment a:calendar.getAppointments())
				System.out.println(a.getId());
			System.out.println("-- Current waitinglist --");
			for (WaitingAppointment wa:calendar.getWaitingList())
				System.out.println(wa.getId());
			System.out.println("");

			//New appointment is deleted. Waiting appointment should become appointment automatically.
			calendar.cancelAppointment(new_appointment);
			reminder.send(appointerId, "Your appointment is cancelled");

			// Debugging
			System.out.println("-- An appointment removed, waiting appointment promoted to appointment --");
			System.out.println("-- Current appointments --");
			for (Appointment a:calendar.getAppointments())
				System.out.println(a.getId());
			System.out.println("-- Current waitinglist --");
			for (WaitingAppointment wa:calendar.getWaitingList())
				System.out.println(wa.getId());
			System.out.println("");
			
			
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
		}
		
		
	}

}
