package com.moBack.backend;

import com.moBack.backend.dao.PlaceRepository;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.junit.Assert;
import org.junit.Test;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.point;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

import javax.transaction.Transactional;

@Transactional
public class PlaceJpaTest extends AbstractTest{

    @Autowired
    PlaceRepository repository;

    @Test
    public void getPlacesTest() throws ParseException {
     /* jts version
        String pointWKT = String.format("POINT(%s %s)",37.4845142,37.5188072);
        Point point = (Point)new WKTReader().read(pointWKT);
      */
        Point<G2D> pnt = point(WGS84,g(37.4847142,37.5188072));
        Assert.assertEquals(1,repository.getPlaces(pnt,200).size());
    }

    @Test
    public void test() {
        Assert.assertEquals(1,repository.findAll().size());
    }
}
