package com.example.enigma.customer_sentiment.dto.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackResponse {
    private String review;
    private String sentiment;
}