package com.moviesDB.controllerTDD;

import com.moviesDB.repository.UserMovieRepository;
import com.moviesDB.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class MovieControllerTDD {

    MovieService movieService;
    UserMovieRepository userMovieRepository;

    public MovieControllerTDD(MovieService movieService, UserMovieRepository userMovieRepository) {
        this.movieService = movieService;
        this.userMovieRepository = userMovieRepository;
    }

    public MovieControllerTDD() {
    }

    @GetMapping("/v1/configuration")
    public HashMap<String, Object> getConfiguration() {
        return movieService.getConfig();
    }
}
