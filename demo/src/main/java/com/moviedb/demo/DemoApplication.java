package com.moviedb.demo;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.DefaultApi;
import org.openapitools.client.auth.ApiKeyAuth;
import org.openapitools.client.model.GETMovieMovieId200Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.cache.config.EnableGemfireCaching;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;

import java.util.Arrays;


@SpringBootApplication
@EnableCaching
//@Configuration
//@ClientCacheApplication(name = "CachingGemFireApplication")
//@EnableCachingDefinedRegions(clientRegionShortcut = ClientRegionShortcut.LOCAL)
//@EnableGemfireCaching
public class DemoApplication {
	@Value("${themoviedatabase.api_key}")
	private String myApiKey;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner sampleRequest() {
		ApiClient defaultClient = new ApiClient();
		defaultClient.setBasePath("https://api.themoviedb.org/3");

		ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
		api_key.setApiKey(myApiKey);
		DefaultApi apiInstance = new DefaultApi(defaultClient);
		try {
			GETMovieMovieId200Response x = apiInstance.gETMovieMovieId(550);
			System.out.println(x);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
