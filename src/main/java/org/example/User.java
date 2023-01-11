package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class User {

    private int id;
    private String username;
    private String password;
    private ArrayList<String> booksOnLoan;
    private boolean admin;


    public User(int id, String username, String password, Boolean admin, ArrayList<String> booksOnLoan) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.booksOnLoan = booksOnLoan;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getBooksOnLoan() {
        return booksOnLoan;
    }

    public void setBooksOnLoan(ArrayList<String> booksOnLoan) {
        this.booksOnLoan = booksOnLoan;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void returnBook(String bookNumber){
        this.booksOnLoan.remove(bookNumber);
    }

    public void addBook(String bookNumber){
        this.booksOnLoan.add(bookNumber);
    }

    @Override
    public String toString() {
        return "ID: " + id + " Name: " + username.toUpperCase() + " Admin: " + admin + "\n";
    }

}


