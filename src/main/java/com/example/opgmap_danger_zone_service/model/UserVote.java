package com.example.opgmap_danger_zone_service.model;

import lombok.*;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "danger_zone_id", nullable = false)
    private DangerZone dangerZone;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private boolean value;

}
