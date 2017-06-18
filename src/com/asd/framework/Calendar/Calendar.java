/*
package com.asd.framework.Calendar;

<<<<<<< HEAD
=======
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

>>>>>>> ebcca62232b417c22b5eda4b141a97c9752c2fe5
import com.asd.framework.Appointment.Appointment;
import com.asd.framework.Appointment.AppointmentStatus;
import com.asd.framework.Appointment.WaitingAppointment;
import com.asd.framework.DatabaseConnection.Db.DbAccess;
import com.asd.framework.Person.Person;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
		readWaitingListFromDB();
	}

	public KeyList<Appointment> getAppointments() {
		return appointments;
	}

	public Appointment getAppointment(Long Id) {
		return appointments.get(Id);
	}

	public Appointment addAppointment(Long appointerId, Long appointeeId, LocalDateTime start) {
		return addAppointment(appointerId, appointeeId, start, start.plusMinutes(defaultDuration));
	}

	public Appointment addAppointment(Long appointerId, Long appointeeId, LocalDateTime start, LocalDateTime end) {
		Long sn = start.toEpochSecond(ZoneOffset.UTC);
		Long en = end.toEpochSecond(ZoneOffset.UTC);
		for (Appointment m : appointments) {
			if ((m.getAppointeeId() == appointeeId) && (m.getStatus() != AppointmentStatus.CANCELLED)) {
				Long s = m.getStartTime().toEpochSecond(ZoneOffset.UTC);
				Long e = m.getEndTime().toEpochSecond(ZoneOffset.UTC);
				if ((sn>=s && sn<=e) || (en>=s && en<=e)) {
					return null;
				}
			}
		}
		long Id = 0;
		while (Id == 0 || appointments.get(Id) != null)
			Id = Math.round(Math.random() * 1000000);
		Appointment appointment = new Appointment(Id, appointerId, appointeeId, start, end);
		if (saveAppointmentToDB(appointment)) {
			appointments.add(Id, appointment);
			return appointment;
		} else {
			return null;
		}
	}

	public boolean approveAppointment(Appointment appointment) {
		appointment.changeStatus(AppointmentStatus.APPROVED);
		if (saveAppointmentStatusToDB(appointment)) {
			return true;
		} else {
			appointment.changeStatus(AppointmentStatus.NEW);
			return false;
		}
	}

	public boolean cancelAppointment(Appointment appointment) {
		appointment.changeStatus(AppointmentStatus.CANCELLED);
		if (saveAppointmentStatusToDB(appointment)) {
			for (WaitingAppointment wa : waitinglist)
				wa.update(this, appointment);
			return true;
		} else {
			appointment.changeStatus(AppointmentStatus.NEW);
			return false;
		}
	}

	public KeyList<WaitingAppointment> getWaitingList() {
		return waitinglist;
	}

	public WaitingAppointment addWaitingAppointment(Long appointerId, Long appointeeId, LocalDateTime start, LocalDateTime end) {
		long Id = 0;
		while (Id == 0 || waitinglist.get(Id) != null)
			Id = Math.round(Math.random() * 1000000);
		WaitingAppointment appointment = new WaitingAppointment(Id, appointerId, appointeeId, start, end);
		if (saveWaitingAppointmentToDB(appointment)) {
			waitinglist.add(Id, appointment);
			return appointment;
		} else {
			return null;
		}
	}

	public boolean removeWaitingAppointment(Long Id) {
		if (removeWaitingAppointmentFromDB(Id)) {
			waitinglist.remove(Id);
			return true;
		} else {
			return false;
		}
	}
	
	public void readAppointmentsFromDB() {
		appointments = new KeyList<>();
		try {
			ResultSet rs = DbAccess.table("appointments").select("recordId","status","appointer","appointee","start","end").get();
			while (rs.next()) {
				Long Id = rs.getLong(1);
				AppointmentStatus status = AppointmentStatus.valueOf(rs.getString(2)); 
				Appointment a = new Appointment(Id,rs.getLong(3),rs.getLong(4),
						LocalDateTime.ofEpochSecond(rs.getLong(5), 0, ZoneOffset.UTC),
						LocalDateTime.ofEpochSecond(rs.getLong(6), 0, ZoneOffset.UTC));
				a.changeStatus(status);
				appointments.add(Id, a);
			}
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
		}
	}
	
	public boolean saveAppointmentToDB(Appointment appointment) {
		try {
            Map<String,String> values=new HashMap<>();
            values.put("recordId", appointment.getId().toString());
            values.put("status", appointment.getStatus().name());
            values.put("appointer", appointment.getAppointerId().toString());
            values.put("appointee", appointment.getAppointeeId().toString());
            values.put("start", String.valueOf(appointment.getStartTime().toEpochSecond(ZoneOffset.UTC)));
            values.put("end", String.valueOf(appointment.getEndTime().toEpochSecond(ZoneOffset.UTC)));
			DbAccess.table("appointments").values(values).insert();
			return true;
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
			return false;
		}
	};
	
	public boolean saveAppointmentStatusToDB(Appointment appointment) {
		try {
            Map<String,String> values=new HashMap<>();
            values.put("status", appointment.getStatus().name());
			DbAccess.table("appointments").set(values).where("recordId",appointment.getId().toString()).update();
			return true;
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
			return false;
		}
	};
	
	public void readWaitingListFromDB() {
		waitinglist = new KeyList<>();
		try {
			ResultSet rs = DbAccess.table("waitinglist").select("recordId","appointer","appointee","start","end").get();
			while (rs.next()) {
				Long Id = rs.getLong(1);
				WaitingAppointment a = new WaitingAppointment(Id,rs.getLong(2),rs.getLong(3),
						LocalDateTime.ofEpochSecond(rs.getLong(3), 0, ZoneOffset.UTC),
						LocalDateTime.ofEpochSecond(rs.getLong(4), 0, ZoneOffset.UTC));
				waitinglist.add(Id,a);
			}
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
		}
	}
	
	public boolean saveWaitingAppointmentToDB(WaitingAppointment wa) {
		try {
            Map<String,String> values=new HashMap<>();
            values.put("recordId", wa.getId().toString());
            values.put("appointer", wa.getAppointerId().toString());
            values.put("appointee", wa.getAppointeeId().toString());
            values.put("start", String.valueOf(wa.getStartTime().toEpochSecond(ZoneOffset.UTC)));
            values.put("end", String.valueOf(wa.getEndTime().toEpochSecond(ZoneOffset.UTC)));
			DbAccess.table("waitinglist").values(values).insert();
			return true;
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
			return false;
		}
	}
	
	public boolean removeWaitingAppointmentFromDB(Long Id) {
		try {
			DbAccess.table("waitinglist").where("recordId",Id.toString()).delete();
			return true;
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
			return false;
		}
	}

	public KeyList<Person> getStaffList() {
		KeyList<Person> staff_list = new KeyList<>();
		try {
			ResultSet rs = DbAccess.table("users").select("recordId","name","birthdate","lon","lat").where("staff","Y").get();
			while (rs.next()) {
				Long Id = rs.getLong(1);
				Person p = new Person(Id,rs.getString(2));
				p.setBirthdate(LocalDate.ofEpochDay(rs.getLong(3)));
				p.setLocation(rs.getLong(4), rs.getLong(5));
				staff_list.add(Id, p);
			}
		} catch (Exception e) {
			System.out.println("Can not connect to database!");
		}
		return staff_list;
	}

}
*/
