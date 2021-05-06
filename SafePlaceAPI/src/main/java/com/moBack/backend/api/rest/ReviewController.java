package com.moBack.backend.api.rest;

import com.moBack.backend.api.dto.ReviewDTO;
import com.moBack.backend.api.service.ReviewService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private ReviewService reviewService;

    public void ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/register")
    public String addReview(ReviewDTO reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }

    @DeleteMapping("/delete")
    public String deleteReview(ReviewDTO reviewDTO) {
        return reviewService.deleteReview(reviewDTO);
    }
}
