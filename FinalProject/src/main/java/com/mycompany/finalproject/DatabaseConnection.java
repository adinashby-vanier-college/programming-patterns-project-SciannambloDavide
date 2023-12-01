/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 2276744
 */
public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/LibrarySystem.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }
     public static void initialize() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Books ("
                + "SN TEXT PRIMARY KEY,"
                + "Title TEXT NOT NULL,"
                + "Author TEXT NOT NULL,"
                + "Publisher TEXT NOT NULL,"
                + "Price REAL NOT NULL,"
                + "Quantity INTEGER,"
                + "Issued INTEGER DEFAULT 0,"
                + "addedDate DATE NOT NULL)";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., log the error or throw a custom exception)
        }
    }
}
