package br.com.mateusfilpo.points_of_interest.controller;

import br.com.mateusfilpo.points_of_interest.domain.PointOfInterest;
import br.com.mateusfilpo.points_of_interest.dto.PointOfInterestDTO;
import br.com.mateusfilpo.points_of_interest.dto.PointOfInterestProximityDTO;
import br.com.mateusfilpo.points_of_interest.service.PointOfInterestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/points-of-interest")
public class PointOfInterestController {

    private final PointOfInterestService service;

    public PointOfInterestController(PointOfInterestService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PointOfInterestDTO>> findAll() {
        List<PointOfInterestDTO> result = service.findAllPointsOfInterest();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<PointOfInterestProximityDTO>> findAllByProximity(
            @RequestParam Integer x,
            @RequestParam Integer y,
            @RequestParam Integer dMax) {

        List<PointOfInterestProximityDTO> result = service.findAllPointsOfInterestByProximity(x, y, dMax);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<Void> createPointOfInterest(@RequestBody PointOfInterestDTO pointOfInterestDto) {
        PointOfInterest result = service.createPointOfInterest(pointOfInterestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
