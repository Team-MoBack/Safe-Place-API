package com.moBack.backend.api.service;

import com.moBack.backend.api.dao.AccountRepository;
import com.moBack.backend.api.dao.PlaceRepository;
import com.moBack.backend.api.dao.ReviewRepository;
import com.moBack.backend.api.dto.ReviewDTO;
import com.moBack.backend.api.entity.Account;
import com.moBack.backend.api.entity.Place;
import com.moBack.backend.api.entity.Review;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private AccountRepository accountRepository;
    private PlaceRepository placeRepository;
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(AccountRepository accountRepository, PlaceRepository placeRepository, ReviewRepository reviewRepository) {
        this.accountRepository = accountRepository;
        this.placeRepository = placeRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public String addReview(ReviewDTO reviewDTO) {
        Optional<Account> account = accountRepository.findById(reviewDTO.getAccountId());
        Optional<Place> place = placeRepository.findById(reviewDTO.getPlaceId());
        Review review = Review.builder()
                .comment(reviewDTO.getComment())
                .rating(reviewDTO.getRating())
                .build();
        if (account.isPresent() && place.isPresent()) {
            account.get().addReview(review);
            place.get().addReview(review);
            accountRepository.save(account.get());
            placeRepository.save(place.get());
            reviewRepository.save(review);
            return "success";
        }
        else {
            return "fail";
        }
    }

    @Override
    public String deleteReview(ReviewDTO reviewDTO) {
        Optional<Account> account = accountRepository.findById(reviewDTO.getAccountId());
        Optional<Place> place = placeRepository.findById(reviewDTO.getPlaceId());
        Review review = Review.builder()
                .id(reviewDTO.getId())
                .comment(reviewDTO.getComment())
                .rating(reviewDTO.getRating())
                .build();
        if (account.isPresent() && place.isPresent()) {
            account.get().deleteReview(review);
            place.get().deleteReview(review);
            accountRepository.save(account.get());
            placeRepository.save(place.get());
            reviewRepository.delete(review);
            return "success";
        }
        else {
            return "fail";
        }
    }
}
