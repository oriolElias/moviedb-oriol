package com.moviedb.demo.Integration;

import com.moviedb.demo.Controller.MovieDbController;
import org.apache.geode.internal.Assert;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;



import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcDataSource.class })
@WebAppConfiguration(value = "src/main/java/com/moviedb/demo/DemoApplication.java")
@AutoConfigureMockMvc(addFilters = false)
@EnableWebMvc
public class MovieDbControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired(required = true)
    private MockMvc mockMvc;

    @MockBean
    MovieDbController movieDbController;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean(MovieDbController.class));
    }

    @Test
    void GIVEN_apiConfiguration_WHEN_MockMvc_THEN_VerifyResponse() throws Exception {
        //GIVEN
        ResultActions request = mockMvc.perform(get("/api/configuration"));

        //WHEN
        MvcResult mvcResult = request.andDo(print()).andExpect(status().isOk()).andReturn();

        //THEN
        assertEquals("application/json",mvcResult.getResponse().getContentType());
    }

    @Test
    void GIVEN_apiGenreMovieList_WHEN_MockMvc_THEN_VerifyResponse() throws Exception {
        //GIVEN
        ResultActions request = mockMvc.perform(get("/api/genre/movie/list"));

        //WHEN
        MvcResult mvcResult = request.andDo(print()).andExpect(status().isOk()).andReturn();

        //THEN
        assertEquals("application/json",mvcResult.getResponse().getContentType());
    }

    @Test
    void GIVEN_apiMoviePopular_WHEN_MockMvc_THEN_VerifyRepsonse() throws Exception{
        //GIVEN
        ResultActions request = mockMvc.perform(get("/api/movie/popular"));

        //WHEN
        MvcResult mvcResult = request.andDo(print()).andExpect(status().isOk()).andReturn();

        //THEN
        assertEquals("application/json",mvcResult.getResponse().getContentType());
    }

    @Test
    void GIVEN_apiMovieTopRated_WHEN_MockMvc_THEN_VerifyRepsonse() throws Exception{
        //GIVEN
        ResultActions request = mockMvc.perform(get("/api/movie/top_rated"));

        //WHEN
        MvcResult mvcResult = request.andDo(print()).andExpect(status().isOk()).andReturn();

        //THEN
        assertEquals("application/json",mvcResult.getResponse().getContentType());
    }

    @Test
    void GIVEN_apiMovieById_WHEN_MockMvc_THEN_VerifyRepsonse() throws Exception{
        //GIVEN
        int id = 500;
        ResultActions request = mockMvc.perform(get("/api/movie/{movie_id}",id));

        //WHEN
        MvcResult mvcResult = request.andDo(print()).andExpect(status().isOk()).andReturn();

        //THEN
        assertEquals("application/json",mvcResult.getResponse().getContentType());
    }

    
}
