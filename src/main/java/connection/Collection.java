package connection;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

public class Collection {

	public static MongoCollection<Document> getCollection(String collectionName) {
		MongoCollection<Document> collection = null;
		try {
			// establishConnections() Code
			// is defined above
			DatabaseConnection.establishConnections();

			// Retrieve the collection
			collection = DatabaseConnection.getDatabase().getCollection(collectionName);

			System.out.println("Collection retrieved Successfully");
			
		} catch (Exception e) {
			System.out.println("Collection retrieval failed");
			System.out.println(e);
		}
		return collection;
	}

	public static void insertAUserIntoDb(String name, String password) {
		try {
			// establishConnections() Code
			// is defined above
			DatabaseConnection.establishConnections();

			// Creating the document
			// to be inserted

			System.out.println("Document inserted Successfully");
		} catch (Exception e) {
			System.out.println("Document insertion failed");
			System.out.println(e);
		}
	}

	public static void displayDocuments() {

		try {
			// establishConnections() Code
			// is defined above
			DatabaseConnection.establishConnections();

			System.out.println("Displaying the list" + " of Documents");

			// Get the list of documents from the DB
			FindIterable<Document> iterobj = getCollection("users").find();

			// Print the documents using iterators
			Iterator itr = iterobj.iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next());
			}
		} catch (Exception e) {
			System.out.println("Could not find the documents " + "or No document exists");
			System.out.println(e);
		}
	}

}