package dao;

import model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {
    private Connection conn;

    public MovieDAOImpl() throws SQLException {
        conn = DBConnection.getConnection();
    }

    @Override
    public void addMovie(Movie movie) {
        String sql = "INSERT INTO Movies (title, genre, release_year, price, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getReleaseYear());
            stmt.setDouble(4, movie.getPrice());
            stmt.setInt(5, movie.getStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Movie getMovie(int id) {
        String sql = "SELECT * FROM Movies WHERE movie_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getInt("release_year"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM Movies";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                movies.add(new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getInt("release_year"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public void updateMovie(Movie movie) {
        String sql = "UPDATE Movies SET title = ?, genre = ?, release_year = ?, price = ?, stock = ? WHERE movie_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getReleaseYear());
            stmt.setDouble(4, movie.getPrice());
            stmt.setInt(5, movie.getStock());
            stmt.setInt(6, movie.getMovieId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMovie(int id) {
        String sql = "DELETE FROM Movies WHERE movie_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
