package dao;

import model.Movie;
import java.util.List;

public interface MovieDAO {
    void addMovie(Movie movie);
    Movie getMovie(int id);
    List<Movie> getAllMovies();
    void updateMovie(Movie movie);
    void deleteMovie(int id);
}
