package org.example.Infraestructure;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.example.Infraestructure.SecundaryPorts.Connection;


public class ConnectionMongoBD implements Connection {
    public MongoDatabase  getConnection(){

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
