package service;

import dao.MovieDAO;
import dao.MovieDAOImpl;
import model.Movie;
import java.sql.SQLException;
import java.util.List;

public class MovieService {
    private MovieDAO movieDAO;

    public MovieService() throws SQLException {
        this.movieDAO = new MovieDAOImpl();
    }

    public void addNewMovie(Movie movie) {
        movieDAO.addMovie(movie);
    }

    public Movie getMovie(int id) {
        return movieDAO.getMovie(id);
    }

    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    public void updateMovie(Movie movie) {
        movieDAO.updateMovie(movie);
    }

    public void deleteMovie(int id) {
        movieDAO.deleteMovie(id);
    }
}
