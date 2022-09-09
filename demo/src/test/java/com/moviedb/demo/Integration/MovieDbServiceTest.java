package com.moviedb.demo.Integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class MovieDbServiceTest {
    @Autowired
    private WebTestClient webTestClient;

    private Integer idTest=550;


    @Test
    void getConfigBody(){
        webTestClient.get()
                .uri("/api/configuration")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.images").exists()
                .jsonPath("$.change_keys").exists()
        ;
    }

    @Test
    void getAllGenres(){
        webTestClient.get()
                .uri("/api/genre/movie/list")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.genres").exists()
        ;
    }

    @Test
    void getPopular(){
        webTestClient.get()
                .uri("/api/movie/popular")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.page").exists()
                .jsonPath("$.total_pages").exists()
                .jsonPath("$.results").exists()
                .jsonPath("$.total_results").exists()
        ;
    }

    @Test
    void getTopRated(){
        webTestClient.get()
                .uri("/api/movie/top_rated")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.page").exists()
                .jsonPath("$.total_pages").exists()
                .jsonPath("$.results").exists()
                .jsonPath("$.total_results").exists()
        ;
    }

    @Test
    void getMovieById(){
        webTestClient.get()
                .uri("/api/movie/"+idTest)
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
        ;
    }

    @Test
    void getCastAndCrew(){
        webTestClient.get()
                .uri("/api/movie/"+idTest+"/credits")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.cast").exists()
                .jsonPath("$.id").exists()
                .jsonPath("$.crew").exists()
        ;
    }

    @Test
    void getAllImages(){
        webTestClient.get()
                .uri("/api/movie/"+idTest+"/images")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
                .expectBody()
                .jsonPath("$.backdrops").exists()
                .jsonPath("$.id").exists()
                .jsonPath("$.posters").exists()
                .jsonPath("$.logos").exists()
        ;
    }

    @Test
    void getKeywords(){
        webTestClient.get()
                .uri("/api/movie/"+idTest+"/keywords")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
        ;
    }

    @Test
    void getRecommendations(){
        webTestClient.get()
                .uri("/api/movie/"+idTest+"/recommendations")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
        ;
    }

    @Test
    void getSimilarMovies(){
        webTestClient.get()
                .uri("/api/movie/"+idTest+"/similar")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user","password"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type","application/json")
        ;
    }





}