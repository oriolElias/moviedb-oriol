package com.moviesDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.cache.config.EnableGemfireCaching;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions; 
import org.apache.geode.cache.client.ClientRegionShortcut;


@SpringBootApplication
@EnableCaching
@Configuration
@ClientCacheApplication(name = "CachingGemFireApplication")
@EnableCachingDefinedRegions(clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireCaching
public class MyMovies1Application {

	public static void main(String[] args) {
		SpringApplication.run(MyMovies1Application.class, args);
	}
}
