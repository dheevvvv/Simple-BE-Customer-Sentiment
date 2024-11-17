package com.example.enigma.customer_sentiment.service.impl;

import com.example.enigma.customer_sentiment.analyzer.SentimentAnalyzer;
import com.example.enigma.customer_sentiment.dto.CommonResponse;
import com.example.enigma.customer_sentiment.dto.PagingResponse;
import com.example.enigma.customer_sentiment.dto.request.FeedbackRequest;
import com.example.enigma.customer_sentiment.dto.response.FeedbackResponse;
import com.example.enigma.customer_sentiment.entity.Feedback;
import com.example.enigma.customer_sentiment.repository.FeedbackRepository;
import com.example.enigma.customer_sentiment.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private SentimentAnalyzer sentimentAnalyzer;

    @Override
    public CommonResponse<FeedbackResponse> addFeedback(FeedbackRequest feedbackRequest) {
        String sentiment = sentimentAnalyzer.analyzeSentiment(feedbackRequest.getReview());
        Feedback feedback = new Feedback(null, feedbackRequest.getReview(), sentiment);
        feedbackRepository.save(feedback);

        FeedbackResponse feedbackDTO = new FeedbackResponse(feedback.getReview(), feedback.getSentiment());
        return new CommonResponse<>(200, "Feedback added successfully", feedbackDTO, null);
    }

    @Override
    public CommonResponse<List<FeedbackResponse>> getFeedbackBySentiment(String sentiment) {
        List<Feedback> feedbackList = feedbackRepository.findFeedbackBySentiment(sentiment);
        List<FeedbackResponse> feedbackDTOList = feedbackList.stream()
                .map(fb -> new FeedbackResponse(fb.getReview(), fb.getSentiment()))
                .collect(Collectors.toList());
        return new CommonResponse<>(200, "Success", feedbackDTOList, null);
    }

    @Override
    public CommonResponse<Long> countFeedbackBySentiment(String sentiment) {
        Long count = feedbackRepository.countFeedbackBySentiment(sentiment);
        return new CommonResponse<>(200, "Success", count, null);
    }

    @Override
    public CommonResponse<List<FeedbackResponse>> getFeedbackBySentimentPaged(String sentiment, int page, int size) {
        var pageable = PageRequest.of(page, size);
        var feedbackPage = feedbackRepository.findFeedbackBySentimentPaged(sentiment, pageable);
        List<FeedbackResponse> feedbackDTOList = feedbackPage.getContent().stream()
                .map(fb -> new FeedbackResponse(fb.getReview(), fb.getSentiment()))
                .collect(Collectors.toList());
        PagingResponse pagingResponse = new PagingResponse(
                feedbackPage.getTotalElements(),
                feedbackPage.getTotalPages(),
                feedbackPage.getNumber(),
                feedbackPage.getSize()
        );
        return new CommonResponse<>(200, "Success", feedbackDTOList, pagingResponse);
    }
}