package com.moviedb.demo.Controller;

import com.moviedb.demo.Service.MovieDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class MovieDbController {
    @Autowired
    MovieDbService movieDbService;

    @GetMapping("/api/configuration")
    public HashMap<String, Object> getConfiguration() {
        HashMap<String, Object> config = movieDbService.getConfig();
        config.put("hola","kease");
        return config;
    }
}
