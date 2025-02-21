-- Insert an Admin User
INSERT INTO movies_schema.user (email, password, role) VALUES
--Admin12345$
--User12345$
('admin@movies.com', '$2a$10$GRJhEDuxUm1aIYdkswLZtugcuPhH4caFjnWA3WL3upB4ft.2PYApK', 'ADMIN'),
('user@movies.com', '$2a$10$SwwTElf9ab3BCNU/CdhgyOiIANht/4PUcOMJA024u4wN7bNsxOnUO', 'USER');

-- Insert Some Movies
INSERT INTO movies_schema.movie (name, release_year, synopsis, category, created_by, rating_count, average_rating) VALUES
('Resident Evil 4: Resurrection', 2010, 'Zombieeees', 'Suspense', 1, 1, 4.0),
('Sonic 3: The movie', 2024, 'Sonic', 'Adventure', 1, 1, 5.0),
('Final Destination', 2000, 'You can se the future... death', 'Suspense', 1, 0, 0.0),
('Shrek', 2001, 'One of the best movies', 'Comedy', 1, 0, 0.0),
('Mad Max: Fury Road', 2015, 'Witness meeeee', 'Action', 1, 0, 0.0);

-- Insert Ratings
INSERT INTO movies_schema.rating (user_id, movie_id, rating) VALUES
(2, 1, 4),
(2, 2, 5);
