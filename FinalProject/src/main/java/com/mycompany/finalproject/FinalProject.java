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

        try {
            DatabaseConnection.initialize();

            Library library = Library.getInstance();
            Book book1 = new Book("Tester", 23, 1, 13, "Title1", "Davide Sciannamblo", "Marc Anthony", "Today");

            DatabaseConnection.loadBooks(library);

            decisionFrame decisionFrame = new decisionFrame();

            decisionFrame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace(); // This will print any exceptions that occur
        }
    }

}
