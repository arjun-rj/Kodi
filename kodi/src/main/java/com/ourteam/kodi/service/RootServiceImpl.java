package com.ourteam.kodi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mongodb.client.model.geojson.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Service;

import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.repository.RootDAO;

@Service
public class RootServiceImpl implements RootService {

	@Autowired
	RootDAO dao;
	
	@Override
	public List<Hen> getAllHens() {
		return dao.findAll();
	}

	
	@Override
	public Hen addHen(Hen hen) {		
		return dao.insert(hen);
	}


	@Override
	public Optional<Hen> getHen(String id) {		
		return dao.findById(id);
	}

	@Override
	public List<Hen> getNearByHens() {
		double longitude = 16.523860603109487;
		double latitude = 16.523860603109487;
		Point point = new Point(longitude, latitude);
		double distance = 3;
		NearQuery nearQuery = NearQuery.near(point, Metrics.KILOMETERS).maxDistance(distance);
		return dao.findAll();
	}
}
