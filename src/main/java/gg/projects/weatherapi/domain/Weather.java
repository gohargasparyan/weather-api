package gg.projects.weatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class Weather {

    @Getter @Setter
    private double temp;
    @Getter @Setter
    private double pressure;
    @Getter @Setter
    private boolean umbrella;

}
