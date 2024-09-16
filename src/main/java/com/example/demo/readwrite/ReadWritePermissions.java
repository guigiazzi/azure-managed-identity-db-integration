package com.example.demo.readwrite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Pet;
import com.example.demo.service.PetStoreServicePostgres;
import com.example.demo.service.PetStoreServiceSQL;

@RestController()
public class ReadWritePermissions {

    @Autowired
    PetStoreServiceSQL petStoreServiceSQL;

    @Autowired
    PetStoreServicePostgres petStoreServicePostgres;

    String managedIdentityReadWrite = "1e268eda-b202-46af-9ae8-75a686630fb5";

    @GetMapping("/sql/read-write/pets")
    public List<Pet> getPetsSQL(){
        return petStoreServiceSQL.getPets(managedIdentityReadWrite);
    }

    @PostMapping("/sql/read-write/pets")
    public String getPetsSQL(@RequestBody Pet pet){
        return petStoreServiceSQL.addPet(managedIdentityReadWrite, pet);
    }

    @GetMapping("/postgres/read-write/pets")
    public List<Pet> getPetsPostgres(){
        return petStoreServicePostgres.getPets(managedIdentityReadWrite);
    }

    @PostMapping("/postgres/read-write/pets")
    public String getPetsPostgres(@RequestBody Pet pet){
        return petStoreServicePostgres.addPet(managedIdentityReadWrite, pet);
    }

}
