package com.ourteam.kodi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.exception.RootException;
import com.ourteam.kodi.service.RootService;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.near;

@RestController
public class RootController {
	
	@Autowired
	RootService service;
	
	@GetMapping("/getallhens")
	public List<Hen> getAllHens() {
		List<Hen> henList = service.getAllHens();
		System.out.println(henList.get(0).toString());
		return henList;
	}
	
	@GetMapping("/gethenbyid/{id}")
	public Optional<Hen> getHen(@RequestParam String id) {
		Optional<Hen> defaultHen = Optional.empty();
		Optional<Hen> hen = service.getHen(id);		
		return Optional.ofNullable(hen).orElse(defaultHen);
	}
	
	@PostMapping("/hen")
	public Hen addHen(@RequestBody Hen hen) {
		return service.addHen(hen);
	}

	@GetMapping("/nearby")
	public Object nearByHens() {
		/*String uri = "mongodb+srv://admin:admin@sync.8nd5l.mongodb.net/test";

		try (MongoClient mongoClient = MongoClients.create(uri)) {
			MongoDatabase database = mongoClient.getDatabase("test");
			MongoCollection<Document> collection = database.getCollection("hen");
			collection.createIndex(Indexes.geo2dsphere("location.coordinates"));
			long count = collection.countDocuments();
			System.out.println(count);
			Document doc = collection.find().first();
			if(doc != null) {
				System.out.println(doc.toJson());
			}

			Point bangalore = new Point(new Position(12.955779, 77.654910 ));
			Point vijayawada = new Point(new Position(16.523860603109487, 80.61267889350644));
			Bson query = near("location.coordinates", vijayawada, 10000.0, 0.0);
			System.out.println("found docs");
			collection.find(query)
					.forEach(hen -> System.out.println(hen.toJson()));
		}
		return "got";*/
		return service.getNearByHens();
	}
}
