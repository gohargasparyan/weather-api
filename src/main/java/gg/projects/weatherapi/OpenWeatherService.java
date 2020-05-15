package gg.projects.weatherapi;

import gg.projects.weatherapi.domain.History;
import gg.projects.weatherapi.domain.Weather;
import gg.projects.weatherapi.openweather.Condition;
import gg.projects.weatherapi.openweather.OpenWeatherClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OpenWeatherService {

    private final OpenWeatherClient openWeatherClient;
    private static final double toCelsius = 273.15;

    public OpenWeatherService(OpenWeatherClient openWeatherClient) {
        this.openWeatherClient = openWeatherClient;
    }

    Weather getCurrentWeather(String location) {
        return mapResponseToWeather(openWeatherClient.requestCurrentWeather(location), false);
    }

    History getHistoricalWeather(String location) {
        return mapResponseToHistory(openWeatherClient.requestHistoricalWeather(location));
    }

    private Weather mapResponseToWeather(gg.projects.weatherapi.openweather.Weather response, boolean toCelsius) {
        boolean umbrella = false;
        if (response.getWeather().size() > 0) {
            umbrella = Condition.umbrella(response.getWeather().get(0).getMain());
        } else {
            log.warn("Couldn't fetch weather condition, setting umbrella to false");
        }

        double tmp = toCelsius ? toCelsius(response.getMain().getTemp()) : response.getMain().getTemp();
        return new Weather(tmp, response.getMain().getPressure(), umbrella);
    }

    private History mapResponseToHistory(gg.projects.weatherapi.openweather.History response) {
        int cnt = response.getCnt() < 5 ? response.getCnt() : 5;
        List<Weather> history = new ArrayList<>();
        double avgTmp = 0;
        double avgPressure = 0;
        for (int i = 0; i < cnt; i++) {
            gg.projects.weatherapi.openweather.Weather weather = response.getList().get(i);
            history.add(mapResponseToWeather(weather, true));
            avgTmp = avgTmp + weather.getMain().getTemp();
            avgPressure = avgPressure + weather.getMain().getPressure();
        }

        avgTmp = avgTmp / cnt;
        avgPressure = avgPressure / cnt;

        return new History(toCelsius(avgTmp), toCelsius(avgPressure), history);
    }

    private static double toCelsius(double kelvin) {
        BigDecimal bigDecimal = new BigDecimal(kelvin - toCelsius).setScale(2, RoundingMode.UP);
        return bigDecimal.doubleValue();
    }

}
