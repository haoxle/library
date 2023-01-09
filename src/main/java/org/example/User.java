package org.example;

import java.util.List;

public class User {

    private int id;
    private String name;
    private String password;


    private Book booksOnLoan;
    private List <Book> listBooks;
    private boolean admin;
    private List <User> allUsers;


    public User(int id, String name, String password, boolean admin, Book booksOnLoan) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.admin = admin;
        this.booksOnLoan = booksOnLoan;
    }


    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Book getBooksOnLoan() {
        return booksOnLoan;
    }

    public void setBooksOnLoan(Book booksOnLoan) {
        this.booksOnLoan = booksOnLoan;
    }

    public List<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<Book> listBooks) {
        this.listBooks = listBooks;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
    @Override
    public String toString() {
        return  id + ": name: " + name + " admin:" + admin + " \n";
    }


}
