package com.ourteam.kodi.controller;

import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.service.HenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HenController {

    @Autowired
    HenService henService;

    @PostMapping("/hens")
    public Object addHen(@RequestBody Hen hen) {
        return henService.addHen(hen);
    }

    @GetMapping("/hens/nearby")
    public Object nearByHens() {
        return henService.getNearByHens();
    }
}
