package com.moBack.backend.api.dao;

import com.moBack.backend.api.AbstractTest;
import com.moBack.backend.api.entity.Account;
import com.moBack.backend.api.entity.Place;
import com.moBack.backend.api.dto.PointDTO;
import com.moBack.backend.api.entity.Review;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.HashSet;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.point;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Transactional
public class JpaTest extends AbstractTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    private Place place1 = Place.builder()
            .name("test-place-1")
            .position(point(WGS84,g(37.4847142,37.5188072)))
            .reviewList(new HashSet<>())
            .build();

    private Place place2 = Place.builder()
            .name("test-place-2")
            .position(point(WGS84,g(37.4847142,37.5188072)))
            .reviewList(new HashSet<>())
            .build();

    private Account account1 = Account.builder()
            .email("test1@gmail.com")
            .firstName("firstName")
            .lastName("lastName")
            .password("1234")
            .reviewList(new HashSet<>())
            .build();

    private Review review = Review.builder()
            .rating(3)
            .comment("so so").build();

    @Before
    public void setup() {
        placeRepository.save(place1);
    }

    @Test
    public void getPlacesTest() throws ParseException {
        PointDTO pointDTO = new PointDTO(37.4847142, 37.5188072);
        Assert.assertTrue(placeRepository.getPlaces(pointDTO, 200).size() > 0);
    }

    @Test
    public void findAllTest() throws ParseException {
        Assert.assertTrue(placeRepository.findAll().size() > 0);
    }

    @Test
    public void addReviewTest() {
        place2.addReview(review);
        place2 = placeRepository.save(place2);

        account1.addReview(review);
        account1 = accountRepository.save(account1);

        Review returnedReview = reviewRepository.save(review);
        Assert.assertNotNull(returnedReview.getPlace());
        Assert.assertNotNull(returnedReview.getAccount());
    }

    @Test
    public void deleteReviewTest() {
        Review review = Review.builder()
                .rating(3)
                .comment("so so").build();

        place2.deleteReview(review);
        placeRepository.save(place2);

        account1.deleteReview(review);
        accountRepository.save(account1);

        reviewRepository.delete(review);
    }
}
