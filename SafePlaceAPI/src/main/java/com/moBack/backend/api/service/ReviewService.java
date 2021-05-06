package com.moBack.backend.api.service;

import com.moBack.backend.api.dto.ReviewDTO;

public interface ReviewService {

    public String addReview(ReviewDTO reviewDTO);
    public String deleteReview(ReviewDTO reviewDTO);
}
