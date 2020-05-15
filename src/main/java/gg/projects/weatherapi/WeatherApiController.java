package gg.projects.weatherapi;

import gg.projects.weatherapi.domain.History;
import gg.projects.weatherapi.domain.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class WeatherApiController {

    private final OpenWeatherService openWeatherService;

    public WeatherApiController(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    @GetMapping("/current")
    public ResponseEntity<Weather> current(@RequestParam String location) {
       return ResponseEntity.ok(openWeatherService.getCurrentWeather(location));
    }

    @GetMapping("/history")
    public ResponseEntity<History> history(@RequestParam String location) {
        return ResponseEntity.ok(openWeatherService.getHistoricalWeather(location));
    }
}
