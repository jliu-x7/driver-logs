package data;

import database.Database;
import service.ShiftService;

import java.time.LocalTime;

public class TimeLogs {
    private int shift_id, employee_id;
    private LocalTime clock_in_time, clock_out_time;
    private double total_hours;
    ShiftService service;

    public TimeLogs(int id, int emp_id, Database db)
    {
        shift_id = id;
        employee_id = emp_id;
        service = new ShiftService(db);
    }

    /*Plans: implement getters and setters.
      Think if you want to calculate total_hours in constructor or in a method.
      - (thoughts)(there are only 3 timestamps, e.g. 4:00, 4:30, 5:00)

        First, I calculate total hours from the shift. (end - shift), then
        retrieve the total_hours from database and +=.

        Then upload it back to the database.
     */

    public double calculateTotalHours() {
        double hour = clock_out_time.getHour() - clock_in_time.getHour();
        double min = clock_out_time.getMinute() - clock_in_time.getMinute();

        return total_hours + (min / 60);
    }

    //Getter Methods
    public int getShift_id() {
        return shift_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public LocalTime getClock_in_time() {
        return clock_in_time;
    }

    public LocalTime getClock_out_time() {
        return clock_out_time;
    }

    public double getTotal_hours() {
        return total_hours;
    }

    //Setter Methods
    public void clockIn() {
        if(LocalTime.now().getMinute() <= 15) {
            clock_in_time = LocalTime.of(LocalTime.now().getHour(), 0);
        } else if (LocalTime.now().getMinute() >= 45) {
            clock_in_time = LocalTime.of(LocalTime.now().getHour() + 1, 0);
        }

        clock_in_time = LocalTime.of(LocalTime.now().getHour(), 30);
        service.updateClockIn(clock_in_time, employee_id);
    }

    public void clockOut() {
        if(LocalTime.now().getMinute() <= 15) {
            clock_out_time = LocalTime.of(LocalTime.now().getHour(), 0);
        } else if (LocalTime.now().getMinute() >= 45) {
            clock_out_time = LocalTime.of(LocalTime.now().getHour() + 1, 0);
        } else {
            clock_out_time = LocalTime.of(LocalTime.now().getHour(), 30);
        }
        total_hours += calculateTotalHours();
        service.updateClockOut(clock_in_time, employee_id);
    }
}
