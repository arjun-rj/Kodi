package com.ourteam.kodi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.ourteam.kodi.document.Hen;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import static com.mongodb.client.model.Filters.near;

@Service
public class RootService implements AutoCloseable{

	MongoClient mongoClient;
	MongoDatabase database;
	public MongoCollection<Document> henCollection;

	public RootService() {
		String uri = "mongodb+srv://admin:admin@sync.8nd5l.mongodb.net/test";
		mongoClient = MongoClients.create(uri);
		database = mongoClient.getDatabase("test");
		henCollection = database.getCollection("hen");
	}

	public MongoCollection<Document> getHenCollection() {
		return henCollection;
	}

	@Override
	public void close() throws Exception {
		if(mongoClient != null) {
			mongoClient.close();
		}
	}
}