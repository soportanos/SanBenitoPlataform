package org.example.Infraestructure.SecundaryPorts;

import com.mongodb.client.MongoDatabase;

public interface Connection {
    public MongoDatabase getConnection();
}
