// package com.example.demo.read;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.service.Pet;
// import com.example.demo.service.PetStoreServiceSQL;

// @RestController()
// public class ReadPermissions {

//     @Autowired
//     PetStoreServiceSQL petStoreService;

//     String managedIdentityRead = "ac401625-8f14-497f-b105-3f49ccb4dee1";

//     @GetMapping("/read/pets")
//     public List<Pet> getPets(){
//         return petStoreService.getPets(managedIdentityRead);
//     }

//     @PostMapping("/read/pets")
//     public String getPets(@RequestBody Pet pet){
//         return petStoreService.addPet(managedIdentityRead, pet);
//     }

// }
