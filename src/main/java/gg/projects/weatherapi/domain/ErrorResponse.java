package gg.projects.weatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ErrorResponse {

    @Getter @Setter
    private int code;
    @Getter @Setter
    private String message;

}
