package com.moviedb.demo.Entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Entity
@Table(name="user_movie")
public class UserMovie {
    @Id
    @Column(name="movieid",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String username;
    String movie;

    //falta datavalidation
    Boolean favorite;
    Integer personal_rating;
    String notes;

}
