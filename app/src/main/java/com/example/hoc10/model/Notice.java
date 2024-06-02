package com.example.hoc10.model;

import java.io.Serializable;

public class Notice implements Serializable {
    private int id;
    private String title,category,price,date,status;

    public Notice() {
    }

    public Notice(int id, String title, String category, String price, String date,String status) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.date = date;
        this.status=status;
    }

    public Notice(String title, String category, String price, String date,String status) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.date = date;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


