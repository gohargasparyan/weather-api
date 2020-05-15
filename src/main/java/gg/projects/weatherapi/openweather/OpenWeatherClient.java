package gg.projects.weatherapi.openweather;

import gg.projects.weatherapi.OpenWeatherConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherClient {

    private final OpenWeatherConfig openWeatherConfig;
    private final RestTemplate restTemplate;

    public OpenWeatherClient(OpenWeatherConfig openWeatherConfig, RestTemplate restTemplate) {
        this.openWeatherConfig = openWeatherConfig;
        this.restTemplate = restTemplate;
    }

    public Weather requestCurrentWeather(String location) {
        return restTemplate.getForObject(openWeatherConfig.getCurrentWeatherUrl(location), Weather.class);
    }

    public History requestHistoricalWeather(String location) {
        return restTemplate.getForObject(openWeatherConfig.getHistoricalWeatherUrl(location), History.class);
    }
}
