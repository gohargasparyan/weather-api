package gg.projects.weatherapi;

import gg.projects.weatherapi.openweather.History;
import gg.projects.weatherapi.openweather.OpenWeatherClient;
import gg.projects.weatherapi.openweather.Weather;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherServiceTest {

    @InjectMocks
    OpenWeatherService openWeatherService;

    @Mock
    OpenWeatherClient client;

    @Test
    public void getCurrentWeather_mockClient_returns_ok() {
        double expectedTemp = 10;
        double expectedPressure = 1000;
        Weather.Main main = new Weather.Main(expectedTemp, expectedPressure);
        List<Weather.WeatherShort> weatherShorts = new ArrayList<>();
        weatherShorts.add(new Weather.WeatherShort("Thunderstorm"));
        weatherShorts.add(new Weather.WeatherShort("Clouds"));
        Weather weather = new Weather(main, weatherShorts);

        Mockito.when(client.requestCurrentWeather(Mockito.anyString())).thenReturn(weather);

        gg.projects.weatherapi.domain.Weather result = openWeatherService.getCurrentWeather("Berlin");
        assertEquals(expectedTemp, result.getTemp(), 0);
        assertEquals(expectedPressure, result.getPressure(), 0);
        assertTrue(result.isUmbrella());
    }

    @Test
    public void getCurrentWeather_mockClient_returns_client_exception() {
        Mockito.when(client.requestCurrentWeather(Mockito.anyString())).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Assertions.assertThrows(HttpClientErrorException.class, () -> openWeatherService.getCurrentWeather("Berlin"));
    }

    @Test
    public void getCurrentWeather_mockClient_returns_server_exception() {
        Mockito.when(client.requestCurrentWeather(Mockito.anyString())).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        Assertions.assertThrows(HttpServerErrorException.class, () -> openWeatherService.getCurrentWeather("Berlin"));
    }

    @Test
    public void getHistoricalWeather_mockClient_returns_ok() {
        Weather.Main main1 = new Weather.Main(279.946, 1016.76);
        Weather weather1 = new Weather(main1, Collections.singletonList(new Weather.WeatherShort("Rain")));

        Weather.Main main2 = new Weather.Main(282.597, 1012.12);
        Weather weather2 = new Weather(main2, Collections.singletonList(new Weather.WeatherShort("Rain")));

        Weather.Main main3 = new Weather.Main( 279.38, 1011);
        Weather weather3 = new Weather(main3, Collections.singletonList(new Weather.WeatherShort("Mist")));

        List<Weather> list =  Arrays.asList(weather1, weather2, weather3);
        History history = new History(3, list);

        Mockito.when(client.requestHistoricalWeather(Mockito.anyString())).thenReturn(history);

        gg.projects.weatherapi.domain.History result = openWeatherService.getHistoricalWeather("Berlin");
        assertEquals(7.5, result.getAvgTemp(), 0);
        assertEquals( 740.15, result.getAvgPressure(), 0);
        assertEquals( 3, result.getHistory().size());
    }

}
