package com.ourteam.kodi.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.document.User;
import com.ourteam.kodi.utils.CommonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

@Service
public class UserService {

    MongoCollection<User> userCollection;

    public UserService(RootService rootService) {
        this.userCollection = rootService.getUserCollection();
    }

    public Object signUpUser(User user) {
        User knownUser = userCollection.find(eq("phone", user.phone)).first();
        if(knownUser != null) {
            return String.format("There is an user already existing with the give phone: %s", user.phone);
        }
        user.setSignedUpAt(new Date());
        user.setLastModifiedAt(new Date());
        InsertOneResult result = userCollection.insertOne(user);
        System.out.println(result.getInsertedId());
        return "User signed up";
    }

    public Object loginUser(String phone) {
        User user = userCollection.find(eq("phone", phone)).first();
        if(user == null) {
            return "User not found";
        }
        /*Bson updates = Updates.combine(
                Updates.set("lastLoginAt", new Date()));*/
        user.lastLoginAt = new Date();  // not working
        return CommonUtils.generateUserToken(user._id.toString(), user.name, user.phone);
    }

    public Object getUserById(String id, String token) {
        Jws<Claims> decode = CommonUtils.parseJwt(token);
        System.out.println(decode.getBody().get("phone"));  // user phone
        System.out.println(decode.getBody().get("jti"));    // user id

        User user = userCollection.find(eq("_id", new ObjectId(id))).first();
        if(user == null) {
            return "Not found";
        }
        System.out.printf("found user, id: %s, name: %s%n", user._id, user.name);
        return user;
    }
}
