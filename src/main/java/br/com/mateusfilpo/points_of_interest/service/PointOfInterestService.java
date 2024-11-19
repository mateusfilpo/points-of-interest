package br.com.mateusfilpo.points_of_interest.service;

import br.com.mateusfilpo.points_of_interest.domain.PointOfInterest;
import br.com.mateusfilpo.points_of_interest.dto.PointOfInterestDTO;
import br.com.mateusfilpo.points_of_interest.dto.PointOfInterestProximityDTO;
import br.com.mateusfilpo.points_of_interest.repository.PointOfInterestRepository;
import br.com.mateusfilpo.points_of_interest.service.exception.InvalidDataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointOfInterestService {

    private final PointOfInterestRepository repository;

    public PointOfInterestService(PointOfInterestRepository repository) {
        this.repository = repository;
    }

    public List<PointOfInterestDTO> findAllPointsOfInterest() {
        return repository.findAll().stream()
                .map(PointOfInterestDTO::new)
                .toList();
    }

//    public List<PointOfInterestProximityDTO> findAllPointsOfInterestByProximity(Integer x, Integer y, Integer dMax) {
//        return repository.findPointsOfInterestNameByProximity(x, y, dMax)
//                .stream()
//                .map(PointOfInterestProximityDTO::new)
//                .toList();
//    }

    public List<PointOfInterestProximityDTO> findAllPointsOfInterestByProximity(Integer x, Integer y, Integer dMax) {
        return findAllPointsOfInterest().stream()
                .filter(pointOfInterest -> calculateDistance(x, pointOfInterest.getX(), y, pointOfInterest.getY()) <= dMax)
                .map(pointOfInterest -> new PointOfInterestProximityDTO(pointOfInterest.getName()))
                .toList();
    }

    public PointOfInterest createPointOfInterest(PointOfInterestDTO pointOfInterestDto) {
        validateData(pointOfInterestDto);
        return repository.save(new PointOfInterest(pointOfInterestDto));
    }

    private void validateData(PointOfInterestDTO pointOfInterestDto) {
        List<String> errors = new ArrayList<>();

        if (pointOfInterestDto.getX() < 0) {
            errors.add("Valor de X não pode ser negativo");
        }

        if (pointOfInterestDto.getY() < 0) {
            errors.add("Valor de Y não pode ser negativo");
        }

        if (!errors.isEmpty()) {
            throw new InvalidDataException(errors);
        }
    }

    private double calculateDistance(Integer x1, Integer x2, Integer y1, Integer y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}
