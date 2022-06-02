package com.example.opgmap_danger_zone_service.repository;

import com.example.opgmap_danger_zone_service.model.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserVoteRepository extends JpaRepository<UserVote, UUID> {

    Optional<UserVote> findByUserIdAndDangerZone_Id(UUID userId, UUID dangerZone_id);

}
