package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class userManager {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<User> users = new ArrayList<>();
    public User currentUser;
    private ArrayList<Book> books = new ArrayList<>();
    private static List<User> allUsers;


    public void addNewUser() {
        System.out.println("Please enter your preferred username");
        String username = scanner.nextLine();
        System.out.println("Please enter a password");
        String password = scanner.nextLine();
        System.out.println("Please confirm your password");
        String confirmPassword = scanner.nextLine();
        if (password.equals(confirmPassword)) {
            Boolean isAdmin = false;
            String isAdminInput = "";
            System.out.println("Admin? Y/N");
            isAdminInput = scanner.nextLine();
            if (isAdminInput.equalsIgnoreCase("y")) {
                isAdmin = true;
            }
            ArrayList<String> userObject = new ArrayList<>();
            JSONObject obj = new JSONObject();
            obj.put("id", users.size() + 1);
            obj.put("username", username);
            obj.put("password", password);
            obj.put("isAdmin", isAdmin);
            obj.put("booksLoanedOut", userObject);

            JSONParser jsonParser = new JSONParser();

            try {
                Object userFile = jsonParser.parse(new FileReader("src/main/resources/users.json"));
                JSONArray jsonArray = (JSONArray) userFile;
                jsonArray.add(obj);
                FileWriter file = new FileWriter("src/main/resources/users.json");
                file.write(jsonArray.toJSONString());
                file.flush();
                file.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else System.out.println("password does not match, try again");
    }
    public void loginUser() {
        System.out.println("Please enter your ID");
        String userID = scanner.next();
        allUsers = getUser();
        for (User u : allUsers) {
            if (u.getId() == Integer.parseInt(userID)) {
                System.out.println(u);
                System.out.println("Please enter your username and Password");
                String userName = scanner.next();
                String userPassword = scanner.next();
                if (userName.equalsIgnoreCase(u.getUsername()) && userPassword.equalsIgnoreCase(u.getPassword())) {
                    System.out.println("Hello " + u.getUsername());
                    currentUser = u;
                    break;
                } else System.out.println("Incorrect details, please try again");
                loginUser();
            } else System.out.println("incorrect ID");
            loginUser();
        }
    }
    public static List<User> getUser() {
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        try {
            List<User> users = new Gson().fromJson(new FileReader("src/main/resources/users.json"), listType);
            allUsers = users;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }
    public void updateCurrentUser(){
        for (User user:users) {
            if(currentUser.getId()== user.getId()){
                currentUser = user;
            }
        }
    }

}
