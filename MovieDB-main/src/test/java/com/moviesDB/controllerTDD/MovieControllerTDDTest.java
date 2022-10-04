package com.moviesDB.controllerTDD;

import com.moviesDB.repository.UserMovieRepository;
import com.moviesDB.services.MovieService;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class MovieControllerTDDTest {

    MovieControllerTDD movieControllerTDD;
    MovieService movieService;
    UserMovieRepository userMovieRepository;




    @BeforeEach
    void setUp(){
        movieService = mock(MovieService.class);
        userMovieRepository = mock(UserMovieRepository.class);
        movieControllerTDD = new MovieControllerTDD(movieService,userMovieRepository);
    }

    @Test
    void getConfiguration(){

        HashMap<String, Object> resultFromGetConfig = new HashMap<>();
        resultFromGetConfig.put("images",0);
        resultFromGetConfig.put("change_keys",0);

        given(movieService.getConfig()).willReturn(resultFromGetConfig);

        assertEquals(resultFromGetConfig,movieControllerTDD.getConfiguration());
    }

}