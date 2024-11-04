package com.app.Chronicles.service;


import com.app.Chronicles.api.response.Weather;
import com.app.Chronicles.cache.AppCache;
import com.app.Chronicles.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String Apikey ;

    @Autowired
    AppCache appCache;

    @Autowired
   private RestTemplate restTemplate;


    public Weather getWeather(String city) {
        String finalApi = appCache.appCache.get(AppCache.Keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, Apikey);

        ResponseEntity<Weather> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, Weather.class);
        // the process of converting JSON response into corresponding POJO is called deserialization

        return response.getBody();
    }



}