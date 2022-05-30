package com.example.opgmap_danger_zone_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DangerZoneDto {

    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private Double positionX;

    @NotNull
    private Double positionY;

    private UUID userId;

    private LocalDateTime created;

    private LocalDateTime updated;

    private Long rating;
}
