// package com.example.demo.service;

// import java.util.List;
// import java.util.ArrayList;

// import org.springframework.stereotype.Service;

// import java.sql.Connection;
// import java.sql.ResultSet;
// import java.sql.Statement;

// import java.sql.SQLException;

// import com.google.gson.Gson;
// import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

// @Service
// public class PetStoreServiceSQL {

// 	public List<Pet> getPets(String managedIdentity){
// 		System.out.println("Getting pets with managed identity " + managedIdentity);

// 		SQLServerDataSource ds = new SQLServerDataSource();
// 		ds.setURL(this.getConnectionString(managedIdentity));

//         try (Connection connection = ds.getConnection(); Statement stmt = connection.createStatement();) {
// 			System.out.println("Connected successfully");

// 			String SQLCommand = "SELECT * FROM dbo.PetStore;";
//             ResultSet rs = stmt.executeQuery(SQLCommand);

// 			List<Pet> result = new ArrayList<>();
//             while (rs.next()) {
//                 result.add(new Pet(rs.getString("PetID"), rs.getString("PetName"), rs.getString("PetType"), rs.getString("Age"), rs.getString("Price")));
//             }

// 			System.out.println("Query result is: " + new Gson().toJson(result));
			
//             return result;
// 		} catch (SQLException e) {
// 			e.printStackTrace();
// 			return null;
// 		}	
// 	}

// 	public String addPet(String managedIdentity, Pet pet){
// 		System.out.println("Adding pet " + new Gson().toJson(pet) + " with managed identity " + managedIdentity);

// 		SQLServerDataSource ds = new SQLServerDataSource();
// 		ds.setURL(this.getConnectionString(managedIdentity));

// 		try (Connection connection = ds.getConnection(); Statement stmt = connection.createStatement();) {
// 			System.out.println("Connected successfully");

// 			String name = pet.getName();
//             String type = pet.getType();
//             int age = Integer.parseInt(pet.getAge());
//             double price = Double.parseDouble(pet.getPrice());

// 			String SQLCommand = "INSERT INTO dbo.PetStore (PetName, PetType, Age, Price) VALUES ('" + name + "', '"+ type + "', " + age + ", " + price + ");";
//             stmt.execute(SQLCommand);

// 			System.out.println("Inserted successfully");

//             return "Inserted successfully";
// 		} catch (SQLException e) {
// 			e.printStackTrace();
// 			return e.getMessage();
// 		}	
// 	}

//     private String getConnectionString(String managedIdentity){
//         return "jdbc:sqlserver://testegiazzi.database.windows.net:1433;databaseName=db-test;msiClientId=" + managedIdentity + ";authentication=ActiveDirectoryMSI;";
//     }
// }
