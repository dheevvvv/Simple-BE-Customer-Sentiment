package com.example.enigma.customer_sentiment.controller;

import com.example.enigma.customer_sentiment.dto.CommonResponse;
import com.example.enigma.customer_sentiment.dto.request.FeedbackRequest;
import com.example.enigma.customer_sentiment.dto.response.FeedbackResponse;
import com.example.enigma.customer_sentiment.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/add")
    public CommonResponse<FeedbackResponse> addFeedback(@RequestBody FeedbackRequest feedbackRequestDTO) {
        return feedbackService.addFeedback(feedbackRequestDTO);
    }

    @GetMapping("/sentiment/{sentiment}")
    public CommonResponse<List<FeedbackResponse>> getFeedbackBySentiment(@PathVariable String sentiment) {
        return feedbackService.getFeedbackBySentiment(sentiment);
    }

    @GetMapping("/sentiment/count")
    public CommonResponse<Long> countFeedbackBySentiment(@RequestParam String sentiment) {
        return feedbackService.countFeedbackBySentiment(sentiment);
    }

    @GetMapping("/sentiment/paged")
    public CommonResponse<List<FeedbackResponse>> getFeedbackBySentimentPaged(
            @RequestParam String sentiment, @RequestParam int page, @RequestParam int size) {
        return feedbackService.getFeedbackBySentimentPaged(sentiment, page, size);
    }
}
