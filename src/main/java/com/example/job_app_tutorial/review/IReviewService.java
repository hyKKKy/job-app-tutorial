package com.example.job_app_tutorial.review;

import java.util.List;

public interface IReviewService {
    List<Review> getAllReviews(Long companyId);

    boolean addReview(Long companyId, Review review);

    Review getReview(Long companyId, Long reviewId);
}
