package com.example.opgmap_danger_zone_service.controller;

import com.example.opgmap_danger_zone_service.dto.DangerZoneDto;
import com.example.opgmap_danger_zone_service.model.DangerZone;
import com.example.opgmap_danger_zone_service.service.DangerZoneService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/danger-zone/")
@SecurityRequirement(name = "security_auth")
@CrossOrigin("*")
public class DangerZoneController {

    private final Class<DangerZoneController> zoneControllerClass = DangerZoneController.class;
    private final DangerZoneService dangerZoneService;

    @GetMapping("/ping")
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PermitAll
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Danger zone service is alive");
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public EntityModel<DangerZone> createDangerZone(Principal principal,
                                                    @Valid @RequestBody DangerZoneDto dangerZoneDto) {
        DangerZone dangerZone = dangerZoneService.createDangerZone(UUID.fromString(principal.getName()), dangerZoneDto);

        return EntityModel.of(dangerZone,
                linkTo(methodOn(zoneControllerClass).createDangerZone(principal, dangerZoneDto))
                        .withSelfRel(),
                linkTo(methodOn(zoneControllerClass).getDangerZoneById(dangerZone.getId()))
                        .withRel("danger-zone")
                        .andAffordances(dangerZoneAffordances(dangerZone.getId())),
                linkTo(methodOn(zoneControllerClass).changeDangerZoneRating(principal, dangerZone.getId(), true))
                        .withRel("vote"));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public EntityModel<DangerZoneDto> getDangerZoneById(@PathVariable UUID id) {
        return EntityModel.of(dangerZoneService.getDangerZoneById(id),
                linkTo(methodOn(zoneControllerClass).getDangerZoneById(id))
                        .withSelfRel().andAffordances(dangerZoneAffordances(id)),
                linkTo(methodOn(zoneControllerClass).changeDangerZoneRating(null, id, true))
                        .withRel("vote"));
    }

    @PutMapping("/{id}/vote")
    @PreAuthorize("isAuthenticated()")
    public EntityModel<DangerZone> changeDangerZoneRating(Principal principal, @PathVariable UUID id, @RequestParam boolean vote) {
        return EntityModel.of(dangerZoneService.changeDangerZoneRating(id, UUID.fromString(principal.getName()), vote),
                linkTo(methodOn(zoneControllerClass).changeDangerZoneRating(null, id, true))
                        .withSelfRel(),
                linkTo(methodOn(zoneControllerClass).getDangerZoneById(id))
                        .withRel("danger-zone")
                        .andAffordances(dangerZoneAffordances(id)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public EntityModel<DangerZoneDto> updateDangerZone(@RequestBody DangerZoneDto dangerZoneDto, @PathVariable UUID id) {
        return EntityModel.of(dangerZoneService.updateDangerZone(id, dangerZoneDto),
                linkTo(methodOn(zoneControllerClass).updateDangerZone(dangerZoneDto, id))
                        .withSelfRel().andAffordances(dangerZoneAffordances(id)),
                linkTo(methodOn(zoneControllerClass).changeDangerZoneRating(null, id, true))
                        .withRel("vote"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public EntityModel<UUID> deleteDangerZoneById(@PathVariable UUID id) {
        return EntityModel.of(dangerZoneService.deleteById(id));
    }

    private List<Affordance> dangerZoneAffordances(UUID id) {
        return List.of(
                afford(methodOn(zoneControllerClass).getDangerZoneById(id)),
                afford(methodOn(zoneControllerClass).deleteDangerZoneById(id)),
                afford(methodOn(zoneControllerClass).updateDangerZone(null, id))
        );
    }

}
