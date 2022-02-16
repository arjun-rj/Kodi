package com.ourteam.kodi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
}
