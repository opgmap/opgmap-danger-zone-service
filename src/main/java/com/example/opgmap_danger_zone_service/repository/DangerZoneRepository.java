package com.example.opgmap_danger_zone_service.repository;

import com.example.opgmap_danger_zone_service.model.DangerZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DangerZoneRepository extends JpaRepository<DangerZone, UUID> {
}
