package com.ourteam.kodi.controller;

import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.service.HenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/hens")
public class HenController {

    @Autowired
    HenService henService;

    @PostMapping()
    public Object addHen(@RequestBody Hen hen) {
        return henService.addHen(hen);
    }

    @GetMapping()
    public Object getHenById(@RequestParam String id) {
        return henService.getHenById(id);
    }

    @GetMapping("/nearby")
    public Object nearByHens() {
        return henService.getNearByHens();
    }
}
