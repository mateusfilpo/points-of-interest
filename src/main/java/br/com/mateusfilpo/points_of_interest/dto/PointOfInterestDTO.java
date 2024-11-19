package br.com.mateusfilpo.points_of_interest.dto;

import br.com.mateusfilpo.points_of_interest.domain.PointOfInterest;

public class PointOfInterestDTO {

    private String name;
    private Integer x;
    private Integer y;

    public PointOfInterestDTO() {
    }

    public PointOfInterestDTO(String name, Integer x, Integer y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public PointOfInterestDTO(PointOfInterest pointOfInterest) {
        this.name = pointOfInterest.getName();
        this.x = pointOfInterest.getX();
        this.y = pointOfInterest.getY();
    }

    public String getName() {
        return name;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
