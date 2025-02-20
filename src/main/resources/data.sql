-- Insert an Admin User
INSERT INTO movies_schema.user (email, password, role) VALUES
('admin@movies.com', '$2a$10$NotFunctionalPassword', 'ADMIN'),
('user@movies.com', '$2a$10$NotFunctionalPassword', 'USER');

-- Insert Some Movies
INSERT INTO movies_schema.movie (name, release_year, synopsis, category, created_by) VALUES
('Resident Evil 4: Resurrection', 2010, 'Zombieeees', 'Suspense', 1),
('Sonic 3: The movie', 2024, 'Sonic', 'Adventure', 1);

-- Insert Ratings
INSERT INTO movies_schema.rating (user_id, movie_id, rating) VALUES
(2, 1, 4),
(2, 2, 5);
