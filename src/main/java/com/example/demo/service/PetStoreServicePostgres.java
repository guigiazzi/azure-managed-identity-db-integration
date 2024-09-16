package com.example.demo.service;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.DemoApplication;
import com.google.gson.Gson;

@Service
public class PetStoreServicePostgres {

    public List<Pet> getPets(String managedIdentity){
        System.out.println("Getting pets with managed identity " + System.getenv("AZ_POSTGRESQL_AD_NON_ADMIN_USERNAME"));

        Properties properties = new Properties();
        try {
            properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Connection connection;
        try {
            System.out.println("Connecting to the database");
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
            System.out.println("Database connection test: " + connection.getCatalog());

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

            System.out.println("Query result is: " + new Gson().toJson(result));

            return result;

        } catch(Exception e){
            e.printStackTrace();
            // System.out.println("Closing database connection");
            // connection.close();
            return null;
        }
    }

    public String addPet(String managedIdentity, Pet pet){
		System.out.println("Adding pet " + new Gson().toJson(pet) + " with managed identity " + managedIdentity);

        Properties properties = new Properties();
        try {
            properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection;
        try {
            System.out.println("Connecting to the database");
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
            System.out.println("Database connection test: " + connection.getCatalog());

            PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO PetStore (petid, petname, pettype, age, price) VALUES (?, ?, ?, ?);");
        
                insertStatement.setLong(1, Long.parseLong(pet.getId()));
                insertStatement.setString(2, pet.getName());
                insertStatement.setString(3, pet.getType());
                insertStatement.setInt(4, Integer.parseInt(pet.getAge()));
                insertStatement.setInt(4, Integer.parseInt(pet.getPrice()));
                insertStatement.executeUpdate();
            
            System.out.println("Inserted successfully");
                
            return "Inserted successfully";
        } catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
