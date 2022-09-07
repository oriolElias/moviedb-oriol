package com.moviedb.demo.Controller;

import com.moviedb.demo.Entity.UserMovie;
import com.moviedb.demo.Repository.UserMovieRepository;
import com.moviedb.demo.Service.MovieDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class MovieDbController {
    @Autowired
    MovieDbService movieDbService;

    @Autowired
    UserMovieRepository userMovieRepository;

    @GetMapping("/api/configuration")
    public HashMap<String, Object> getConfiguration() {
        HashMap<String, Object> config = movieDbService.getConfig();

        return config;
    }
    @GetMapping("/api/genre/movie/list")
    public HashMap<String, Object> getAllGenres() {
        HashMap<String, Object> config = movieDbService.getAllGenres();

        return config;
    }
    @GetMapping("api/movie/popular")
    public HashMap<String, Object> getPopularMovies(){
        HashMap<String, Object> config = movieDbService.getPopular();

        return config;
    }
    @GetMapping("api/movie/top_rated")
    public HashMap<String, Object> getTopRated(){
        HashMap<String, Object> config = movieDbService.getTopRated();

        return config;
    }
    @GetMapping("api/movie/{movie_id}")
    public HashMap<String, Object> getMovieById(@AuthenticationPrincipal UserDetails user, @PathVariable Integer movie_id){

        UserMovie userMovie = userMovieRepository.findByUsernameAndMovie(user.getUsername(),movie_id.toString()).orElse(null);
        HashMap<String, Object> movie = movieDbService.getMovieById(movie_id);

        if(userMovie != null){
            movie.put("favorite",userMovie.getFavorite());
            movie.put("personal_rating",userMovie.getPersonal_rating());
            movie.put("notes",userMovie.getNotes());
        }

        return movie;
    }

    @PatchMapping("api/movie/{movie_id}")
    public ResponseEntity<UserMovie> putFavoritePersonalRatingNotes(@PathVariable Integer movie_id, @RequestBody UserMovie newUserMovie,@AuthenticationPrincipal UserDetails user){

        //UserMovie updatedMovie = userMovieRepository.findById(movie_id).orElse(null);//patchUserMovie(movie_id,UserMovie);
        UserMovie updatedMovie = userMovieRepository.findByUsernameAndMovie(user.getUsername(),movie_id.toString()).orElse(null);
        if(updatedMovie == null){
            updatedMovie = new UserMovie();
        }
        updatedMovie.setUsername(user.getUsername());
        updatedMovie.setMovie(movie_id.toString());
        updatedMovie.setFavorite(newUserMovie.getFavorite());
        updatedMovie.setPersonal_rating(newUserMovie.getPersonal_rating());
        updatedMovie.setNotes((newUserMovie.getNotes()));

        userMovieRepository.save(updatedMovie);

        return new ResponseEntity<UserMovie>(updatedMovie, HttpStatus.OK);
    }

    @GetMapping("api/movie/{movie_id}/credits")
    public HashMap<String, Object> getCastAndCrew(@PathVariable Integer movie_id){
        HashMap<String, Object> config = movieDbService.getCastAndCrew(movie_id);

        return config;
    }
    @GetMapping("api/movie/{movie_id}/images")
    public HashMap<String, Object> getImages(@PathVariable Integer movie_id){
        HashMap<String, Object> config = movieDbService.getAllImages(movie_id);

        return config;
    }
    @GetMapping("api/movie/{movie_id}/keywords")
    public HashMap<String, Object> getKeywords(@PathVariable Integer movie_id){
        HashMap<String, Object> config = movieDbService.getKeywords(movie_id);

        return config;
    }
    @GetMapping("api/movie/{movie_id}/recommendations")
    public HashMap<String, Object> getRecommendations(@PathVariable Integer movie_id){
        HashMap<String, Object> config = movieDbService.getRecommendations(movie_id);

        return config;
    }
    @GetMapping("api/movie/{movie_id}/similar")
    public HashMap<String, Object> getSimilar(@PathVariable Integer movie_id){
        HashMap<String, Object> config = movieDbService.getSimilarMovies(movie_id);

        return config;
    }


}
