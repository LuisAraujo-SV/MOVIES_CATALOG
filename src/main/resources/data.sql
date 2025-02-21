-- Insert an Admin User
INSERT INTO movies_schema.user (email, password, role) VALUES
('admin@movies.com', '$2a$10$CHdG928tip8sC6Ewstz5Ku1tsu34ahCohXRfPfMYL30Sky7.qwEJy', 'ADMIN'),
('user@movies.com', '$2a$10$PQbupOhDS4Hf2VjZ40ta7O9NpJF2vjPQj6b1cjC0KVIVrFrUM1zIe', 'USER');

-- Insert Some Movies
INSERT INTO movies_schema.movie (name, release_year, synopsis, category, created_by, rating_count, average_rating) VALUES
('Resident Evil 4: Resurrection', 2010, 'Zombieeees', 'Suspense', 1, 1, 4.0),
('Sonic 3: The movie', 2024, 'Sonic', 'Adventure', 1, 1, 5.0);

-- Insert Ratings
INSERT INTO movies_schema.rating (user_id, movie_id, rating) VALUES
(2, 1, 4),
(2, 2, 5);
