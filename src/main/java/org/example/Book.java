package org.example;

public class Book {
    private int Number;
    private String Title;


    private String Author;
    private String Genre;
    private String SubGenre;
    private String Publisher;
    private boolean onLoan = false;
    private int amountLoaned = 0;


    public Book(int Number, String Title, String Author, String Genre, String SubGenre, String Publisher) {
        this.Number = Number;
        this.Title = Title;
        this.Author = Author;
        this.Genre = Genre;
        this.SubGenre = SubGenre;
        this.Publisher = Publisher;
    }


    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getSubGenre() {
        return SubGenre;
    }

    public void setSubGenre(String subGenre) {
        SubGenre = subGenre;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }
    public boolean isOnLoan() {
        return onLoan;
    }

    public void setOnLoan(boolean onLoan) {
        this.onLoan = onLoan;
    }

    public int getAmountLoaned() {
        return amountLoaned;
    }

    public void setAmountLoaned(int amountLoaned) {
        this.amountLoaned = amountLoaned;
    }

    public void addAmountLoaned() {
        amountLoaned ++;
    }
    @Override
    public String toString() {
        return "ID: " + Number + " Title: "+ Title+ " \n";
    }
}
