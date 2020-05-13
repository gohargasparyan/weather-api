package gg.projects.weatherapi;

import gg.projects.weatherapi.domain.Weather;
import gg.projects.weatherapi.openweather.Condition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OpenWeatherService {

    private final OpenWeatherConfig openWeatherConfig;
    private final RestTemplate restTemplate;

    public OpenWeatherService(OpenWeatherConfig openWeatherConfig, RestTemplate restTemplate) {
        this.openWeatherConfig = openWeatherConfig;
        this.restTemplate = restTemplate;
    }

    Weather getCurrentWeather(String location) {
        gg.projects.weatherapi.openweather.Weather response
                = restTemplate.getForObject(openWeatherConfig.getCurrentWeatherUrl(location), gg.projects.weatherapi.openweather.Weather.class);
        return mapResponseToWeather(response);
    }

    private Weather mapResponseToWeather(gg.projects.weatherapi.openweather.Weather response) {
        gg.projects.weatherapi.openweather.Weather.Main main = response.getMain();
        boolean umbrella = false;
        if (response.getWeather().size() > 0) {
            gg.projects.weatherapi.openweather.Weather.WeatherShort weatherShort = response.getWeather().get(0);
            umbrella = Condition.umbrella(weatherShort.getMain());
        } else {
            log.warn("Couldn't fetch weather condition, setting umbrella to false");
        }

        return new Weather(main.getTemp(), main.getPressure(), umbrella);
    }


}
