package com.example.enigma.customer_sentiment.repository;

import com.example.enigma.customer_sentiment.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    @Query(value = "SELECT * FROM feedback WHERE sentiment = :sentiment", nativeQuery = true)
    List<Feedback> findFeedbackBySentiment(String sentiment);

    @Query(value = "SELECT COUNT(*) FROM feedback WHERE sentiment = :sentiment", nativeQuery = true)
    Long countFeedbackBySentiment(String sentiment);

    @Query(value = "SELECT * FROM feedback WHERE sentiment = :sentiment", nativeQuery = true,
            countQuery = "SELECT COUNT(*) FROM feedback WHERE sentiment = :sentiment")
    Page<Feedback> findFeedbackBySentimentPaged(String sentiment, Pageable pageable);
}