package com.moBack.backend.api.service;

import com.moBack.backend.api.AbstractTest;
import com.moBack.backend.api.dao.AccountRepository;
import com.moBack.backend.api.dao.PlaceRepository;
import com.moBack.backend.api.dao.ReviewRepository;
import com.moBack.backend.api.dto.ReviewDTO;
import com.moBack.backend.api.entity.Account;
import com.moBack.backend.api.entity.Place;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.point;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

public class ReviewServiceTest extends AbstractTest {

    private ReviewService reviewService;
    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private PlaceRepository placeRepository;
    @MockBean
    private ReviewRepository reviewRepository;

    private ReviewDTO reviewDTO = ReviewDTO.builder()
            .accountId(10)
            .comment("test comment")
            .rating(5)
            .build();


    private Place place = Place.builder()
            .name("test-place-2")
            .position(point(WGS84,g(37.4847142,37.5188072)))
            .reviewList(new HashSet<>())
            .build();

    private Account account = Account.builder()
            .email("test1@gmail.com")
            .firstName("firstName")
            .lastName("lastName")
            .password("1234")
            .reviewList(new HashSet<>())
            .build();

    @Before
    public void setup() {
        reviewService = new ReviewServiceImpl(accountRepository,placeRepository,reviewRepository);
    }

    @Test
    public void addReviewSuccessTest() {
        Mockito.when(accountRepository.findById(reviewDTO.getAccountId())).thenReturn(Optional.of(account));
        Mockito.when(placeRepository.findById(reviewDTO.getPlaceId())).thenReturn(Optional.of(place));
        Assert.assertEquals("success",reviewService.addReview(reviewDTO));
    }

    @Test
    public void addReviewFailTest() {
        Mockito.when(accountRepository.findById(reviewDTO.getAccountId())).thenReturn(Optional.empty());
        Mockito.when(placeRepository.findById(reviewDTO.getPlaceId())).thenReturn(Optional.empty());
        Assert.assertEquals("fail",reviewService.addReview(reviewDTO));
    }

    @Test
    public void deleteReviewSuccessTest() {
        Mockito.when(accountRepository.findById(reviewDTO.getAccountId())).thenReturn(Optional.of(account));
        Mockito.when(placeRepository.findById(reviewDTO.getPlaceId())).thenReturn(Optional.of(place));
        Assert.assertEquals("success",reviewService.deleteReview(reviewDTO));
    }

    @Test
    public void deleteReviewFailTest() {
        Mockito.when(accountRepository.findById(reviewDTO.getAccountId())).thenReturn(Optional.empty());
        Mockito.when(placeRepository.findById(reviewDTO.getPlaceId())).thenReturn(Optional.empty());
        Assert.assertEquals("fail",reviewService.deleteReview(reviewDTO));
    }
}
