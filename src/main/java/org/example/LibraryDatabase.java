package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.json.CDL;


import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibraryDatabase {
    private static List<Book> allBooks;
    private static List<User> allUsers;

    Scanner scanner = new Scanner(System.in);

    public void CsvToJson() {
        InputStream inputStream = CsvToJson.class.getClassLoader().getResourceAsStream("books.csv");
        assert inputStream != null;
        String csvAsString = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        String json = CDL.toJSONArray(csvAsString).toString();
        try {
            Files.write(Path.of("src/main/resources/stock.json"), json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //should move this to users
    public static List<Book> readJson() {
        Type listType = new TypeToken<List<Book>>() {
        }.getType();
        try {
            List<Book> books = new Gson().fromJson(new FileReader("src/main/resources/stock.json"), listType);
            System.out.println(books.toString().replace(",", ""));
            allBooks = books;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allBooks;
    }



}