package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

@RestController
public class WeatherController {

    @RequestMapping(value = "/getTemp", method = RequestMethod.GET)
    public String getTemperature1(@RequestParam(value = "coord", defaultValue = "37.8267,-122.4233") String coord) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        MapType type =  mapper.getTypeFactory().constructMapType(
                Map.class, String.class,Object.class);
        URL url = new URL("https://api.darksky.net/forecast/ac3fe411198e681a8d9c06a88859dc2a/"+ coord);
        InputStream is= url.openStream();
        String str= IOUtils.toString(is, "UTF-8");
        Map<String,Object> map= mapper.readValue(str, type);
        double latitude =(double) map.get("latitude");
        double longitude =(double) map.get("longitude");
        Map<String, Object> currently =(Map<String, Object>) map.get("currently");
        double temperature =(double) currently.get("temperature");
        Map<String, Object> daily =(Map<String, Object>) map.get("hourly");
        String summary =(String) daily.get("summary");
        return "latitude: " + latitude +" Longitude: "+ longitude+ "\n" + summary
                +" currently temperature is "+ temperature;
    }
//    @RequestMapping(value = "/getTemp", method = RequestMethod.GET)
//    public String getTemperature() throws Exception{
//        ObjectMapper mapper = new ObjectMapper();
//        MapType type =  mapper.getTypeFactory().constructMapType(
//                Map.class, String.class,Object.class);
//        URL url = new URL("https://api.darksky.net/forecast/ac3fe411198e681a8d9c06a88859dc2a/37.8267,-122.4233");
//        InputStream is= url.openStream();
//        String str= IOUtils.toString(is, "UTF-8");
//        Map<String,Object> map= mapper.readValue(str, type);
//        double latitude =(double) map.get("latitude");
//        double longitude =(double) map.get("longitude");
//        Map<String, Object> currently =(Map<String, Object>) map.get("currently");
//        double temperature =(double) currently.get("temperature");
//        Map<String, Object> daily =(Map<String, Object>) map.get("hourly");
//        String summary =(String) daily.get("summary");
//        return "latitude: " + latitude +" Longitude: "+ longitude+ "\n" + summary
//                +" currently temperature is "+ temperature;
//    }
}
