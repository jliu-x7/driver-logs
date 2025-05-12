package service;

import database.Database;
import data.TimeLogs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ShiftService {
    Database db;

    public ShiftService(Database db) {
        this.db = db;
    }

    public void updateClockIn(LocalTime start, int id) {
        db.executeUpdate("UPDATE shifts SET clock_in_time = '" + start.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "' WHERE employee_id = " + id);
    }

    public void updateClockOut(LocalTime end, int id) {
        db.executeUpdate("UPDATE shifts SET clock_out_time = '" + end.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "' WHERE employee_id = " + id);
    }

    public void updateHours(double hours, int id) {
        double totalHours = hours;
        try {
            ResultSet rs = db.selectWhere("shifts", " WHERE employee_id = " + id);
            if (rs.next()) {
                totalHours += rs.getDouble("total_hours");
                db.executeUpdate("UPDATE shifts SET total_hours = " + totalHours + " WHERE employee_id = " + id);
            } else {
                System.out.println("No shifts found for employee ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error at ShiftService method updateHours: " + e.getMessage());
        }
    }
}
