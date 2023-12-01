/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

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
