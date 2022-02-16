package com.ourteam.kodi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ourteam.kodi.document.Hen;

public interface RootDAO extends MongoRepository<Hen, String> {

}