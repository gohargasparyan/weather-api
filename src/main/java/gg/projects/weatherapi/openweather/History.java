package gg.projects.weatherapi.openweather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class History {

    @Getter
    @Setter
    private Integer cnt;

    @Getter
    @Setter
    private List<Weather> list;
}
