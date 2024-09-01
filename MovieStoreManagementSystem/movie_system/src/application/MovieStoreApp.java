package application;

import service.MovieService;
import model.Movie;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MovieStoreApp {
    private static MovieService movieService;

    public static void main(String[] args) {
        try {
            movieService = new MovieService();
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("Movie Store Management System");
                System.out.println("1. Add Movie");
                System.out.println("2. View Movies");
                System.out.println("3. Update Movie");
                System.out.println("4. Delete Movie");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        // Add Movie
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("Enter release year: ");
                        int releaseYear = scanner.nextInt();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter stock: ");
                        int stock = scanner.nextInt();
                        scanner.nextLine(); 
                        Movie movie = new Movie(0, title, genre, releaseYear, price, stock);
                        movieService.addNewMovie(movie);
                        System.out.println("Movie added successfully.");
                        break;

                    case 2:
                        // View Movies
                        List<Movie> movies = movieService.getAllMovies();
                        for (Movie m : movies) {
                            System.out.println(m);
                        }
                        break;

                    case 3:
                        // Update Movie
                        System.out.print("Enter movie ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); 
                        Movie movieToUpdate = movieService.getMovie(id);
                        if (movieToUpdate != null) {
                            System.out.print("Enter new title: ");
                            String newTitle = scanner.nextLine();
                            System.out.print("Enter new genre: ");
                            String newGenre = scanner.nextLine();
                            System.out.print("Enter new release year: ");
                            int newReleaseYear = scanner.nextInt();
                            System.out.print("Enter new price: ");
                            double newPrice = scanner.nextDouble();
                            System.out.print("Enter new stock: ");
                            int newStock = scanner.nextInt();
                            scanner.nextLine(); 
                            movieToUpdate.setTitle(newTitle);
                            movieToUpdate.setGenre(newGenre);
                            movieToUpdate.setReleaseYear(newReleaseYear);
                            movieToUpdate.setPrice(newPrice);
                            movieToUpdate.setStock(newStock);
                            movieService.updateMovie(movieToUpdate);
                            System.out.println("Movie updated successfully.");
                        } else {
                            System.out.println("Movie not found.");
                        }
                        break;

                    case 4:
                        // Delete Movie
                        System.out.print("Enter movie ID to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); 
                        movieService.deleteMovie(deleteId);
                        System.out.println("Movie deleted successfully.");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            } while (choice != 5);

            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
