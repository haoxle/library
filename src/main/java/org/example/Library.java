package org.example;

import java.util.Scanner;

public class Library {
    LibraryDatabase db = new LibraryDatabase();
    Scanner scanner = new Scanner(System.in);


    public void allBooks(){
         db.readJson();
     }
    public void mainMenu() {
        System.out.println("What would you like to do today?");
        System.out.println("(0) Exit\n(1) See All Books");
        String choice = scanner.next();
        switch (choice){
            case "1":
                allBooks();
            case "0":
                System.exit(0);
            default:
                System.out.println("Didn't understand input, try again");
                mainMenu();
                break;
        }
    }
}
