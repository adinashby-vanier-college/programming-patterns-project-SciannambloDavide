/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author 2276744
 */
public class Library implements LibraryObservable {

    private List<LibraryObserver> observers = new ArrayList<>();
    private static Library libraryObject;
    private ArrayList<Book> books;

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
        notifyObservers();  //notify the observers when a new book is added

    }

    public void registerObserver(LibraryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LibraryObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (LibraryObserver observer : observers) {
            observer.update();  //this is responsible for updating the booksTextBox in the MainForm, using it's update() method
        }
    }

    public static Library getlObject() {
        return libraryObject;
    }

    public static void setlObject(Library lObject) {
        Library.libraryObject = lObject;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    private void insertBookIntoDatabase(Book book) {
        String insertQuery = "INSERT INTO Books (SN, Title, Author, Publisher, Price, Quantity, Issued, addedDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
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
        books.remove(book);
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

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
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

    public static Map<String, String> viewCatalog() throws SQLException {
        Map<String, String> catalog = new LinkedHashMap<>();
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT SN, Title FROM Books ORDER BY SN")) {

            while (rs.next()) {
                catalog.put(rs.getString("SN"), rs.getString("Title"));
            }
        }
        return catalog;
    }

    public static Map<String, String> viewIssuedBooks() throws SQLException {
        Map<String, String> issuedBooks = new LinkedHashMap<>();
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT SN, StudentId FROM IssuedBooks ORDER BY SN")) {

            while (rs.next()) {
                issuedBooks.put(rs.getString("SN"), rs.getString("StudentId"));
            }
        }
        return issuedBooks;
    }

    public static Book findBookBySerialNumber(String serialNumber) throws SQLException {
        Book foundBook = null;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books WHERE SN = ?")) {
            stmt.setString(1, serialNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int price = rs.getInt("Price");
                    int qte = rs.getInt("Quantity");
                    int issuedQte = rs.getInt("Issued");
                    String bookName = rs.getString("Title");
                    String author = rs.getString("Author");
                    String publisher = rs.getString("Publisher");
                    String dateOfPurchase = rs.getString("DateOfPurchase"); // Make sure this column name matches your database

                    foundBook = new Book(serialNumber, price, qte, issuedQte, bookName, author, publisher, dateOfPurchase);
                }
            }
        }
        return foundBook;
    }
    private void updateBookInDatabase(Book book) {
    Connection conn = null;
    PreparedStatement pstmt = null;
        try {
        String sql = "UPDATE books SET qte = ?, issuedQte = ? WHERE SN = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, book.getQte());
        pstmt.setInt(2, book.getIssuedQte());
        pstmt.setString(3, book.getSN());
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        // Handle exceptions
        ex.printStackTrace();
    } finally {
        // Close resources, if open
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

    public void borrowBook(String serialNumber) throws SQLException {
    Book book = findBookBySerialNumber(serialNumber);
    if (book != null && book.getQte() > 0) {
        book.setQte(book.getQte() - 1);
        book.setIssuedQte(book.getIssuedQte() + 1);
        updateBookInDatabase(book);
        notifyObservers();
    }
}

public void returnBook(String serialNumber) throws SQLException {
    Book book = findBookBySerialNumber(serialNumber);
    if (book != null && book.getIssuedQte() > 0) {
        book.setQte(book.getQte() + 1);
        book.setIssuedQte(book.getIssuedQte() - 1);
        updateBookInDatabase(book);
        notifyObservers();
    }
}


    public List<LibraryObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<LibraryObserver> observers) {
        this.observers = observers;
    }

    public static Library getLibraryObject() {
        return libraryObject;
    }

    public static void setLibraryObject(Library libraryObject) {
        Library.libraryObject = libraryObject;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Library other = (Library) obj;
        if (!Objects.equals(this.observers, other.observers)) {
            return false;
        }
        return Objects.equals(this.books, other.books);
    }

    @Override
    public String toString() {
        return books.toString();
    }

}
