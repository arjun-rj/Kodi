package com.ourteam.kodi.controller;

import java.util.List;
import java.util.Optional;

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

@RestController
public class RootController {
	
	@Autowired
	RootService service;
	
	@GetMapping("/getallhens")
	public List<Hen> getAllHens() {
		List<Hen> henList = service.getAllHens();
		System.out.println(henList.get(0).toString());
		return henList;
	}
	
	@GetMapping("/gethenbyid/{id}")
	public Optional<Hen> getHen(@RequestParam String id) {
		Optional<Hen> defaultHen = Optional.empty();
		Optional<Hen> hen = service.getHen(id);		
		return Optional.ofNullable(hen).orElse(defaultHen);
	}
	
	@PostMapping("/addhen")
	public Hen addHen(@RequestBody Hen hen) {
		return service.addHen(hen);
	}
}
