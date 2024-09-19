package com.example.demo;

import com.example.demo.service.Pet;
import com.example.demo.service.PetStoreServicePostgres;

public class DemoApplication {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		PetStoreServicePostgres service = new PetStoreServicePostgres();
		service.getPets();
		service.addPet(new Pet("23", "Fred", "Bird", "5", "200"));
		service.getPets();

	}

}
