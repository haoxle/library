package org.example;

import java.util.Scanner;

public class Library {
    LibraryDatabase db = new LibraryDatabase();
    Scanner scanner = new Scanner(System.in);
    User hao = new User(1,"hao", "haole", true, null);

    public void allBooks(){
         db.readJson();
     }

     public void loginRegister() {
         System.out.println("Would you like to login or register? \n(1) Login\n(2) Register");
         String option = scanner.next();
         if(option.equals("1") || option.equalsIgnoreCase("login")){
            loginUser();
         } else if (option.equals("2") || option.equalsIgnoreCase("register")) {
             registerUser();
             mainMenu();
         } else System.out.println("please enter one of the two options");
     }

    public void mainMenu() {
        System.out.println("What would you like to do today?");
        System.out.println("(0) Exit\n(1) See All Books\n(2) Loan a book");
        String choice = scanner.next();
        switch (choice){
            case "0":
                System.exit(0);
            case "1":
                allBooks();
            case "2":
                loanABook();
                mainMenu();
            default:
                System.out.println("Didn't understand input, try again");
                mainMenu();
                break;
        }
    }

    public void registerUser() {
        System.out.println("To register, first please enter your preferred username");
        String userName = scanner.next();
        System.out.println("please enter your password");
        String userPassword = scanner.next();
        User newUser = new User(2, userName, userPassword, false, null);
        System.out.println("Thank you for registering " + newUser.getName());
    }

    public void loginUser() {
        System.out.println("Please enter your username and password");
        String userLogin = scanner.next();
        String userPassword = scanner.next();
        if(userLogin.equalsIgnoreCase(hao.getName()) && userPassword.equalsIgnoreCase(hao.getPassword())){
            System.out.println("Hello "+hao.getName());
            mainMenu();
        } else System.out.println("Incorrect details, please try again");
        loginUser();
    }

    public void loanABook() {
        System.out.println("Please select which book you would like to take out by entering the ID, you can only take out one book at a time");
        allBooks();
        String rent = scanner.next();
    }

}
