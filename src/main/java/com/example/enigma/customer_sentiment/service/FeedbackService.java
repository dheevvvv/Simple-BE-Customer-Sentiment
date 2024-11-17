package com.example.enigma.customer_sentiment.service;

import com.example.enigma.customer_sentiment.dto.CommonResponse;
import com.example.enigma.customer_sentiment.dto.request.FeedbackRequest;
import com.example.enigma.customer_sentiment.dto.response.FeedbackResponse;

import java.util.List;

public interface FeedbackService {

    // Menambahkan feedback baru
    CommonResponse<FeedbackResponse> addFeedback(FeedbackRequest feedbackRequest);

    // Mendapatkan feedback berdasarkan sentimen tertentu
    CommonResponse<List<FeedbackResponse>> getFeedbackBySentiment(String sentiment);

    // Mendapatkan jumlah feedback berdasarkan sentimen tertentu
    CommonResponse<Long> countFeedbackBySentiment(String sentiment);

    // Mendapatkan feedback dengan paginasi berdasarkan sentimen tertentu
    CommonResponse<List<FeedbackResponse>> getFeedbackBySentimentPaged(String sentiment, int page, int size);
}