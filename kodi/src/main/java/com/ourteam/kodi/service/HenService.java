package com.ourteam.kodi.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.ourteam.kodi.document.Hen;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.near;

@Service
public class HenService {

    MongoCollection<Hen> henCollection;

    public HenService(RootService rootService) {
        henCollection = rootService.getHenCollection();
    }

    public Object addHen(Hen hen) {
        return henCollection.insertOne(hen);
    }

    public Object getNearByHens() {
        //henCollection.createIndex(Indexes.geo2dsphere("address.location.coordinates"));
        List<Hen> hens = new ArrayList<>();
        Point vijayawada = new Point(new Position(16.523860603109487, 80.61267889350644));
        Bson query = near("address.location.coordinates", vijayawada, 10000.0, 0.0);
        henCollection.find(query)
                .forEach(hens::add);
        return hens;
    }
}
