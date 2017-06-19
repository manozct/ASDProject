/*

package com.asd.framework.Calendar;

import java.time.LocalDateTime;

import com.asd.framework.DatabaseConnection.Db.DatabaseType;
import com.asd.framework.DatabaseConnection.Db.DbConnection;

public class CalendarTest {

	public static void main(String[] args) {

		try {
			DbConnection.getCOnnection(DatabaseType.MySql, "localhost", 3306, "mts", "root", "1234");
			Calendar calendar = Calendar.getInstance();
			Long newappointment = calendar.addAppointment(3, 3, LocalDateTime.of(2017,06,19,10,00), LocalDateTime.of(2017,06,19,10,30));
			if (newappointment<=0) {
				System.out.println("Can not add new appointment. It could be overlapping of timeslot!");
				return;
			}
		} catch (Exception e) {
			System.out.println("Can not connect to database, please check your credentials!");
		}
		
		
	}

}

*/
