package br.com.mateusfilpo.points_of_interest.dto;

public class PointOfInterestProximityDTO {

    private String name;

    public PointOfInterestProximityDTO() {}

    public PointOfInterestProximityDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
