package com.azure.cosmos.samples.mongodbspring;

import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.identity.ManagedIdentityCredential;
import com.azure.identity.ManagedIdentityCredentialBuilder;
import com.google.gson.Gson;
import com.azure.core.credential.TokenCredential;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.util.CosmosPagedIterable;

@SpringBootApplication
@RestController
// public class ConsoleApplication implements CommandLineRunner {
	public class ConsoleApplication {

	public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
	}

	private static Logger log = LoggerFactory.getLogger(ConsoleApplication.class);
	
	// @Autowired
	// private ProductRepository repository;

	// @Override
	// public void run(String... args) {

	// 	DefaultAzureCredential credential = new DefaultAzureCredentialBuilder()
	// 		.build();
	// 	// String endpoint = System.getenv("COSMOS_ENDPOINT");

	// 	log.info("Connecting to database");

	// 	CosmosClient client = new CosmosClientBuilder()
	// 		.endpoint("https://testegiazzi.mongo.cosmos.azure.com:443/")
	// 		// .key("mNPdMjduDcFLCONuMXiEpwMl1wbfwKgKmTN5YYrkFGCU8abmpeRIl137plIbNBU0YIDWqXpP6IdKACDbVcRd3A==")
	// 		.credential(credential)
	// 		// .consistencyLevel(ConsistencyLevel.EVENTUAL)
	// 		// .preferredRegions(Collections.singletonList("Central US"))
	// 		// .authorizationTokenResolver(null)
	// 		.buildClient();

	// 	log.info("Connected successfully");

	// 	CosmosDatabase database = client.getDatabase("db-test");
	// 	CosmosContainer container = database.getContainer("product");
	
	// 	log.info("Create document");
	// 	log.info("--------------------------------");
	// 	Product product = new Product(
	// 		"some3",
	// 		"some3",
	// 		8,
	// 		true
	// 	);

	// 	// CosmosItemRequestOptions cosmosItemRequestOptions = new CosmosItemRequestOptions();
    //     // CosmosItemResponse<Product> item = container.createItem(product, new PartitionKey(family.getLastName()), cosmosItemRequestOptions);
    //     container.createItem(product);

	// 	// repository.save(product);

	// 	log.info("Item created successfully");
		
	// 	log.info("Query all documents");	
	// 	log.info("--------------------------------");
	// 	CosmosPagedIterable<Product> response = container.readAllItems(new PartitionKey(""), Product.class);
		
	// 	response.forEach(item -> {
	// 		log.info(new Gson().toJson(item));
	// 	});

	// 	// for (Product a : repository.findByCategory("gear-surf-surfboards")) {
	// 	// 	log.info(a.toString());
	// 	// }
		
	// }

	@GetMapping("/products")
	public void getAll(){
		log.info("Getting credentials now");

		// DefaultAzureCredential credential = new DefaultAzureCredentialBuilder()
		// 	.managedIdentityClientId("1e268eda-b202-46af-9ae8-75a686630fb5")
		// 	.build();

		TokenCredential credential = new ManagedIdentityCredentialBuilder()
			.clientId("1e268eda-b202-46af-9ae8-75a686630fb5")
			.build();

		log.info("Credential acquired");

		// String endpoint = System.getenv("COSMOS_ENDPOINT");
	
		log.info("\n\n\n!GET! - Connecting to database");
	
		CosmosClient client = new CosmosClientBuilder()
			.endpoint("https://testegiazzi.mongo.cosmos.azure.com:443/")
			// .key("mNPdMjduDcFLCONuMXiEpwMl1wbfwKgKmTN5YYrkFGCU8abmpeRIl137plIbNBU0YIDWqXpP6IdKACDbVcRd3A==")
			.credential(credential)
			// .consistencyLevel(ConsistencyLevel.EVENTUAL)
			// .preferredRegions(Collections.singletonList("Central US"))
			// .authorizationTokenResolver(null)
			
			.buildClient();
	
		log.info("Connected successfully");
	
		CosmosDatabase database = client.getDatabase("db-test");
		CosmosContainer container = database.getContainer("product");

		log.info("Query all documents");	
		log.info("--------------------------------");
		CosmosPagedIterable<Product> response = container.readAllItems(new PartitionKey(""), Product.class);
		
		response.forEach(item -> {
			log.info(new Gson().toJson(item));
		});
	}

	@PostMapping("/products")
	public void insert(){
		DefaultAzureCredential credential = new DefaultAzureCredentialBuilder()
			.build();
		// String endpoint = System.getenv("COSMOS_ENDPOINT");
	
		log.info("\n\n\n!INSERT! - Connecting to database");
	
		CosmosClient client = new CosmosClientBuilder()
			.endpoint("https://testegiazzi.mongo.cosmos.azure.com:443/")
			// .key("mNPdMjduDcFLCONuMXiEpwMl1wbfwKgKmTN5YYrkFGCU8abmpeRIl137plIbNBU0YIDWqXpP6IdKACDbVcRd3A==")
			.credential(credential)
			// .consistencyLevel(ConsistencyLevel.EVENTUAL)
			// .preferredRegions(Collections.singletonList("Central US"))
			// .authorizationTokenResolver(null)
			.buildClient();
	
		log.info("Connected successfully");
	
		CosmosDatabase database = client.getDatabase("db-test");
		CosmosContainer container = database.getContainer("product");

		log.info("Create document");
		log.info("--------------------------------");
		Product product = new Product(
			"some3",
			"some3",
			8,
			true
		);

		// CosmosItemRequestOptions cosmosItemRequestOptions = new CosmosItemRequestOptions();
        // CosmosItemResponse<Product> item = container.createItem(product, new PartitionKey(family.getLastName()), cosmosItemRequestOptions);
        container.createItem(product);

		log.info("Item created successfully");
	}

}