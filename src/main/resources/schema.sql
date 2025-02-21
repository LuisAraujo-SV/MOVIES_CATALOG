--Create database
CREATE SCHEMA IF NOT EXISTS movies_schema;


-- Users Table
DROP TABLE IF EXISTS movies_schema.rating;
DROP TABLE IF EXISTS movies_schema.movie;
DROP TABLE IF EXISTS movies_schema.user;

CREATE TABLE movies_schema.user (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) CHECK (role IN ('ADMIN', 'USER')) NOT NULL
);

-- Movies Table
CREATE TABLE movies_schema.movie (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    release_year INT NOT NULL,
    synopsis TEXT NOT NULL,
    category VARCHAR(100) NOT NULL,
    image_url VARCHAR(500),
    created_by INT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    rating_count INT NULL,
    average_rating NUMERIC NULL,
    FOREIGN KEY (created_by) REFERENCES movies_schema.user(id) ON DELETE CASCADE,
    UNIQUE (name, release_year, category)
);

-- Ratings Table
CREATE TABLE movies_schema.rating (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    movie_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES movies_schema.user(id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES movies_schema.movie(id) ON DELETE CASCADE,
    UNIQUE (user_id, movie_id) -- Ensures a user can rate a movie only once
);