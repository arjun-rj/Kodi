package com.ourteam.kodi.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.near;

@Service
public class HenService {

    MongoCollection<Document> henCollection;

    public HenService(RootService rootService) {
        henCollection = rootService.getHenCollection();
    }

    public Object getNearByHens() {
        List<String> hens = new ArrayList<>();
        Point vijayawada = new Point(new Position(16.523860603109487, 80.61267889350644));
        Bson query = near("location.coordinates", vijayawada, 10000.0, 0.0);
        System.out.println("found docs");
        henCollection.find(query)
                .forEach(hen -> hens.add(hen.toJson()));
        return hens;
    }
}
