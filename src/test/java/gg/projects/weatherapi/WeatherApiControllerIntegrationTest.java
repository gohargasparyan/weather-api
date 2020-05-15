package gg.projects.weatherapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeatherApiControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCurrentWeather_ResponseOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/current").param("location", "Berlin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.temp").exists())
                .andExpect(jsonPath("$.pressure").exists())
                .andExpect(jsonPath("$.umbrella").exists());
    }

    @Test
    public void getCurrentWeather_ResponseNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/current").param("location", "nonexistingcity"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void getHistoricalWeather_ResponseOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/history").param("location", "Berlin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.avgTemp").exists())
                .andExpect(jsonPath("$.avgPressure").exists())
                .andExpect(jsonPath("$.history").exists());
    }
}
