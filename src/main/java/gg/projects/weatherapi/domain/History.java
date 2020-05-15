package gg.projects.weatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class History {

    @Getter
    @Setter
    private double avgTemp;

    @Getter @Setter
    private double avgPressure;

    @Getter @Setter
    List<Weather> history;

}
