package com.ourteam.kodi.service;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.ourteam.kodi.document.Hen;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.stereotype.Service;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Service
public class RootService implements AutoCloseable{

	MongoClient mongoClient;
	MongoDatabase database;
	public MongoCollection<Hen> henCollection;

	public RootService() {
		String uri = "mongodb+srv://admin:admin@sync.8nd5l.mongodb.net/test";

		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		ConnectionString connectionString = new ConnectionString(uri);
		MongoClientSettings settings = MongoClientSettings.builder()
				.codecRegistry(pojoCodecRegistry)
				.applyConnectionString(connectionString)
				.build();
		mongoClient = MongoClients.create(settings);
		database = mongoClient.getDatabase("test");
		henCollection = database.getCollection("hen", Hen.class);
	}

	public MongoCollection<Hen> getHenCollection() {
		return henCollection;
	}

	@Override
	public void close() throws Exception {
		if(mongoClient != null) {
			mongoClient.close();
		}
	}
}