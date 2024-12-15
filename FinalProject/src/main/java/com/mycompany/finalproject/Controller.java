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
public class Controller {

    private ArrayList<LibraryController> libraryControllers;
    private decisionFrame mainFormView;
    private static Controller controllerObject;

    private Controller() {

    }

    private Controller(ArrayList<LibraryController> libraryControllers, decisionFrame mainFormView) {
        this.libraryControllers = libraryControllers;
        this.mainFormView = mainFormView;

    }

    public static Controller getInstance() {
        // create object if it's not already created
        if (controllerObject == null) {
            controllerObject = new Controller();
        }

        // returns the singleton object
        return controllerObject;
    }

    public static Controller getInstance(ArrayList<LibraryController> libraryControllers, decisionFrame mainFormView) {
        // create object if it's not already created
        if (controllerObject == null) {
            controllerObject = new Controller(libraryControllers, mainFormView);
        }

        // returns the singleton object
        return controllerObject;
    }

    public void setLibrarysControllers(ArrayList<LibraryController> libraryControllers) {
        this.libraryControllers = libraryControllers;
    }

    public ArrayList<LibraryController> getLibrarysControllers() {
        return libraryControllers;
    }

    public void setMainFormView(decisionFrame mainFormView) {
        this.mainFormView = mainFormView;
    }

}
