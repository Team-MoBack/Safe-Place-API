package com.moBack.backend.api.dao;

import com.moBack.backend.api.entity.Place;
import com.moBack.backend.api.dto.PointDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    @Query(value = "select * from Place where ST_Distance_Sphere(location,ST_SRID(point(:#{#center.getLon()},:#{#center.getLat()}),4326)) < :radius ", nativeQuery = true)
    public List<Place> getPlaces(@Param("center") PointDTO center, @Param("radius") float radius);
}
