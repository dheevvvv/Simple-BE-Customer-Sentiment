package com.example.enigma.customer_sentiment.service;

import com.example.enigma.customer_sentiment.analyzer.SentimentAnalyzer;
import com.example.enigma.customer_sentiment.dto.CommonResponse;
import com.example.enigma.customer_sentiment.dto.request.FeedbackRequest;
import com.example.enigma.customer_sentiment.dto.response.FeedbackResponse;
import com.example.enigma.customer_sentiment.entity.Feedback;
import com.example.enigma.customer_sentiment.repository.FeedbackRepository;
import com.example.enigma.customer_sentiment.service.impl.FeedbackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FeedbackServiceImplTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private SentimentAnalyzer sentimentAnalyzer;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFeedback() {
        String review = "This product is great!";
        when(sentimentAnalyzer.analyzeSentiment(review)).thenReturn("positive");

        Feedback feedback = new Feedback(null, review, "positive");
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        FeedbackRequest request = new FeedbackRequest(review);
        CommonResponse<FeedbackResponse> response = feedbackService.addFeedback(request);

        assertEquals(200, response.getStatus());
        assertEquals("positive", response.getData().getSentiment());
    }

    @Test
    void testGetFeedbackBySentiment() {
        String sentiment = "positive";
        List<Feedback> feedbackList = List.of(new Feedback(1L, "Great product", sentiment));
        when(feedbackRepository.findFeedbackBySentiment(sentiment)).thenReturn(feedbackList);

        CommonResponse<List<FeedbackResponse>> response = feedbackService.getFeedbackBySentiment(sentiment);

        assertEquals(200, response.getStatus());
        assertEquals(1, response.getData().size());
    }

    @Test
    void testCountFeedbackBySentiment() {
        String sentiment = "positive";
        when(feedbackRepository.countFeedbackBySentiment(sentiment)).thenReturn(5L);

        CommonResponse<Long> response = feedbackService.countFeedbackBySentiment(sentiment);

        assertEquals(200, response.getStatus());
        assertEquals(5L, response.getData());
    }

    @Test
    void testGetFeedbackBySentimentPaged() {
        String sentiment = "neutral";
        PageRequest pageable = PageRequest.of(0, 2);
        List<Feedback> feedbackList = List.of(new Feedback(1L, "Okay product", sentiment));
        when(feedbackRepository.findFeedbackBySentimentPaged(sentiment, pageable))
                .thenReturn(new PageImpl<>(feedbackList, pageable, 1));

        CommonResponse<List<FeedbackResponse>> response = feedbackService.getFeedbackBySentimentPaged(sentiment, 0, 2);

        assertEquals(200, response.getStatus());
        assertEquals(1, response.getData().size());
    }
}