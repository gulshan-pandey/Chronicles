package com.app.Chronicles.service;


import com.app.Chronicles.api.response.Quotes;
import com.app.Chronicles.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuoteService {


    @Autowired
    private RestTemplate restTemplate;

    public Quotes getQuote() {
        List<Quotes> quotes = restTemplate.exchange(
                Placeholders.QUOTE_API,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Quotes>>() {}
        ).getBody();

        return quotes != null && !quotes.isEmpty() ? quotes.get(0) : null;
    }
}
