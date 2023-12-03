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

    public Library() {
        books = new ArrayList<>();
    }

    public static Library getInstance() {
        if (libraryObject == null) {
            libraryObject = new Library();
        }
        return libraryObject;
    }

    public void addBook(Book book) {
        books.add(book);
        insertBookIntoDatabase(book);
    }

    private void insertBookIntoDatabase(Book book) {
        String insertQuery = "INSERT INTO Books (SN, Title, Author, Publisher, Price, Quantity, Issued, addedDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( Connection connection = DatabaseConnection.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, book.getSN());
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
        if (!verifyStudentInformation(student)) {
            return false; // Student information is not valid
        }

        // Check if the book is available
        if (book.getQte() <= 0) {
            return false; // Book is not available
        }

        // Decrease the quantity of available copies and increase the issued copies
        book.setQte(book.getQte() - 1);
        book.setIssuedQte(book.getIssuedQte() + 1);

        // Add a new entry to the "IssuedBooks" table
        addToIssuedBooksTable(book, student);

        // Return true indicating the book was successfully issued
        return true;
    }

    public boolean verifyStudentInformation(Student student) {
        // Check if the student object and its fields are not null and not empty
        return student != null
                && student.getStudentId() != 0 && student.getStudentId() != 0
                && student.getName() != null && !student.getName().isEmpty()
                && student.getContact() != null && !student.getContact().isEmpty();
    }

    private void addToIssuedBooksTable(Book book, Student student) {
        String insertQuery = "INSERT INTO IssuedBooks (SN, StId, StName, StudentContact, IssueDate) "
                + "VALUES (?, ?, ?, ?, ?)";

        try ( Connection connection = DatabaseConnection.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, book.getSN());
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

    public boolean containsBook(String sn) {
        for (Book book : books) {
            if (book.getSN().equals(sn)) {
                return true; // Book with the same SN exists in the library
            }
        }
        return false; // Book with the specified SN is not in the library
    }

}
