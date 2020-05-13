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
}
