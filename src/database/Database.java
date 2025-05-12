package database;

import data.DriverInfo;
import service.DriverService;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static final String URL = "jdbc:sqlite:driver_management.db";
    private Connection conn;

    //Database methods
    public void connect() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL); // Establish a new connection if null or closed
            System.out.println("Connection established.");
        }

    }

    public void closeConnection() throws SQLException{
        if(conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public ArrayList<DriverInfo> executeQuery(String query){
        ArrayList<DriverInfo> drivers = new ArrayList<DriverInfo>();

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt("employee_id");
                String name = rs.getString("name");
                String tele = rs.getString("phone_number");
                String pass = rs.getString("password");
                double rate = rs.getDouble("hour_rate");
                drivers.add(new DriverInfo(id, name, tele, pass, rate));
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error processing query: " + e.getMessage());
        }

        return drivers;
    }

    public int executeUpdate(String query) {
        int count = 0;

        try {

            Statement stmt = conn.createStatement();
            count = stmt.executeUpdate(query);
            System.out.println("Query executed: " + query + " | Rows affected: " + count);

        } catch(SQLException e) {
            System.out.println("Error updating database: " + e.getMessage());
        }

        return count;
    }

    //Table methods
    public int insert(String table, String columns, String values) throws SQLException {
        Statement stmt = conn.createStatement();
        int inserted = stmt.executeUpdate("INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")");

        return inserted;
    }

    public ResultSet selectWhere(String table, String condition) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + condition);

        return rs;
    }

    public int update(String table, String column, String value, String condition) throws SQLException {

        Statement stmt = conn.createStatement();
        return stmt.executeUpdate("UPDATE " + table + " SET " + column + " = '" + value + "' WHERE " + condition);
    }
}
