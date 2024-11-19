package br.com.mateusfilpo.points_of_interest.repository;

import br.com.mateusfilpo.points_of_interest.domain.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {

    @Query(value =
            "SELECT p.name " +
            "FROM point_of_interest p " +
            "WHERE SQRT(POWER(p.x - :x, 2) + POWER(p.y - :y, 2)) <= :dMax",
           nativeQuery = true)
    List<String> findPointsOfInterestNameByProximity(Integer x, Integer y, Integer dMax);
}
