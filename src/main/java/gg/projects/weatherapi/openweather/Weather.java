package gg.projects.weatherapi.openweather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Getter
    @Setter
    private Main main;

    @Getter
    @Setter
    private List<WeatherShort> weather;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Main {

        @Getter @Setter
        private double temp;
        @Getter @Setter
        private double pressure;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class WeatherShort {

        @Getter @Setter
        private String main;

    }

}
