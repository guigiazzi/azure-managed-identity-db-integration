package com.example.demo.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

// import org.springframework.stereotype.Service;

import com.example.demo.DemoApplication;
// import com.google.gson.Gson;

// @Service
public class PetStoreServicePostgres {

    public List<Pet> getPets(){
        Properties properties = new Properties();
        try {
            properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Getting pets with managed identity " + properties.getProperty("user"));
        
        Connection connection;
        try {
            // System.out.println("SELECT - Connecting to the database");
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);

            PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM PetStore;");
            ResultSet resultSet = readStatement.executeQuery();

            List<Pet> result = new ArrayList<>();
            while (resultSet.next()) {
                Pet pet = new Pet(String.valueOf(resultSet.getInt("petid")),
                    resultSet.getString("petname"),
                    resultSet.getString("pettype"),
                    String.valueOf(resultSet.getInt("age")),
                    String.valueOf(resultSet.getInt("price")));

                result.add(pet);
            }

            System.out.println("Query result is: ");
            for (Pet pet : result) {
                System.out.print("{");
                for (Field field : pet.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    System.out.print("\"" + field.getName() + "\":\"" + field.get(pet) + "\", ");            
                }
                System.out.print("},");
            }

            System.out.println("");
            return result;

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String addPet(Pet pet) throws IllegalArgumentException, IllegalAccessException{
        Properties properties = new Properties();
        try {
            properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Adding pet with managed identity " + properties.getProperty("user"));
        System.out.print("{");
        for (Field field : pet.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            System.out.print("\"" + field.getName() + "\":\"" + field.get(pet) + "\", ");            
        }
        System.out.print("}\n");

        Connection connection;
        try {
            // System.out.println("INSERT - Connecting to the database");
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);

            PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO PetStore (petid, petname, pettype, age, price) VALUES (?, ?, ?, ?, ?);");

                insertStatement.setLong(1, Long.parseLong(pet.getId()));
                insertStatement.setString(2, pet.getName());
                insertStatement.setString(3, pet.getType());
                insertStatement.setInt(4, Integer.parseInt(pet.getAge()));
                insertStatement.setInt(5, Integer.parseInt(pet.getPrice()));
                insertStatement.executeUpdate();
            
            System.out.println("Inserted successfully");
                
            return "Inserted successfully";
        } catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
