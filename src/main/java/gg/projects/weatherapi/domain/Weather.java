package gg.projects.weatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class Weather {

    @Getter @Setter
    private Integer temp;
    @Getter @Setter
    private Integer pressure;
    @Getter @Setter
    private boolean umbrella;

}
