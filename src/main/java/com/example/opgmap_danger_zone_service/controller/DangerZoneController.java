package com.example.opgmap_danger_zone_service.controller;

import com.example.opgmap_danger_zone_service.dto.DangerZoneDto;
import com.example.opgmap_danger_zone_service.service.DangerZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/danger-zone/")
public class DangerZoneController {

    private final RestTemplate restTemplate;
    private final DangerZoneService dangerZoneService;

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

    @PostMapping
    public UUID createDangerZone(@Valid @RequestBody DangerZoneDto dangerZoneDto) {
        return dangerZoneService.createDangerZone(dangerZoneDto);
    }

    @GetMapping("/{id}")
    public DangerZoneDto getDangerZoneById(@PathVariable UUID id) {
        return dangerZoneService.getDangerZoneById(id);
    }

    @PutMapping("/{id}/vote")
    public UUID changeDangerZoneRating(@PathVariable UUID id, @RequestParam boolean vote){
        return dangerZoneService.changeDangerZoneRating(id, vote);
    }

    @PutMapping("/{id}")
    public DangerZoneDto updateDangerZone(@PathVariable UUID id, @RequestBody DangerZoneDto dangerZoneDto) {
        return dangerZoneService.updateDangerZone(id, dangerZoneDto);
    }

    @DeleteMapping("/{id}")
    public String deleteDangerZoneById(@PathVariable UUID id) {
        return dangerZoneService.deleteById(id);
    }

}
