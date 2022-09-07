package com.moviedb.demo.Repository;

import com.moviedb.demo.Entity.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMovieRepository extends JpaRepository<UserMovie,Integer> {
    Optional<UserMovie> findByUsernameAndMovie(String username, String movie_id);
}
