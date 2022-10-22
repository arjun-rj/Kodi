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
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.repository.RootDAO;

import static com.mongodb.client.model.Filters.near;

@Service
public class RootServiceImpl implements RootService, AutoCloseable {

	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> henCollection;

	public RootServiceImpl() {
		String uri = "mongodb+srv://admin:admin@sync.8nd5l.mongodb.net/test";
		mongoClient = MongoClients.create(uri);
		database = mongoClient.getDatabase("test");
		henCollection = database.getCollection("hen");
	}

	@Override
	public List<Hen> getAllHens() {
		//return dao.findAll();
		return new ArrayList<>();
	}

	
	@Override
	public Hen addHen(Hen hen) {		
		//return dao.insert(hen);
		return null;
	}


	@Override
	public Optional<Hen> getHen(String id) {		
		//return dao.findById(id);
		return Optional.empty();
	}

	@Override
	public Object getNearByHens() {
		List<String> hens = new ArrayList<>();
		Point vijayawada = new Point(new Position(16.523860603109487, 80.61267889350644));
		Bson query = near("location.coordinates", vijayawada, 10000.0, 0.0);
		System.out.println("found docs");
		henCollection.find(query)
				.forEach(hen -> hens.add(hen.toJson()));
		return hens;
	}

	@Override
	public void close() throws Exception {
		if(mongoClient != null) {
			mongoClient.close();
		}
	}
}
