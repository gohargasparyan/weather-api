package gg.projects.weatherapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openweather")
public class OpenWeatherConfig {

    @Getter @Setter
    private String apiKey;

    @Getter @Setter
    private String currentUrl;

    @Getter @Setter
    private String historyUrl;

    public String getCurrentWeatherUrl(String location) {
        return String.format("%s?q=%s&appid=%s&units=metric", currentUrl, location, apiKey);
    }

    public String getHistoricalWeatherUrl(String location) {
        return historyUrl;
    }
}
