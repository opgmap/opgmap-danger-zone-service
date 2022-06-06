package com.example.opgmap_danger_zone_service.kafka.services;

import com.example.opgmap_danger_zone_service.kafka.config.KafkaConfiguration;
import com.example.opgmap_danger_zone_service.kafka.dto.DeleteDangerZoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final StreamBridge streamBridge;

    public void delete(UUID id) {
        // delete danger zone kafka event
        streamBridge.send(KafkaConfiguration.DELETE, DeleteDangerZoneDto.builder()
                .id(id)
                .createdAt(LocalDateTime.now())
                .build(), MediaType.APPLICATION_JSON);
    }

}
