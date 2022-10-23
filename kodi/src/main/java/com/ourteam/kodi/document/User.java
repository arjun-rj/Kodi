package com.ourteam.kodi.document;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

@Data
public class User {
    public ObjectId _id;
    public String name;
    public String phone;
    public Location userLocation;
    public ArrayList<String> myHens;
    public Date signedUpAt;
    public Date lastModifiedAt;
    public Date lastLoginAt;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", userLocation=" + userLocation +
                ", myHens=" + myHens +
                ", signedUpAt=" + signedUpAt +
                ", lastModifiedAt=" + lastModifiedAt +
                ", lastLoginAt=" + lastLoginAt +
                '}';
    }
}
