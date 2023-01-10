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


public class User {

    private int ID;
    private String name;
    private String password;


    private Book booksOnLoan;
    private List <Book> listBooks;



    public static List<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(List<User> allUsers) {
        User.allUsers = allUsers;
    }

    private String admin;
    private static List <User> allUsers;


    public User(int id, String name, String password, String admin) {
        this.ID = id;
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public User() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public List<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<Book> listBooks) {
        this.listBooks = listBooks;
    }

    @Override
    public String toString() {
        return   "ID: " +ID  + " Name: " + name.toUpperCase() + " Admin: " + admin + " \n";
    }



    public void writeToJsonUser(String name, String password, String admin) {
        ArrayList <Book> borrowedBook = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID", getUser().size() + 1);
        jsonObject.put("name", name);
        jsonObject.put("password", password);
        jsonObject.put("admin", admin);
        jsonObject.put("onLoan", borrowedBook);


        JSONParser jsonParser = new JSONParser();
        try {
            Object userFile = jsonParser.parse(new FileReader("src/main/resources/users.json"));
            JSONArray jsonArray = (JSONArray) userFile;
            jsonArray.add(jsonObject);
            FileWriter file = new FileWriter("src/main/resources/users.json");
            file.write(jsonArray.toJSONString());
            file.close();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("JSON file created: "+jsonObject);
    }

    public static List<User> getUser() {
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        try {
            List<User> users = new Gson().fromJson(new FileReader("src/main/resources/users.json"), listType);
            System.out.println(users.toString().replace(",", ""));
            allUsers = users;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }
    }


