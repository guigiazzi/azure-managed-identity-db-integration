package com.example.demo.readwrite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Pet;
import com.example.demo.service.PetStoreService;

@RestController()
public class ReadWritePermissions {

    @Autowired
    PetStoreService petStoreService;

    String managedIdentityReadWrite = "1e268eda-b202-46af-9ae8-75a686630fb5";

    @GetMapping("/read-write/pets")
    public List<Pet> getPets(){
        return petStoreService.getPets(managedIdentityReadWrite);
    }

    @PostMapping("/read-write/pets")
    public String getPets(@RequestBody Pet pet){
        return petStoreService.addPet(managedIdentityReadWrite, pet);
    }

}
