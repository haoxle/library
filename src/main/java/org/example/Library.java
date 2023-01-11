package org.example;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    LibraryDatabase db = new LibraryDatabase();
    Scanner scanner = new Scanner(System.in);
    User user = new User();
    private static userManager um = new userManager();
    private static Loan loan = new Loan();

    public void allBooks() {
         db.readJson();

    }

    public void loginRegister() throws IOException, CsvException {
        System.out.println("Would you like to login or register? \n(1) Login\n(2) Register");
        String option = scanner.next();
        if (option.equals("1") || option.equalsIgnoreCase("login")) {
            um.loginUser();
            mainMenu();
        } else if (option.equals("2") || option.equalsIgnoreCase("register")) {
           um.addNewUser();
           mainMenu();
        } else System.out.println("please enter one of the two options");
    }

    public void mainMenu() {
        System.out.println("What would you like to do today?");
        System.out.println("(0) Exit\n(1) See All Books\n(2) Loan a book\n(3) Return a book\n(4) See Users");
        String choice = scanner.next();
        switch (choice) {
            case "0":
                System.exit(0);
            case "1":
                allBooks();
                mainMenu();
            case "2":
                loan.loanBook(um.currentUser);
                mainMenu();
            case "4":
                System.out.println(um.getUser().toString().replace(",", "").replace("[", "").replace("]", "").trim());
                mainMenu();
            default:
                System.out.println("Didn't understand input, try again");
                mainMenu();
                break;
        }
    }




}
