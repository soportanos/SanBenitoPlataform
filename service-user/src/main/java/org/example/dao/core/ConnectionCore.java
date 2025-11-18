package org.example.dao.core;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public abstract class ConnectionCore {
    public MongoDatabase  getConnection() throws Exception{

        //Parameters
        String host = "localhost";
        String port= "27017";
        String database = "BD_TEST";
        //URL
        String url = "mongodb://"+host+":"+port;

        MongoClient client = MongoClients.create(url);

        MongoDatabase db = client.getDatabase(database);

        return db;

    }
}
