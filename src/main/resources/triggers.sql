-- Set the search
SET search_path TO movies_schema;

-- Create trigger function to update the average rating for a movie
CREATE OR REPLACE FUNCTION update_movie_rating()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        UPDATE movie
        SET
            rating_count = rating_count + 1,
            average_rating = ((average_rating * rating_count) + NEW.rating) / (rating_count + 1)
        WHERE id = NEW.movie_id;
        RETURN NEW;

    ELSIF (TG_OP = 'UPDATE') THEN
        UPDATE movie
        SET
            average_rating = ((average_rating * rating_count) - OLD.rating + NEW.rating) / rating_count
        WHERE id = NEW.movie_id;
        RETURN NEW;

    ELSIF (TG_OP = 'DELETE') THEN
        UPDATE movie
        SET
            rating_count = rating_count - 1,
            average_rating = CASE
                WHEN (rating_count - 1) = 0 THEN 0
                ELSE ((average_rating * rating_count) - OLD.rating) / (rating_count - 1)
            END
        WHERE id = OLD.movie_id;
        RETURN OLD;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Create trigger for INSERT on rating table
CREATE TRIGGER trg_update_movie_rating_insert
AFTER INSERT ON rating
FOR EACH ROW
EXECUTE FUNCTION update_movie_rating();

-- Create trigger for UPDATE on rating table
CREATE TRIGGER trg_update_movie_rating_update
AFTER UPDATE ON rating
FOR EACH ROW
EXECUTE FUNCTION update_movie_rating();

-- Create trigger for DELETE on rating table
CREATE TRIGGER trg_update_movie_rating_delete
AFTER DELETE ON rating
FOR EACH ROW
EXECUTE FUNCTION update_movie_rating();