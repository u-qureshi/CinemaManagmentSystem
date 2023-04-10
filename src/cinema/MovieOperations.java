
package cinema;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieOperations {
    
    public List<Movie> fetchMovies(JComboBox<String> cbmovies) {
    List<Movie> movies = new ArrayList<>();

    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";

    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "SELECT * FROM movies";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        cbmovies.removeAllItems();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String genre = resultSet.getString("genre");
            String director = resultSet.getString("director");
            String duration = resultSet.getString("duration");
            String year = resultSet.getString("year");
            Movie movie = new Movie(id, title, genre, director, year, duration);
            movies.add(movie);
            cbmovies.addItem(title);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return movies;
}
    
    public int fetchMovieId(String title){

    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";
    int id=0;
    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "SELECT * FROM movies WHERE title = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, title);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()){
         id = resultSet.getInt("id");
        
        }
        else{
        id= 0;
                }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return id;
    }
    
    public int fetchShowTimeId(String start_time){

    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";
    int id=0;
    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "SELECT * FROM showtimes WHERE start_time = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, start_time);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()){
         id = resultSet.getInt("id");
        
        }
        else{
        id= 0;
                }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return id;
    }
    
    public Movie fetchMovie(String title){

    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";
    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "SELECT * FROM movies WHERE title = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, title);
        ResultSet resultSet = statement.executeQuery();
       
        if (resultSet.next()){
         int id = resultSet.getInt("id");
        String movie_title = resultSet.getString("title");
        String genre = resultSet.getString("genre");
        String director = resultSet.getString("director");
        String year = resultSet.getString("year");
        String duration = resultSet.getString("duration");
         
        Movie movie = new Movie(id, movie_title, genre, director, year, duration);
        return movie;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
    }

    public boolean addMovie(Movie movie) {
    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";

    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "INSERT INTO movies (title, genre, director, duration, year) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, movie.getTitle());
        statement.setString(2, movie.getGenre());
        statement.setString(3, movie.getDirector());
        statement.setString(4, movie.getDuration());
        statement.setString(5, movie.getYear());
        

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean deleteMovie(int movieId) {
    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";

    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "DELETE FROM movies WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, movieId);

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public boolean updateMovie(Movie movie) {
    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";

    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "UPDATE movies SET title = ?, genre = ?, director = ?, duration = ?, year = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, movie.getTitle());
        statement.setString(2, movie.getGenre());
        statement.setString(3, movie.getDirector());
        statement.setString(4, movie.getDuration());
        statement.setString(5, movie.getYear());
        statement.setInt(6, movie.getId());

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public List<Showtime> fetchShowtimes(int movieId, JComboBox<String> cbtimes) {
    List<Showtime> showtimes = new ArrayList<>();

    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";

    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "SELECT * FROM showtimes WHERE movie_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, movieId);
        ResultSet resultSet = statement.executeQuery();
        cbtimes.removeAllItems();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
//            int cinemaId = resultSet.getInt("cinema_id");
            String startTime = resultSet.getString("start_time");
            String endTime = resultSet.getString("end_time");

            Showtime showtime = new Showtime(id, movieId, startTime, endTime);
            showtimes.add(showtime);
            cbtimes.addItem(startTime);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return showtimes;
}
}
