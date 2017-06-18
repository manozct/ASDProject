package com.asd.framework.Appointment;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.asd.framework.Calendar.Calendar;

public class WaitingAppointment {

	private Long Id;
	private Long appointerId;
	private Long appointeeId;
	private LocalDateTime start;
	private LocalDateTime end;

	public WaitingAppointment(Long Id, Long appointerId, Long appointeeId, LocalDateTime start, LocalDateTime end) {
		this.appointerId = appointerId;
		this.appointeeId = appointeeId;
		this.start = start;
		this.end = end;
		this.Id = Id;
	}

	public void update(Calendar calendar, Appointment other) {
		if (other.getAppointeeId() == appointeeId) {
			Long sn = start.toEpochSecond(ZoneOffset.UTC);
			Long en = end.toEpochSecond(ZoneOffset.UTC);
			Long so = other.getStartTime().toEpochSecond(ZoneOffset.UTC);
			Long eo = other.getEndTime().toEpochSecond(ZoneOffset.UTC);
			if (sn>=so && en<=eo) {
				calendar.addAppointment(appointerId, appointeeId, start, end);
				calendar.removeWaitingAppointment(Id);
			}
		}
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
}
