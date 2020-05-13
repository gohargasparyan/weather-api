package gg.projects.weatherapi;

import gg.projects.weatherapi.domain.Weather;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherService {

    private final OpenWeatherConfig openWeatherConfig;

    public OpenWeatherService(OpenWeatherConfig openWeatherConfig) {
        this.openWeatherConfig = openWeatherConfig;
    }

    Weather getCurrentWeather(String location) {
        return new Weather(1, 1, true);
    }


}
