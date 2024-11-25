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
   private RestTemplate restTemplate;           // for making API calls and handling the JSON responses

    @Autowired
    private RedisService redisService;

    public Weather getWeather(String city) {
        Weather weather = redisService.get(city, Weather.class);
        if(weather != null){
            return weather;             // returning the weather object from cache(redis)
        }else{
        String finalApi = appCache.cache.get(AppCache.Keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.WEATHER_API_KEY, Apikey);

        ResponseEntity<Weather> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, Weather.class);
        // the process of converting JSON response into corresponding POJO is called deserialization
            Weather body = response.getBody();          // getting the response body
            if(body != null){
                redisService.set(city,body,1l);
            }
            return body;
        }
    }


}