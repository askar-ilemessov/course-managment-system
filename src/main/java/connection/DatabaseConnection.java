package connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.MongoClients;

public class DatabaseConnection {

	private static MongoClient mongoClient;
	private static DatabaseConnection databaseConnection;
	private static MongoClientURI connectionString = new MongoClientURI(
			"mongodb+srv://admin:admin@cluster0.bwy7e.mongodb.net/CMS?retryWrites=true&w=majority");

	// MongoDatabase database = mongoClient.getDatabase("test");

	private DatabaseConnection() {

		mongoClient = new MongoClient(connectionString);
	}

	public static void establishConnections() {
		try {
			mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("CMS");

			System.out.println("Successfully Connected" + " to the database");

		} catch (Exception e) {
			System.out.println("Connection establishment failed");
			System.out.println(e);
		}
	}

	public static MongoClient getConnection() {

		if (databaseConnection == null) {
			databaseConnection = new DatabaseConnection();
		}

		return mongoClient;
	}
	
	public static MongoDatabase getDatabase () {
		return mongoClient.getDatabase("CMS");
	}

	public static void closeDatabase() {
		mongoClient.close();
	}
	
	public static void main(String[] args) {
		establishConnections();
		Collection coll = null;
		coll.getCollection("users");
		coll.displayDocuments();
	}

}
