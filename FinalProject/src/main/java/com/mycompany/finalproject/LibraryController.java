/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Marc-Anthony
 */
public class LibraryController {

    private Library model;
    private decisionFrame view;
    private studentFrame view2;
    private librarianFrame view3;

    public LibraryController(Library model, decisionFrame view) {
        this.model = model;
        this.view = view;
    }

    //control model object
    public void setBookTitle(String name, int index) {
        model.getBooks().get(index).setTitle(name);
    }

    public String getBookTitle(int index) {
        return model.getBooks().get(index).getTitle();
    }

    public void setAuthorName(String author, int index) {
        model.getBooks().get(index).setAuthor(author);
    }

    public String getAuthorName(int index) {
        return model.getBooks().get(index).getAuthor();
    }

    public void setBookQuantity(int stock, int index) {
        model.getBooks().get(index).setQte(stock);
    }

    public int getBookQuantity(int index) {
        return model.getBooks().get(index).getQte();
    }

    public void setBookSN(String sn, int index) {
        model.getBooks().get(index).setSN(sn);
    }

    public String getBookSN(int index) {
        return model.getBooks().get(index).getSN();
    }

    public void setBookPrice(int price, int index) {
        model.getBooks().get(index).setPrice(price);
    }

    public int getBookPrice(int index) {
        return model.getBooks().get(index).getPrice();
    }

    public void setBookIssuedQuantity(int issuedQte, int index) {
        model.getBooks().get(index).setIssuedQte(issuedQte);
    }

    public int getBookIssuedQuantity(int index) {
        return model.getBooks().get(index).getIssuedQte();
    }

    public void setBookPublisher(String publisher, int index) {
        model.getBooks().get(index).setPublisher(publisher);
    }

    public String getBookPublisher(int index) {
        return model.getBooks().get(index).getPublisher();
    }

    public void setBookDateOfPurchase(String date, int index) {
        model.getBooks().get(index).setDateOfPurchase(date);
    }

    public String getBookDateOfPurchase(int index) {
        return model.getBooks().get(index).getDateOfPurchase();
    }

    public Library getLibraryModel() {
        return model;
    }

    public void setLibraryModel(Library model) {
        this.model = model;
    }

    public void setLibraryBooks(ArrayList<Book> books) {
        model.setBooks(books);
    }

    public ArrayList<Book> getLibraryBooks() {
        return model.getBooks();
    }

    //control view object
    public void updateView() {
        updateBookTextArea(this);
    }

    public void setView(decisionFrame view) {
        this.view = view;
    }

    public void addBook(String SN, int price, int qte, int issuedQte, String title, String author, String publisher, String dateOfPurchase) {
        Book newBook = new Book(SN, price, qte, issuedQte, title, author, publisher, dateOfPurchase);
        model.addBook(newBook);

    }

    public void updateBookTextArea(LibraryController libraryController) {

        StringBuilder booksDisplay = new StringBuilder();

        for (Book book : libraryController.getLibraryBooks()) {
            booksDisplay.append(book.toString());
        }
        view2.booksTextArea.setText(booksDisplay.toString());
        view3.booksTextArea.setText(booksDisplay.toString());

    }
}
