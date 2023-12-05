/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author 2276744
 */
public class Student {

    private int studentId;
    private String name, contactNumber;

    public Student(int studentId, String name, String contact) {
        this.studentId = studentId;
        this.name = name;
        this.contactNumber = contact;
    }

    public Map<String, String> viewCatalog() throws SQLException {
        return Library.viewCatalog(); // This assumes you want the student to access the same catalog as in the Library class.
    }

    public static List<Book> searchBookByTitle(String title) throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books WHERE Title LIKE ?")) {
            stmt.setString(1, "%" + title + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String sn = rs.getString("SN");
                    int price = rs.getInt("Price");
                    int qte = rs.getInt("Quantity");
                    int issuedQte = rs.getInt("Issued");
                    String bookTitle = rs.getString("Title");
                    String author = rs.getString("Author");
                    String publisher = rs.getString("Publisher");
                    String dateOfPurchase = rs.getString("addedDate"); // Assuming there is a field like this

                    Book book = new Book(sn, price, qte, issuedQte, bookTitle, author, publisher, dateOfPurchase);
                    books.add(book);
                }
            }
        }
        return books;
    }

    // Method to search books by author's name
    public static List<Book> searchBookByName(String name) throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books WHERE Author LIKE ?")) {
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String sn = rs.getString("SN");
                    int price = rs.getInt("Price");
                    int qte = rs.getInt("Quantity");
                    int issuedQte = rs.getInt("Issued");
                    String bookTitle = rs.getString("Title");
                    String author = rs.getString("Author");
                    String publisher = rs.getString("Publisher");
                    String dateOfPurchase = rs.getString("addedDate"); // Assuming there is a field like this

                    Book book = new Book(sn, price, qte, issuedQte, bookTitle, author, publisher, dateOfPurchase);
                    books.add(book);
                }
            }
        }
        return books;
    }

    // Method to search books by publisher
    public static List<Book> searchBookByPublisher(String publisher) throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books WHERE Publisher LIKE ?")) {
            stmt.setString(1, "%" + publisher + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String sn = rs.getString("SN");
                    int price = rs.getInt("Price");
                    int qte = rs.getInt("Quantity");
                    int issuedQte = rs.getInt("Issued");
                    String bookTitle = rs.getString("Title");
                    String author = rs.getString("Author");
                    String publisher2 = rs.getString("Publisher");
                    String dateOfPurchase = rs.getString("addedDate"); // Assuming there is a field like this

                    Book book = new Book(sn, price, qte, issuedQte, bookTitle, author, publisher2, dateOfPurchase);
                    books.add(book);
                }
            }
        }
        return books;
    }

    // Method to borrow a book
    public static boolean borrow(Book book) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Books SET Quantity = Quantity - 1, Issued = Issued + 1 WHERE SN = ? AND Quantity > 0")) {
            stmt.setString(1, book.getSN());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // Method to return a book
    public static boolean toReturn(Book book) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Books SET Quantity = Quantity + 1, Issued = Issued - 1 WHERE SN = ?")) {
            stmt.setString(1, book.getSN());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contactNumber;
    }

    public void setContact(String contact) {
        this.contactNumber = contact;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.studentId;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.contactNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.studentId != other.studentId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.contactNumber, other.contactNumber);
    }

}
