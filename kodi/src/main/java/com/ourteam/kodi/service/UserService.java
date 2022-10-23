package com.ourteam.kodi.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.ourteam.kodi.common.Constants;
import com.ourteam.kodi.common.ReturnStatus;
import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.document.User;
import com.ourteam.kodi.utils.CommonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

@Service
public class UserService {

    MongoCollection<User> userCollection;

    public UserService(RootService rootService) {
        this.userCollection = rootService.getUserCollection();
    }

    public ResponseEntity<Object> signUpUser(User user) {
        User knownUser = userCollection.find(eq("phone", user.phone)).first();
        if(knownUser != null) {
            String message = String.format("There is an user already existing with the give phone: %s", user.phone);
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        user.setSignedUpAt(new Date());
        user.setLastModifiedAt(new Date());
        InsertOneResult result = userCollection.insertOne(user);
        if(result.getInsertedId() == null) {
            return new ResponseEntity<>(Constants.SOME_PROBLEM, HttpStatus.FORBIDDEN);
        }
        String userId = result.getInsertedId().asObjectId().getValue().toString();
        System.out.println("Created user: "+userId);
        ReturnStatus<String> status = CommonUtils.generateUserToken(userId, user.name, user.phone);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    public ResponseEntity<Object> loginUser(String phone) {
        User user = userCollection.find(eq("phone", phone)).first();
        if(user == null) {
            return new ResponseEntity<>(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        /*Bson updates = Updates.combine(
                Updates.set("lastLoginAt", new Date()));*/
        user.lastLoginAt = new Date();  // not working
        System.out.println(user._id.toString());
        ReturnStatus<String> status = CommonUtils.generateUserToken(user._id.toString(), user.name, user.phone);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    public ResponseEntity<Object> getUserById(String id, String token) {
        ReturnStatus<Jws<Claims>> status = CommonUtils.parseJwt(token);
        if(!status.status) {
            return new ResponseEntity<>(status.message, HttpStatus.FORBIDDEN);
        }
        Jws<Claims> decode = status.data;
        System.out.println(decode.getBody().get("phone"));  // user phone
        System.out.println(decode.getBody().get("jti"));    // user id
        User user = userCollection.find(eq("_id", new ObjectId(id))).first();
        if(user == null) {
            return new ResponseEntity<>(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        System.out.printf("found user, id: %s, name: %s%n", user._id, user.name);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }
}
