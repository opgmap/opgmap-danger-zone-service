package com.example.opgmap_danger_zone_service.service;

import com.example.opgmap_danger_zone_service.dto.DangerZoneDto;
import com.example.opgmap_danger_zone_service.model.DangerZone;

import java.util.UUID;

public interface DangerZoneService {

    DangerZone createDangerZone(UUID id, DangerZoneDto dangerZoneDto);

    DangerZoneDto getDangerZoneById(UUID id);

    DangerZone changeDangerZoneRating(UUID id, UUID userId, boolean vote);

    DangerZoneDto updateDangerZone(UUID id, DangerZoneDto dangerZoneDto);

    DangerZoneDto deleteById(UUID id);
}
