package com.moviedb.demo;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.cache.config.EnableGemfireCaching;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;

import java.util.Arrays;


@SpringBootApplication
//@EnableCaching
//@Configuration
@ClientCacheApplication(name = "CachingGemFireApplication")
@EnableCachingDefinedRegions(clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireCaching
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
