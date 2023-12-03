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
public class Book {
    private int price, qte, issuedQte;
    String title, author, publisher, dateOfPurchase, SN;

    public Book(String SN, int price, int qte, int issuedQte, String title, String author, String publisher, String dateOfPurchase) {
        this.SN = SN;
        this.price = price;
        this.qte = qte;
        this.issuedQte = issuedQte;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public String toString() {
        return "Book{" + "price=" + price + ", qte=" + qte + ", issuedQte=" + issuedQte + ", title=" + title + ", author=" + author + ", publisher=" + publisher + ", dateOfPurchase=" + dateOfPurchase + ", SN=" + SN + '}';
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getIssuedQte() {
        return issuedQte;
    }

    public void setIssuedQte(int issuedQte) {
        this.issuedQte = issuedQte;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.price;
        hash = 89 * hash + this.qte;
        hash = 89 * hash + this.issuedQte;
        hash = 89 * hash + Objects.hashCode(this.title);
        hash = 89 * hash + Objects.hashCode(this.author);
        hash = 89 * hash + Objects.hashCode(this.publisher);
        hash = 89 * hash + Objects.hashCode(this.dateOfPurchase);
        hash = 89 * hash + Objects.hashCode(this.SN);
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
        final Book other = (Book) obj;
        if (this.price != other.price) {
            return false;
        }
        if (this.qte != other.qte) {
            return false;
        }
        if (this.issuedQte != other.issuedQte) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        if (!Objects.equals(this.dateOfPurchase, other.dateOfPurchase)) {
            return false;
        }
        return Objects.equals(this.SN, other.SN);
    }

    
}
