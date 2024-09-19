package com.example.demo;

import com.example.demo.service.Pet;
import com.example.demo.service.PetStoreServicePostgres;

public class DemoApplication {

	public static void main(String[] args) {
		PetStoreServicePostgres service = new PetStoreServicePostgres();
		service.getPets();
		service.addPet(new Pet("20", "Bob", "Bird", "9", "800"));
		service.getPets();

	}

}
