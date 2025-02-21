-- Insert an Admin User
INSERT INTO movies_schema.user (email, password, role) VALUES
--admin12345$
--user12345$
('admin@movies.com', '$2a$10$lmecDDyR7Lmg3GlQuEYfy.iGaT/ZRE9tW4.ZHJQQDRkynyBD3wHbS', 'ADMIN'),
('user@movies.com', '$2a$10$NSHDNCwW1ttH1vVILmEt/eoOXQODdR6pHZboyn8ruootMtAtcJhn2', 'USER');

-- Insert Some Movies
INSERT INTO movies_schema.movie (name, release_year, synopsis, category, created_by, rating_count, average_rating) VALUES
('Resident Evil 4: Resurrection', 2010, 'Zombieeees', 'Suspense', 1, 1, 4.0),
('Sonic 3: The movie', 2024, 'Sonic', 'Adventure', 1, 1, 5.0);

-- Insert Ratings
INSERT INTO movies_schema.rating (user_id, movie_id, rating) VALUES
(2, 1, 4),
(2, 2, 5);
