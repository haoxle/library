package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Loan {
    static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> loanedBooks = new ArrayList<>();

    public static void loanBook(User currentUser) {
        System.out.println("Please enter the number of the book you wish to loan");
        String bookNumber = scanner.nextLine();
        for (int i = 0; i < loanedBooks.size(); i++) {
            if (!(loanedBooks.get(i).contains(bookNumber))) {
                updateUserBooks(currentUser, bookNumber);
                updateLoanTimes(bookNumber);
                System.out.println("Loaned out");
            }  else System.out.println("The book is currently on loan, please choose another");
            break;
        }
    }


    public static void updateUserBooks(User user, String bookNumber) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object userFile = jsonParser.parse(new FileReader("src/main/resources/users.json"));
            JSONArray userJsonArray = (JSONArray) userFile;
            for (int i = 0; i < userJsonArray.size(); i++) {
                JSONObject userObj = (JSONObject) userJsonArray.get(i);
                if (userObj.get("username").equals(user.getUsername())) {
                    ArrayList<String> userBooks = (ArrayList<String>) userObj.get("booksLoanedOut");
                    userBooks.add(bookNumber);
                    userObj.put("booksLoanedOut", userBooks);
                    userJsonArray.set(i, userObj);
                }
            }
            FileWriter file = new FileWriter("src/main/resources/users.json");
            file.write(userJsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static void updateLoanTimes(String bookNumber) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object bookFile = jsonParser.parse(new FileReader("src/main/resources/stock_data.json"));
            JSONArray bookJsonArray = (JSONArray) bookFile;
            for (int i = 0; i < bookJsonArray.size(); i++) {
                JSONObject bookObj = (JSONObject) bookJsonArray.get(i);
                if (bookObj.get("Number").equals(bookNumber)) {
                    long AmountLoaned = (long) bookObj.get("AmountLoaned");
                    AmountLoaned++;
                    bookObj.put("AmountLoaned", AmountLoaned);
                    bookJsonArray.set(i, bookObj);
                }
            }
            FileWriter file = new FileWriter("src/main/resources/stock_data.json");
            file.write(bookJsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void checkLoanedBooks() {
        JSONParser jsonParser = new JSONParser();
        try {
            Object bookFile = jsonParser.parse(new FileReader("src/main/resources/users.json"));
            JSONArray bookJsonArray = (JSONArray) bookFile;
            for (int i = 0; i < bookJsonArray.size(); i++) {
                JSONObject bookObj = (JSONObject) bookJsonArray.get(i);
                loanedBooks.add(bookObj.get("booksLoanedOut").toString());
            }
            FileWriter file = new FileWriter("src/main/resources/users.json");
            file.write(bookJsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public static void printLoanedBooks() {
        for (int i = 0; i < loanedBooks.size(); i++)
            if(loanedBooks.get(i).isEmpty()) {
                loanedBooks.remove(i);
            }
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        for (int i = 0; i < loanedBooks.size(); i++)
            set.add(loanedBooks.get(i));
        System.out.println( "Books currently on loan: " + set.toString().replace("[", "").replace("]", "").replace(" ", "").replace("\"", ""));
    };

}
