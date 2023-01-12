package org.example;

import org.json.CDL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class CsvToJson {
    public static void createJSONBooks(){
        InputStream inputStream = CsvToJson.class.getClassLoader().getResourceAsStream("books.csv");
        String csvAsString = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        String json = CDL.toJSONArray(csvAsString).toString();
        try {
            Files.write(Path.of("src/main/resources/stock.json"), json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        try {
            Object userFile = jsonParser.parse(new FileReader("src/main/resources/stock.json"));
            JSONArray jsonArray = (JSONArray) userFile;

            for (int i=0; i < jsonArray.size(); i++){
                JSONObject bookObj = (JSONObject) jsonArray.get(i);
                bookObj.put("AmountLoaned",0);
            }

            FileWriter file = new FileWriter("src/main/resources/stock.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CsvToJson.createJSONBooks();
    }

}
