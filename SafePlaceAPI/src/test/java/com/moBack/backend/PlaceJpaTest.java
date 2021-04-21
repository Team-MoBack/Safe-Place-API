package com.moBack.backend;

import com.moBack.backend.dao.PlaceRepository;
import com.moBack.backend.dto.PointDTO;
import com.moBack.backend.entity.Place;
import org.junit.Assert;
import org.junit.Test;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PlaceJpaTest extends AbstractTest {

    @Autowired
    PlaceRepository repository;

    @Test
    public void getPlacesTest() throws ParseException {
     /* jts version
        String pointWKT = String.format("POINT(%s %s)",37.4845142,37.5188072);
        Point point = (Point)new WKTReader().read(pointWKT);
      */
        //Point<G2D> pnt = point(WGS84,g(37.4847142,37.5188072));
        PointDTO pointDTO = new PointDTO(37.4847142, 37.5188072);
        List<Place> res = repository.getPlaces(pointDTO, 200);
        Assert.assertEquals(1, repository.getPlaces(pointDTO, 200).size());
    }

    @Test
    public void findAllTest() throws ParseException {
        Assert.assertTrue(repository.findAll().size() > 0);
    }
}
