package com.moviedb.demo.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Service
public class MovieDbService {
    WebClient webClient = WebClient.create("https://api.themoviedb.org/3/");
    @Value("${themoviedatabase.api_key}")
    private String api_key;

    public HashMap<String, Object> getConfig() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("configuration")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
    public HashMap<String, Object> getAllGenres() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("genre/movie/list")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
    public HashMap<String, Object> getPopular() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/popular")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
    public HashMap<String, Object> getTopRated() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/top_rated")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
    public HashMap<String, Object> getMovieById(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString())
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
    public HashMap<String, Object> getCastAndCrew(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString()+"/credits")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
    public HashMap<String, Object> getAllImages(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString()+"/images")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
    public HashMap<String, Object> getKeywords(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString()+"/keywords")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
    public HashMap<String, Object> getRecommendations(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString()+"/recommendations")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
    public HashMap<String, Object> getSimilarMovies(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/"+id.toString()+"/similar")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }


}
