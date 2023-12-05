/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 2276744
 */
public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/LibrarySystem.db";
    private static DatabaseConnection instance;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }
     public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public static void initialize() {
        String createBooksTableQuery = "CREATE TABLE IF NOT EXISTS Books ("
                + "SN TEXT PRIMARY KEY,"
                + "Title TEXT NOT NULL,"
                + "Author TEXT NOT NULL,"
                + "Publisher TEXT NOT NULL,"
                + "Price DECIMAL(10,2) NOT NULL,"
                + "Quantity INTEGER,"
                + "Issued INTEGER DEFAULT 0,"
                + "addedDate TEXT NOT NULL)";

        String createStudentsTable = "CREATE TABLE IF NOT EXISTS Students ("
                + "StudentId INTEGER PRIMARY KEY,"
                + "Name TEXT NOT NULL,"
                + "Contact TEXT NOT NULL)";
        String createIssuedBooksTable = "CREATE TABLE IF NOT EXISTS IssuedBooks ("
                + "ID INTEGER PRIMARY KEY,"
                + "SN TEXT NOT NULL,"
                + "StudentId INTEGER NOT NULL,"
                + "IssueDate TEXT NOT NULL,"
                + "StudentContact TEXT NOT NULL,"
                + "FOREIGN KEY (SN) REFERENCES BOOKS(SN)"
                + "FOREIGN KEY (StudentId) REFERENCES Students(StudentId))";
        try ( Connection conn = getConnection();  Statement statement = conn.createStatement()) {
            statement.executeUpdate(createBooksTableQuery);
            statement.executeUpdate(createIssuedBooksTable);
            statement.executeUpdate(createStudentsTable);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., log the error or throw a custom exception)
        }
    }

    public static void loadBooks(Library library) {
        String sqlSelectAllBooks = "SELECT * FROM Books";

        try ( Connection connection = getConnection();  Statement statement = connection.createStatement();  ResultSet rs = statement.executeQuery(sqlSelectAllBooks)) {

            while (rs.next()) {
                String sn = rs.getString("SN");

                // Check if the book with the same SN already exists in the library
                if (!library.containsBook(sn)) {
                    Book book = new Book(
                            sn,
                            rs.getInt("Price"),
                            rs.getInt("Quantity"),
                            rs.getInt("Issued"),
                            rs.getString("Title"),
                            rs.getString("Author"),
                            rs.getString("Publisher"),
                            rs.getString("addedDate"));
                    //library.addBook(book);
                    System.out.println(book.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
