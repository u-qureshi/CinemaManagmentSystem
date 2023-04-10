package cinema;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieManagment extends JFrame {

private List<Movie> fetchMovies(JComboBox<String> cbmovies) {
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


private Movie fetchMovie(String title){

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

private boolean addMovie(Movie movie) {
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

private boolean deleteMovie(int movieId) {
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


    public MovieManagment() {
        setTitle("Movie Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        JLabel lblMovieName = new JLabel("Movie Name:");
        JTextField txtMovieName = new JTextField();
        txtMovieName.setColumns(10);

        JLabel lblReleaseDate = new JLabel("Year:");
        JTextField txtReleaseDate = new JTextField();
        txtReleaseDate.setColumns(10);
        
        
        JLabel lblgenre = new JLabel("Genre:");
        JTextField txtgenre = new JTextField();
        txtReleaseDate.setColumns(10);
        
        JLabel lbldirector = new JLabel("Director:");
        JTextField txtdirector = new JTextField();
        txtReleaseDate.setColumns(10);
        
        JLabel lblduration = new JLabel("Duration:");
        JTextField txtduration = new JTextField();
        txtReleaseDate.setColumns(10);
        
        
       JLabel lblmovietitle = new JLabel("Movies:");
        JComboBox<String> cbmovies = new JComboBox<>();

        JButton btnAddMovie = new JButton("Add Movie");
        btnAddMovie.addActionListener(e -> {
        String title = txtMovieName.getText();
        String year = txtReleaseDate.getText();
        String genre = txtgenre.getText();
        String director = txtdirector.getText();
        String duration = txtduration.getText();
        
        Movie movie = new Movie(1,title, genre, director,year, duration );
            addMovie(movie);
            JOptionPane.showMessageDialog(null, "Movie Added");
            fetchMovies(cbmovies);
          txtMovieName.setText("");
        txtReleaseDate.setText("");
        txtgenre.setText("");
        txtdirector.setText("");
        txtduration.setText("");
        });
        fetchMovies(cbmovies);
        
        

        JButton btnEditMovie = new JButton("Edit Movie");
        btnEditMovie.addActionListener(e -> {
        
        dispose();
        new editMovie(fetchMovie(cbmovies.getSelectedItem().toString())).setVisible(true);
        
        });

        JButton btnDeleteMovie = new JButton("Delete Movie");
        btnDeleteMovie.addActionListener(e -> {
        deleteMovie(fetchMovie(cbmovies.getSelectedItem().toString()).getId());
        JOptionPane.showMessageDialog(null, "Movie Deleted");
        fetchMovies(cbmovies);
        
        });
        // Add action listener for the delete movie button

        JButton btnBackToAdmin = new JButton("Back to Admin");
        btnBackToAdmin.addActionListener(e -> {
            dispose();
            new Admin().setVisible(true);
        });
        add(btnBackToAdmin);
        // Add action listener for the back to admin button as shown before

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblMovieName)
                    .addComponent(lblReleaseDate)
                    .addComponent(lblgenre)
                    .addComponent(lbldirector)
                    .addComponent(lblduration)
                               
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtMovieName)
                    .addComponent(txtReleaseDate)
                    .addComponent(txtgenre)
                    .addComponent(txtdirector)
                    .addComponent(txtduration)
                    .addComponent(btnAddMovie)
                    .addComponent(lblmovietitle)
                    .addComponent(cbmovies)

                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditMovie)
                        .addComponent(btnDeleteMovie))
                    .addComponent(btnBackToAdmin))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMovieName)
                    .addComponent(txtMovieName))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReleaseDate)
                    .addComponent(txtReleaseDate))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblgenre)    
                    .addComponent(txtgenre))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbldirector)
                    .addComponent(txtdirector))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblduration)
                    .addComponent(txtduration)
                )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMovie)

                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmovietitle)
                )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cbmovies)
                )

                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditMovie)
                    .addComponent(btnDeleteMovie))
                .addComponent(btnBackToAdmin)
        );

        Font font = new Font("Arial", Font.PLAIN, 14);
        lblMovieName.setFont(font);
        txtMovieName.setFont(font);
        lblReleaseDate.setFont(font);
        txtReleaseDate.setFont(font);
        lblgenre.setFont(font);
        txtgenre.setFont(font);
        lbldirector.setFont(font);
        txtdirector.setFont(font);
        lblduration.setFont(font);
        txtduration.setFont(font);
        btnAddMovie.setFont(font);
        lblmovietitle.setFont(font);
        cbmovies.setFont(font);
        btnEditMovie.setFont(font);
        btnDeleteMovie.setFont(font);
        btnBackToAdmin.setFont(font);
        
        
    }
}

