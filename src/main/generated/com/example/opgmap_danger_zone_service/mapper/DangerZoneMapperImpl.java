package com.example.opgmap_danger_zone_service.mapper;

import com.example.opgmap_danger_zone_service.dto.DangerZoneDto;
import com.example.opgmap_danger_zone_service.dto.DangerZoneDto.DangerZoneDtoBuilder;
import com.example.opgmap_danger_zone_service.model.DangerZone;
import com.example.opgmap_danger_zone_service.model.DangerZone.DangerZoneBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-06T16:30:28+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Homebrew)"
)
@Component
public class DangerZoneMapperImpl implements DangerZoneMapper {

    @Override
    public DangerZone fromDto(DangerZoneDto dangerZoneDto) {
        if ( dangerZoneDto == null ) {
            return null;
        }

        DangerZoneBuilder dangerZone = DangerZone.builder();

        dangerZone.id( dangerZoneDto.getId() );
        dangerZone.name( dangerZoneDto.getName() );
        dangerZone.positionX( dangerZoneDto.getPositionX() );
        dangerZone.positionY( dangerZoneDto.getPositionY() );
        dangerZone.created( dangerZoneDto.getCreated() );
        dangerZone.updated( dangerZoneDto.getUpdated() );
        dangerZone.rating( dangerZoneDto.getRating() );
        dangerZone.userId( dangerZoneDto.getUserId() );

        return dangerZone.build();
    }

    @Override
    public DangerZoneDto toDto(DangerZone dangerZone) {
        if ( dangerZone == null ) {
            return null;
        }

        DangerZoneDtoBuilder dangerZoneDto = DangerZoneDto.builder();

        dangerZoneDto.id( dangerZone.getId() );
        dangerZoneDto.name( dangerZone.getName() );
        dangerZoneDto.positionX( dangerZone.getPositionX() );
        dangerZoneDto.positionY( dangerZone.getPositionY() );
        dangerZoneDto.userId( dangerZone.getUserId() );
        dangerZoneDto.created( dangerZone.getCreated() );
        dangerZoneDto.updated( dangerZone.getUpdated() );
        dangerZoneDto.rating( dangerZone.getRating() );

        return dangerZoneDto.build();
    }
}
