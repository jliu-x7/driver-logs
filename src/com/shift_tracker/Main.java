package com.shift_tracker;

import database.Database;
import data.TimeLogs;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();

        try {
            db.connect(); // Establish connection

            // Create a TimeLogs object for employee ID 1
            TimeLogs log = new TimeLogs(1, 1, db);

            // Simulate a clock-in event
            System.out.println("Clocking in...");
            log.clockIn();

            // Simulate waiting time (for testing, we'll just proceed to clock-out)
            Thread.sleep(2000); // Simulating delay (2 sec)

            // Simulate a clock-out event
            System.out.println("Clocking out...");
            log.clockOut();

        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread sleep interrupted: " + e.getMessage());
        } finally {
            try {
                db.closeConnection(); // Ensure connection is closed
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
