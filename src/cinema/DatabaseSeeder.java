package cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseSeeder {
    private static final String URL = "jdbc:mysql://localhost:3306/cinema";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Insert sample users
            insertUser(connection, "admin", "admin123", "admin");
            insertUser(connection, "user1", "user123", "user");
            insertUser(connection, "user2", "user456", "user");

            // Insert sample movies
            insertMovie(connection, "The Godfather", "Crime, Drama", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", "1972-03-24", 175, "https://www.imdb.com/title/tt0068646/mediaviewer/rm305423872");

            // Insert sample showtimes
            insertShowtime(connection, 1, LocalDateTime.of(2022, 5, 1, 10, 0, 0), LocalDateTime.of(2022, 5, 1, 12, 30, 0));
            insertShowtime(connection, 1, LocalDateTime.of(2022, 5, 1, 13, 0, 0), LocalDateTime.of(2022, 5, 1, 15, 30, 0));

            // Insert sample bookings
            insertBooking(connection, 1, 2, 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertUser(Connection connection, String username, String password, String account_type) throws SQLException {
        String query = "INSERT INTO users (username, password, account_type) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, account_type);
            statement.executeUpdate();
        }
    }

    private static void insertMovie(Connection connection, String title, String genre, String description, String releaseDate, int duration, String imageUrl) throws SQLException {
        String query = "INSERT INTO movies (title, genre, description, release_date, duration, image_url) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setString(3, description);
            statement.setString(4, releaseDate);
            statement.setInt(5, duration);
            statement.setString(6, imageUrl);
            statement.executeUpdate();
        }
    }

    private static void insertShowtime(Connection connection, int movieId, LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
        String query = "INSERT INTO showtimes (movie_id, start_time, end_time) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, movieId);
            statement.setObject(2, startTime);
            statement.setObject(3, endTime);
            statement.executeUpdate();
        }
    }

    private static void insertBooking(Connection connection, int userId, int showtimeId, int numTickets) throws SQLException {
        String query = "INSERT INTO bookings (user_id, showtime_id, num_tickets) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, showtimeId);
            statement.setInt(3, numTickets);
            statement.executeUpdate();
        }
    }
    
}
