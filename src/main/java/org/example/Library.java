package org.example;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Library {
    LibraryDatabase db = new LibraryDatabase();
    Scanner scanner = new Scanner(System.in);
    public String newRow;
    User user = new User();
    private static List<User> allUsers;
    private static List<Book> allBooks;

    public void allBooks() {
        db.readJson();
    }


    public void loginRegister() throws IOException, CsvException {
        System.out.println("Would you like to login or register? \n(1) Login\n(2) Register");
        String option = scanner.next();
        if (option.equals("1") || option.equalsIgnoreCase("login")) {
            loginUser();
        } else if (option.equals("2") || option.equalsIgnoreCase("register")) {
            registerUser();
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
                loanABook();
                mainMenu();
            case "4":
                System.out.println(allUsers.toString().replace(",", "").replace("[", "").replace("]", "").trim());
                mainMenu();
            default:
                System.out.println("Didn't understand input, try again");
                mainMenu();
                break;
        }
    }

    public void registerUser() throws IOException, CsvException {
        System.out.println("To register, first please enter your preferred username");
        String userName = scanner.next();
        System.out.println("please enter your password");
        String userPassword = scanner.next();
        System.out.println("Would you like to be admin? true/false");
        String adminStatus = scanner.next();
        User newUser = new User(2, userName, userPassword, adminStatus);
        System.out.println("Thank you for registering " + newUser.getName());
        newUser.writeToJsonUser(userName, userPassword, adminStatus);
    }

    public void loginUser() {
        System.out.println("Please enter your ID");
        String userID = scanner.next();
        allUsers = User.getUser();
        for (User u : allUsers) {
            if (u.getID() == Integer.parseInt(userID)) {
                System.out.println(u);
                System.out.println("Please enter your username and Password");
                String userName = scanner.next();
                String userPassword = scanner.next();
                if (userName.equalsIgnoreCase(u.getName()) && userPassword.equalsIgnoreCase(u.getPassword())) {
                    System.out.println("Hello " + u.getName());
                    user = new User(u.getID(), u.getName(), u.getPassword(), u.getAdmin());
                    mainMenu();
                } else System.out.println("Incorrect details, please try again");
                loginUser();
            } else System.out.println("incorrect ID");
            loginUser();

        }

    }


    public void loanABook() {
        System.out.println("Please select which book you would like to take out by entering the ID, you can only take out one book at a time");
        allBooks();
        allBooks = LibraryDatabase.readJson();
        String rent = scanner.next();
        for (Book b : allBooks) {
            if (Integer.parseInt(rent) == b.getNumber()) {
                System.out.println(b.getTitle());
                user.addBookToUser(user.getID(), b.toString(), user.getName());
            }
        }
    }

}
