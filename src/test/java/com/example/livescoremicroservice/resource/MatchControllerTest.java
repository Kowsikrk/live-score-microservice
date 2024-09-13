package com.example.livescoremicroservice.resource;

import com.example.livescoremicroservice.model.Match;
import com.example.livescoremicroservice.service.MatchService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MatchController.class)
public class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchService matchService;

    @Test
    public void testGetMatchById() throws Exception {
        Match match = new Match(1L, "India", "Australia", 250, 230, "Live");

        Mockito.when(matchService.getMatchById(1L)).thenReturn(match);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/matches/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teamA").value("India"))
                .andExpect(jsonPath("$.scoreTeamA").value(250));
    }
}
