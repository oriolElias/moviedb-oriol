package com.moviesDB;

import java.security.Principal;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.hamcrest.core.IsNull;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviesDB.entities.UserMovie;
import com.moviesDB.repository.UserMovieRepository;
import com.moviesDB.services.MovieService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class MovieDBTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	MovieService movieService;

	@MockBean
	private UserMovieRepository userMovieRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getAllGenres() throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		result.put("genres", 0);

		given(movieService.findAllGenres()).willReturn(result);
		ResultActions actions = mockMvc.perform(get("/api/genre/list"));

		actions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.genres", is(0)));

	}

	@Test
	void getAllPopularMovies() throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		result.put("page", 0);
		result.put("total_pages", 10);
		result.put("results", 0);
		result.put("total_results", 10);

		given(movieService.findPopularMovie()).willReturn(result);
		ResultActions actions = mockMvc.perform(get("/api/movie/popular"));

		actions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.page", is(0)));
	}

	@Test
	@WithMockUser
	void getMovieById() throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		result.put("page", 1);
		result.put("total_pages", 35032);
		result.put("results", 0);
		result.put("total_results", 35032);
		Integer id = 550;

		given(movieService.findMovieById(id)).willReturn(result);

		ResultActions response = mockMvc.perform(get("/api/movie/{movie_id}", id));

		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.page", is(1)))
				.andExpect(jsonPath("$.total_pages", is(35032))).andExpect(jsonPath("$.results", is(0)))
				.andExpect(jsonPath("$.total_results", is(35032)));
	}

	@Test
	@WithMockUser
	void putFavoritePersonalRatingNotes() throws Exception {
		Integer id = 550;
		UserMovie userMovie = new UserMovie();

		Principal principal = new Principal() {
			@Override
			public String getName() {
				return "admin";
			}
		};

		given(userMovieRepository.findByUsernameAndMovie(principal.getName(), id.toString()))
				.willReturn(Optional.of(userMovie));
		given(userMovieRepository.save(userMovie)).willAnswer((invocation) -> invocation.getArgument(0));

		ResultActions response = mockMvc.perform(patch("/api/movie/{movie_id}", id)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userMovie)));

		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.id", is(IsNull.nullValue())))
				.andExpect(jsonPath("$.username", is("user"))).andExpect(jsonPath("$.movie", is(id.toString())))
				.andExpect(jsonPath("$.favorite", is(IsNull.nullValue())))
				.andExpect(jsonPath("$.personal_rating", is(IsNull.nullValue())))
				.andExpect(jsonPath("$.notes", is(IsNull.nullValue())));

	}

	@Test
	void findCredits() throws Exception {
		Integer id = 550;
		HashMap<String, Object> result = new HashMap<>();
		result.put("cast", 1);
		result.put("id", id);
		result.put("crew", 0);

		given(movieService.findCredits(id)).willReturn(result);

		ResultActions response = mockMvc.perform(get("/api/movie/{movie_id}/credit", id));

		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.cast", is(1)))
				.andExpect(jsonPath("$.id", is(id))).andExpect(jsonPath("$.crew", is(0)));
	}

	@Test
	void getImages() throws Exception {
		Integer id = 550;
		HashMap<String, Object> result = new HashMap<>();
		result.put("backdrops", 1);
		result.put("posters", 0);
		result.put("id", id);
		result.put("logos", 0);

		given(movieService.findImage(id)).willReturn(result);

		ResultActions response = mockMvc.perform(get("/api/movie/{movie_id}/images", id));

		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.backdrops", is(1)))
				.andExpect(jsonPath("$.posters", is(0))).andExpect(jsonPath("$.id", is(id)))
				.andExpect(jsonPath("$.logos", is(0)));
	}

	@Test
	void getKeywords() throws Exception {
		Integer id = 550;
		HashMap<String, Object> result = new HashMap<>();
		result.put("keywords", 1);
		result.put("id", id);

		given(movieService.findKeywords(id)).willReturn(result);

		ResultActions response = mockMvc.perform(get("/api/movie/{movie_id}/keywords", id));

		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.keywords", is(1)))
				.andExpect(jsonPath("$.id", is(id)));
	}

	@Test
	void getRecommendations() throws Exception {
		Integer id = 550;
		HashMap<String, Object> result = new HashMap<>();
		result.put("page", 1);
		result.put("total_pages", 35032);
		result.put("results", 0);
		result.put("total_results", 35032);

		given(movieService.findRecommendation(id)).willReturn(result);

		ResultActions response = mockMvc.perform(get("/api/movie/{movie_id}/recommendations", id));

		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.page", is(1)))
				.andExpect(jsonPath("$.total_pages", is(35032))).andExpect(jsonPath("$.results", is(0)))
				.andExpect(jsonPath("$.total_results", is(35032)));
	}

	@Test
	void getSimilar() throws Exception {
		Integer id = 550;
		HashMap<String, Object> result = new HashMap<>();
		result.put("page", 1);
		result.put("total_pages", 35032);
		result.put("results", 0);
		result.put("total_results", 35032);

		given(movieService.findSimilar(id)).willReturn(result);

		ResultActions response = mockMvc.perform(get("/api/movie/{movie_id}/similar", id));

		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.page", is(1)))
				.andExpect(jsonPath("$.total_pages", is(35032))).andExpect(jsonPath("$.results", is(0)))
				.andExpect(jsonPath("$.total_results", is(35032)));
	}

}
