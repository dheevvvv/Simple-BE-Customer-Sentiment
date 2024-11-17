package com.example.enigma.customer_sentiment.analyzer;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SentimentAnalyzer {

    private static final List<String> POSITIVE_KEYWORDS = Arrays.asList("good", "great", "excellent", "amazing", "love", "best");
    private static final List<String> NEGATIVE_KEYWORDS = Arrays.asList("bad", "terrible", "worst", "hate", "poor", "awful");

    public String analyzeSentiment(String review) {
        String lowerCaseReview = review.toLowerCase();
        boolean containsPositive = POSITIVE_KEYWORDS.stream().anyMatch(lowerCaseReview::contains);
        boolean containsNegative = NEGATIVE_KEYWORDS.stream().anyMatch(lowerCaseReview::contains);

        if (containsPositive && !containsNegative) {
            return "positive";
        } else if (containsNegative && !containsPositive) {
            return "negative";
        } else {
            return "neutral";
        }
    }
}