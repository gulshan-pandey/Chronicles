package com.app.Chronicles.service;


import com.app.Chronicles.api.response.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    public  static final String Apikey ="06094b9adee861c25642a17a046c2650";
    public  static final String url ="https://api.weatherstack.com/current?access_key=KEY&query=CITY";


    @Autowired
   private RestTemplate restTemplate;


    public Weather getWeather(String city) {
        String finalApi = url.replace("CITY", city).replace("KEY", Apikey);

        ResponseEntity<Weather> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, Weather.class);
        // the process of converting JSON response into corresponding POJO is called deserialization

        return response.getBody();
    }


}
