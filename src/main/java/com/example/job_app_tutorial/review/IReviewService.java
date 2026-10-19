package com.example.job_app_tutorial.review;

import java.util.List;

public interface IReviewService {
    List<Review> getAllReviews(Long companyId);
}
