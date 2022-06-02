package com.example.opgmap_danger_zone_service.service;

import com.example.opgmap_danger_zone_service.model.UserVote;

import java.util.Optional;
import java.util.UUID;

public interface UserVoteService {
    UserVote getVote(UUID dangerZoneId, UUID userId);

    Optional<UserVote> findVote(UUID dangerZoneId, UUID userId);

    UserVote saveVote(UserVote userVote);

    UserVote updateVote(UserVote userVote);

    void removeVote(UserVote userVote);

    void removeVoteById(UUID id);
}
