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
        // try {
        //     this.setEnvVars();
            // this.getAllEnvVars();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        Properties properties = new Properties();
        try {
            properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Now Getting pets with managed identity " + properties.getProperty("user"));
        
        Connection connection;
        try {
            System.out.println("!! Connecting to the database");
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

            System.out.println("Query result is: ");
            for (Pet pet : result) {
                System.out.print("{");
                for (Field field : pet.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    System.out.print("\"" + field.getName() + "\":\"" + field.get(pet) + "\", ");            
                }
                System.out.print("},");
            }

            return result;

        } catch(Exception e){
            e.printStackTrace();
            // System.out.println("Closing database connection");
            // connection.close();
            return null;
        }
    }

    public String addPet(Pet pet){
        // try {
        //     this.setEnvVars();
            // this.getAllEnvVars();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        Properties properties = new Properties();
        try {
            properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));
            System.out.println("Adding pet with managed identity " + properties.getProperty("user"));
            System.out.print("{");
            for (Field field : pet.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                System.out.print("\"" + field.getName() + "\":\"" + field.get(pet) + "\", ");            
            }
            System.out.print("}\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // System.out.println("Adding pet " + new Gson().toJson(pet) + " with managed identity " + properties.getProperty("user"));


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

    // private void setEnvVars() throws Exception{
    //     // ModuleLayer layer = ModuleLayer.boot();
    //     // ModuleLayer.Controller controller = layer.controller();
    //     // Module sourceModule = ModuleLayer.boot().findModule("source.module").orElseThrow();
    //     // Module targetModule = ModuleLayer.boot().findModule("target.module").orElseThrow();
        
    //     // controller.addOpens(sourceModule, "package.name", targetModule);
        
        
    //     Map<String, String> map = new HashMap<String, String>();

    //     map.put("AZ_RESOURCE_GROUP", "rg-test");
    //     map.put("AZ_DATABASE_SERVER_NAME", "testegiazzi2");
    //     map.put("AZ_DATABASE_NAME", "db-test");
    //     map.put("AZ_LOCATION", "brsouth");
    //     map.put("AZ_POSTGRESQL_AD_NON_ADMIN_USERNAME", "mi-write-credentials");

    //     this.setEnv(map);
    // }

    //     private void setEnv(Map<String, String> newenv) throws Exception {
    //         try {
    //           Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
    //           Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
    //           theEnvironmentField.setAccessible(true);
    //           Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
    //           env.putAll(newenv);
    //           Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
    //           theCaseInsensitiveEnvironmentField.setAccessible(true);
    //           Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
    //           cienv.putAll(newenv);
    //         } catch (NoSuchFieldException e) {
    //           Class[] classes = Collections.class.getDeclaredClasses();
    //           Map<String, String> env = System.getenv();
    //           for(Class cl : classes) {
    //             if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
    //               Field field = cl.getDeclaredField("m");
    //               field.setAccessible(true);
    //               Object obj = field.get(env);
    //               Map<String, String> map = (Map<String, String>) obj;
    //               map.clear();
    //               map.putAll(newenv);
    //             }
    //           }
    //         }
    //       }
    

    private void getAllEnvVars(){
        System.out.println("Getting all env vars");
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n", envName, env.get(envName));
        }
    }

}
