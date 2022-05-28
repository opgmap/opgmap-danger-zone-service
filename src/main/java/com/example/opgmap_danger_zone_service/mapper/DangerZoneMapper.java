package com.example.opgmap_danger_zone_service.mapper;

import com.example.opgmap_danger_zone_service.dto.DangerZoneDto;
import com.example.opgmap_danger_zone_service.model.DangerZone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DangerZoneMapper {

    DangerZone fromDto(DangerZoneDto dangerZoneDto);

    DangerZoneDto toDto(DangerZone dangerZone);
}
