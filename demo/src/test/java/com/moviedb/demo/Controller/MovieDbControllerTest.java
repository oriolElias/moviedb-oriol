package com.moviedb.demo.Controller;

import com.moviedb.demo.Service.MovieDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class MovieDbControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieDbService movieDbService;

    @Test
    void getConfiguration() throws Exception {

        HashMap<String, Object> resultFromGetConfig = new HashMap<>();
        resultFromGetConfig.put("images",0);
        resultFromGetConfig.put("change_keys",0);


        given(movieDbService.getConfig()).willReturn(resultFromGetConfig);

        ResultActions response = mockMvc.perform(get("/api/configuration"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.images",is(0)))
                .andExpect(jsonPath("$.change_keys",is(0)))
        ;


    }

    @Test
    void getAllGenres() throws Exception {
        HashMap<String, Object> resultFromGetConfig = new HashMap<>();
        resultFromGetConfig.put("genres",0);


        given(movieDbService.getAllGenres()).willReturn(resultFromGetConfig);

        ResultActions response = mockMvc.perform(get("/api/genre/movie/list"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.genres",is(0)))

        ;
    }

    @Test
    void getPopularMovies() throws Exception {
        HashMap<String, Object> resultFromGetConfig = new HashMap<>();
        resultFromGetConfig.put("page",1);
        resultFromGetConfig.put("total_pages",35032);
        resultFromGetConfig.put("results",0);
        resultFromGetConfig.put("total_results",35032);



        given(movieDbService.getPopular()).willReturn(resultFromGetConfig);

        ResultActions response = mockMvc.perform(get("/api/movie/popular"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.page",is(1)))
                .andExpect(jsonPath("$.total_pages",is(35032)))
                .andExpect(jsonPath("$.results",is(0)))
                .andExpect(jsonPath("$.total_results",is(35032)))
        ;
    }

    @Test
    void getTopRated() throws Exception {
        HashMap<String, Object> resultFromGetConfig = new HashMap<>();
        resultFromGetConfig.put("page",1);
        resultFromGetConfig.put("total_pages",35032);
        resultFromGetConfig.put("results",0);
        resultFromGetConfig.put("total_results",35032);



        given(movieDbService.getTopRated()).willReturn(resultFromGetConfig);

        ResultActions response = mockMvc.perform(get("/api/movie/top_rated"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.page",is(1)))
                .andExpect(jsonPath("$.total_pages",is(35032)))
                .andExpect(jsonPath("$.results",is(0)))
                .andExpect(jsonPath("$.total_results",is(35032)))
        ;
    }

    @Test
    void getMovieById() {
    }

    @Test
    void putFavoritePersonalRatingNotes() {
    }

    @Test
    void getCastAndCrew() {
    }

    @Test
    void getImages() {
    }

    @Test
    void getKeywords() {
    }

    @Test
    void getRecommendations() {
    }

    @Test
    void getSimilar() {
    }
}