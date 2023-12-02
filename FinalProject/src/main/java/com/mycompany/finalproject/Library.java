/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 2276744
 */
public class Library {

    private static Library libraryObject;
    private List<Book> books;

    public static Library getInstance() {
        if (libraryObject == null) {
            libraryObject = new Library();
        }
        return libraryObject;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    private void insertBookIntoDatabase(Book book) {
        String insertQuery = "INSERT INTO Books (SN, Title, Author, Publisher, Price, Quantity, Issued, addedDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( Connection connection = DatabaseConnection.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, book.getSN());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getPublisher());
            preparedStatement.setDouble(5, book.getPrice());
            preparedStatement.setInt(6, book.getQte());
            preparedStatement.setInt(7, book.getIssuedQte());
            preparedStatement.setString(8, book.getDateOfPurchase());

            // Execute the insert query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., log the error or throw a custom exception)
        }
    }

    public boolean issueBook(Book book, Student student) {
        // Verify student information (you may need to implement this verification)
        if (!verifyStudentInformation(student)) {
            return false; // Student information is not valid
        }

        // Check if the book is available
        if (book.getQte()<= 0) {
            return false; // Book is not available
        }

        // Decrease the quantity of available copies and increase the issued copies
        book.setQte(book.getQte()- 1);
        book.setIssuedQte(book.getIssuedQte()+ 1);

        // Add a new entry to the "IssuedBooks" table
        addToIssuedBooksTable(book, student);

        // Return true indicating the book was successfully issued
        return true;
    }

    private boolean verifyStudentInformation(Student student) {
        // Implement your student verification logic here
        // You may check student details, roll number, etc.
        // Return true if student information is valid; otherwise, return false
        return true;
    }

    private void addToIssuedBooksTable(Book book, Student student) {
        String insertQuery = "INSERT INTO IssuedBooks (SN, StId, StName, StudentContact, IssueDate) "
                + "VALUES (?, ?, ?, ?, ?)";

        try ( Connection connection = DatabaseConnection.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, book.getSN());
            preparedStatement.setInt(2, student.getStudentId());
            preparedStatement.setString(3, student.getName());
            preparedStatement.setString(4, student.getContact());
            preparedStatement.setString(5, book.dateOfPurchase);

            // Execute the insert query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., log the error or throw a custom exception)
        }
    }

}
