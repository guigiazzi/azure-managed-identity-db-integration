package GetStarted;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * Simple application that shows how to use Azure Cosmos DB for MongoDB API in a Java application.
 *
 */
public class Program {
	
    public static void main(String[] args)
    {		
        // read write connection string
        MongoClientURI uri = new MongoClientURI("mongodb://testegiazzi:mNPdMjduDcFLCONuMXiEpwMl1wbfwKgKmTN5YYrkFGCU8abmpeRIl137plIbNBU0YIDWqXpP6IdKACDbVcRd3A==@testegiazzi.mongo.cosmos.azure.com:10255/?ssl=true&replicaSet=globaldb&retrywrites=false&maxIdleTimeMS=120000&appName=@testegiazzi@&authSource=db-test");
        
        // read only connection string
        // MongoClientURI uri = new MongoClientURI("mongodb://testegiazzi:2p5heAvYgnMGmdSkbRBR4MldGKKH7xxhS2muWb2zXikDn6ep4BBHhDJO66rByeu4C28WsuRjXJ4WACDbIGVzug==@testegiazzi.mongo.cosmos.azure.com:10255/?ssl=true&replicaSet=globaldb&retrywrites=false&maxIdleTimeMS=120000&appName=@testegiazzi@&authSource=db-test");
        
        System.out.println("Connecting to CosmosDB for MongoDB with connection string " + uri.getURI());

        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(uri);
            
            System.out.println("Connected successfully");
            
            MongoDatabase database = mongoClient.getDatabase("db-test");
            MongoCollection<Document> collection = database.getCollection("products");

            System.out.println("Getting all documents");
            collection.find().iterator().forEachRemaining(result ->{
                System.out.println(result.toJson());
            });

            try {
                System.out.println("Inserting new document");
                Document document1 = new Document("fruit", "watermelon");
                collection.insertOne(document1);
                System.out.println("Inserted successfully");
            } catch(Exception e){
                e.printStackTrace();
            }

            System.out.println("Getting all documents");
            collection.find().iterator().forEachRemaining(result ->{
                System.out.println(result.toJson());
            });   	
        	            
        } finally {
        	if (mongoClient != null) {
        		mongoClient.close();
        	}
        }
    }
}
