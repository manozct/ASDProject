/*

package com.asd.framework.Appointment;

import java.time.LocalDateTime;

import com.asd.framework.Calendar.Calendar;

public class WaitingAppointment {

	private Long Id;
	private Integer appointerId;
	private Integer appointeeId;
	private LocalDateTime start;
	private LocalDateTime end;

	public WaitingAppointment(Long Id, Integer appointerId, Integer appointeeId, LocalDateTime start, LocalDateTime end) {
		this.appointerId = appointerId;
		this.appointeeId = appointeeId;
		this.start = start;
		this.end = end;
		this.Id = Id;
	}

	public void update(Calendar calendar, Appointment other) {
		if (other.getAppointeeId() == appointeeId) {
			LocalDateTime s = other.getStartTime();
			LocalDateTime e = other.getEndTime();
			if ((start.isEqual(s) || start.isAfter(s)) && (end.isEqual(e) || end.isBefore(e))) {
				calendar.addAppointment(appointerId, appointeeId, start, end);
				calendar.removeWaitingAppointment(Id);
			}
		}

	}
}

*/
