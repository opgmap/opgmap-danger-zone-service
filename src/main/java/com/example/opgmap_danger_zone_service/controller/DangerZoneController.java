package com.example.opgmap_danger_zone_service.controller;

import com.example.opgmap_danger_zone_service.dto.DangerZoneDto;
import com.example.opgmap_danger_zone_service.service.DangerZoneService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/danger-zone/")
@SecurityRequirement(name = "security_auth")
@CrossOrigin("*")
public class DangerZoneController {
    private final DangerZoneService dangerZoneService;

    @GetMapping("/ping")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Danger zone service is alive");
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public UUID createDangerZone(Principal principal,
                                 @Valid @RequestBody DangerZoneDto dangerZoneDto) {
        return dangerZoneService.createDangerZone(UUID.fromString(principal.getName()), dangerZoneDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public DangerZoneDto getDangerZoneById(@PathVariable UUID id) {
        return dangerZoneService.getDangerZoneById(id);
    }

    @PutMapping("/{id}/vote")
    @PreAuthorize("isAuthenticated()")
    public UUID changeDangerZoneRating(Principal principal, @PathVariable UUID id, @RequestParam boolean vote){
        return dangerZoneService.changeDangerZoneRating(id, UUID.fromString(principal.getName()), vote);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public DangerZoneDto updateDangerZone(@PathVariable UUID id, @RequestBody DangerZoneDto dangerZoneDto) {
        return dangerZoneService.updateDangerZone(id, dangerZoneDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public UUID deleteDangerZoneById(@PathVariable UUID id) {
        return dangerZoneService.deleteById(id);
    }

}
