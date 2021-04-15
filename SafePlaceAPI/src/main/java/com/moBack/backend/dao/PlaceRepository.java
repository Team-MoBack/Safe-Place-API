package com.moBack.backend.dao;

import com.moBack.backend.entity.Place;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    @Query(value = "select * from Place where ST_Distance_Sphere(location,point(:#{#center.getPosition().getLon()},:#{#center.getPosition().getLat()})) < :radius ", nativeQuery = true)
    public List<Place> getPlaces(@Param("center") Point<G2D> center, @Param("radius") float radius);
}
