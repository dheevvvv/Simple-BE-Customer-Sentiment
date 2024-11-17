package com.example.enigma.customer_sentiment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CommonResponse<T> {
    private Integer status;
    private String message;
    private T data;
    private PagingResponse paging;
}
