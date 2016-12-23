package Javabeans;

import com.mongodb.*;
import java.net.UnknownHostException;

public class mongodbcon {

	final String HOST = "localhost";
	final int PORT=27017;
	final String DBNAME="Assignment3";
	public static mongodbcon instance;
	public MongoClient connection;
	public DB database;

private mongodbcon() throws UnknownHostException {
	this.connection = new MongoClient(this.HOST,this.PORT);
	this.database = this.connection.getDB(DBNAME);
}

public static mongodbcon createInstance() throws UnknownHostException {
	if(mongodbcon.instance == null) {
		mongodbcon.instance = new mongodbcon();
	}
	return mongodbcon.instance;
}

public DBCollection getCollection(String name) {
	return this.database.getCollection(name);
}

}
