package data;

public class DriverInfo {
    private int employee_id;
    private double hour_rate;
    private String name;
    private String phone_number;
    private String password;

    public DriverInfo(int id, String name, String tele, String pass, double rate) {
        this.employee_id = id;
        hour_rate = rate;
        this.name = name;
        phone_number = tele;
        this.password = pass;
    }

    public int getId() {
        return employee_id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return password;
    }

    public String getTelephone() {
        return phone_number;
    }

    public double getRate() {
        return hour_rate;
    }

    public void setId(int id) {
        this.employee_id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.password = pass;
    }

    public void setTelephone(String telephone) {
        phone_number = telephone;
    }

    public void setHourlyRate(double hourlyRate) {
        hour_rate = hourlyRate;
    }
}
