package org.fitsay.tourist_trips.Database;

import java.sql.*;
public class DatabaseHandler
{
    public String url = "jdbc:sqlserver://localhost:1433;databaseName=K_PP;encrypt=true;trustServerCertificate=true;";
    public String username = "VADIM";
    public String password = "qwerty";
    public static Connection connection;
    public static Statement stm;
    public static ResultSet rs;
    public static boolean rs2;
    public void Connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            stm = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void Disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
