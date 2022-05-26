package com.example.opgmap_danger_zone_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/danger-zone/")
public class DangerZoneController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ping")
    public ResponseEntity<String> ping(HttpServletRequest httpRequest) {
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", httpRequest.getHeader("Authorization"));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://DANGER-ZONE-SERVICE/api/v1/danger-zone/all", HttpMethod.GET, requestEntity, String.class);
        return ResponseEntity.ok("Hello from Danger Zone controller - " + response.getBody());
    }

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAll(HttpServletRequest httpRequest) {
        return ResponseEntity.ok(new ArrayList<>());
    }

}
