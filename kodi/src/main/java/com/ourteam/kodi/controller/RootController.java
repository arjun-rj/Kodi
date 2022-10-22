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
import com.ourteam.kodi.service.HenService;
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
	HenService henService;

	@GetMapping("/getallhens")
	public List<Hen> getAllHens() {
		return null;
	}
	
	@GetMapping("/gethenbyid/{id}")
	public Optional<Hen> getHen(@RequestParam String id) {
		Optional<Hen> defaultHen = Optional.empty();
		/*Optional<Hen> hen = service.getHen(id);
		return Optional.ofNullable(hen).orElse(defaultHen);*/
		return defaultHen;
	}
	
	@PostMapping("/hen")
	public Hen addHen(@RequestBody Hen hen) {
		return null;
	}

	@GetMapping("/nearby")
	public Object nearByHens() {
		return henService.getNearByHens();
	}
}
