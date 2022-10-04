package com.moviesDB.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviesDB.entities.UserMovie;

public interface UserMovieRepository extends JpaRepository<UserMovie, Integer> {

	Optional<UserMovie>findByUsernameAndMovie(String username, String movieid);

}
