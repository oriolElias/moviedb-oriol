package com.moviesDB;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class MovieDBServiceTest {

	@Autowired
	private WebTestClient webTestClient;

	private int id = 550;

	@Test
	void getConfigTest() {
		webTestClient.get().uri("/api/configuration").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isOk().expectHeader().valueEquals("Content-Type", "application/json").expectBody().jsonPath("$.images")
				.exists();
	}

	@Test
	void getGenreTest() {
		webTestClient.get().uri("/api/genre/list").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectHeader().valueEquals("Content-Type", "application/json").expectBody().jsonPath("$.genres")
				.exists();
	}

	@Test
	void getPopularTest() {
		webTestClient.get().uri("/api/movie/popular").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isOk().expectHeader().valueEquals("Content-Type", "application/json").expectBody()
				.jsonPath("$.results").exists();
	}

	@Test
	void getTopRatedTest() {
		webTestClient.get().uri("/api/movie/topRated").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isOk().expectHeader().valueEquals("Content-Type", "application/json").expectBody()
				.jsonPath("$.results").exists();
	}

	@Test
	void getMovieIdTest() {
		webTestClient.get().uri("/api/movie/" + id).headers(HttpHeaders -> HttpHeaders.setBasicAuth("user", "password"))
				.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectHeader()
				.valueEquals("Content-Type", "application/json");
	}

	@Test
	void getSimilarTest() {
		webTestClient.get().uri("/api/movie/" + id + "/similar")
				.headers(HttpHeaders -> HttpHeaders.setBasicAuth("user", "password")).accept(MediaType.APPLICATION_JSON)
				.exchange().expectStatus().isOk().expectHeader().valueEquals("Content-Type", "application/json");
	}

	@Test
	void getRecommendationTest() {
		webTestClient.get().uri("/api/movie/" + id + "/recommendations")
				.headers(HttpHeaders -> HttpHeaders.setBasicAuth("user", "password")).accept(MediaType.APPLICATION_JSON)
				.exchange().expectStatus().isOk().expectHeader().valueEquals("Content-Type", "application/json")
				.expectBody().jsonPath("$.results").exists();
	}
	
	@Test
	void getKeywordsTest() {
		webTestClient.get().uri("/api/movie/" + id + "/keywords")
				.headers(HttpHeaders -> HttpHeaders.setBasicAuth("user", "password")).accept(MediaType.APPLICATION_JSON)
				.exchange().expectStatus().isOk().expectHeader().valueEquals("Content-Type", "application/json")
				.expectBody().jsonPath("$.keywords").exists();
	}
	@Test
	void getImagesTest() {
		webTestClient.get().uri("/api/movie/" + id + "/images")
				.headers(HttpHeaders -> HttpHeaders.setBasicAuth("user", "password")).accept(MediaType.APPLICATION_JSON)
				.exchange().expectStatus().isOk().expectHeader().valueEquals("Content-Type", "application/json")
				.expectBody().jsonPath("$.posters").exists();
	}
	
	@Test
	void getcreditsTest() {
		webTestClient.get().uri("/api/movie/" + id + "/credit")
				.headers(HttpHeaders -> HttpHeaders.setBasicAuth("user", "password")).accept(MediaType.APPLICATION_JSON)
				.exchange().expectStatus().isOk().expectHeader().valueEquals("Content-Type", "application/json")
				.expectBody().jsonPath("$.cast").exists();
	}

}
