/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finalproject;

import java.util.Random;

/**
 *
 * @author 2276744
 */
public class FinalProject {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Library library = new Library();
        Book book1 = new Book("Tester", 23, 1, 13, "Title1", "Davide Sciannamblo", "Marc Anthony", "Today");
        DatabaseConnection.initialize();
                //library.addBook(book1);
                DatabaseConnection.loadBooks(library);

    }
}
