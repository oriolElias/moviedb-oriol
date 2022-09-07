DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users(
  userid int not null auto_increment,
  username varchar_ignorecase(50) not null ,
  password varchar_ignorecase(500) not null,
  role varchar(50) not null,
  enabled int not null,
  primary key (userid)
);
DROP TABLE IF EXISTS user_movie;
CREATE TABLE IF NOT EXISTS user_movie(
  userid int not null,
  movieid int not null,
  movie varchar(255),
  username varchar(255),
  favorite boolean,
  personal_rating int,
  notes varchar(255),
  foreign key (userid) references users(userid)
);

INSERT INTO users ('username','password','role','enabled')
VALUES ('user',
        '$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu',
        'USER', 1);

INSERT INTO users ('username','password','role','enabled')
VALUES ('admin',
        '$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy',
        'ADMIN', 1);