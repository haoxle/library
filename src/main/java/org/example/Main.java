package org.example;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public class Main {
    static Library library = new Library();
    public static void main(String[] args) throws IOException, CsvException {
        library.loginRegister();
    }
}