package gg.projects.weatherapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openweather")
public class OpenWeatherConfig {

    @Getter @Setter
    private String url;

    @Getter @Setter
    private String apikey;

    @Getter @Setter
    private String current;

    public String getCurrentWeatherUrl(String location) {
        return String.format("%s%s?q=%s&appid=%s&units=metric", url, current, location, apikey);
    }
}
