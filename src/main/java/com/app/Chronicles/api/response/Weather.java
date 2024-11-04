package com.app.Chronicles.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Weather{

    private Current current;

    @Getter
    @Setter
    public class Current{
        int temperature;
        @JsonProperty("weather_descriptions")
        ArrayList<String> weatherDescriptions;
        int humidity;
        int feelslike;
    }




}
