package com.moviedb.demo.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Service
public class MovieDbService {
    WebClient webClient = WebClient.create("https://api.themoviedb.org/3/");
    @Value("${themoviedatabase.api_key}")
    private String api_key;
    @Cacheable("configuration")
    public HashMap<String, Object> getConfig() {

        /*try {
            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/


        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("configuration")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
    @Cacheable("genres")
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
    @Cacheable("popular")
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
    @Cacheable("top")
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
    @Cacheable("movie")
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
    @Cacheable("credits")
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
    @Cacheable("images")
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
    @Cacheable("keywords")
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
    @Cacheable("recommendations")
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
    @Cacheable("similar")
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
