/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

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
//    private void insertBookIntoDatabase(Book book) {
//        String insertQuery = "INSERT INTO Books (SN, Title, Author, Publisher, Price, Quantity, Issued, addedDate) " +
//                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//
//        try (PreparedStatement preparedStatement = DatabaseConnection.prepareStatement(insertQuery)) {
//            preparedStatement.setInt(1, book.getSN());
//            preparedStatement.setString(2, book.getTitle());
//            preparedStatement.setString(3, book.getAuthor());
//            preparedStatement.setString(4, book.getPublisher());
//            preparedStatement.setDouble(5, book.getPrice());
//            preparedStatement.setInt(6, book.getQte());
//            preparedStatement.setInt(7, book.getIssuedQte());
//            preparedStatement.setString(8, book.getDateOfPurchase());
//
//            // Execute the insert query
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle the exception appropriately (e.g., log the error or throw a custom exception)
//        }
//    }
}
