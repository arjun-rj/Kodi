package com.ourteam.kodi.controller;

import com.ourteam.kodi.document.Hen;
import com.ourteam.kodi.service.HenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/hens")
public class HenController {

    @Autowired
    HenService henService;

    @PostMapping()
    public ResponseEntity<Object> addHen(@RequestBody Hen hen,
                                         @RequestHeader(name = "token", required = true) String token) {
        return henService.addHen(hen, token);
    }

    @PutMapping()
    public Object updateHen(@RequestParam String id) {
        return henService.updateHen(id);
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
