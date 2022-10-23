package com.ourteam.kodi.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.client.result.InsertOneResult;
import com.ourteam.kodi.common.Constants;
import com.ourteam.kodi.common.ReturnStatus;
import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.document.Owner;
import com.ourteam.kodi.document.User;
import com.ourteam.kodi.utils.CommonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.near;

@Service
public class HenService {

    MongoCollection<Hen> henCollection;
    MongoCollection<User> userCollection;

    public HenService(RootService rootService) {
        henCollection = rootService.getHenCollection();
        userCollection = rootService.getUserCollection();
    }

    public ResponseEntity<Object> addHen(Hen hen, String token) {
        ReturnStatus<Jws<Claims>> status = CommonUtils.parseJwt(token);
        if(!status.status) {
            return new ResponseEntity<>(status.message, HttpStatus.FORBIDDEN);
        }
        String userId = status.data.getBody().get("jti").toString();
        System.out.println(userId);
        User user = userCollection.find(eq("_id", new ObjectId(userId))).first();
        if(user == null) {
            return new ResponseEntity<>(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        if(hen.owner == null) {
            hen.owner = new Owner(user._id.toString(), user.phone, user.name, "");
        }
        InsertOneResult result = henCollection.insertOne(hen);
        return new ResponseEntity<>(result.getInsertedId(), HttpStatus.OK);
    }

    public Object updateHen(String id) {
        Bson updates = Updates.combine(
                Updates.set("weight", 800.0),
                Updates.set("lastModifiedAt", new Date()));
        return henCollection.findOneAndUpdate(eq("_id", new ObjectId(id)), updates);
    }

    public Object getHenById(String id) {
        Hen hen = henCollection.find(eq("_id", new ObjectId(id))).first();
        if(hen == null) {
            return "Not found";
        }
        System.out.printf("found hen, id: %s, name: %s%n", hen._id, hen.nickName);
        return hen;
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
