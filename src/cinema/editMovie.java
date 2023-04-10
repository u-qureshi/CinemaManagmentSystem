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
import cinema.MovieOperations;

public class editMovie extends JFrame {
private Movie movie;

    public editMovie(Movie movie) {
        this.movie = movie;
        MovieOperations mop = new MovieOperations();
        setTitle("Edit Movie");
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
        
        txtMovieName.setText(movie.getTitle());
        txtReleaseDate.setText(movie.getYear());
        txtgenre.setText(movie.getGenre());
        txtdirector.setText(movie.getDirector());
        txtduration.setText(movie.getDuration());
       

        JButton btnAddMovie = new JButton("Update");
        btnAddMovie.addActionListener(e -> {
        String title = txtMovieName.getText();
        String year = txtReleaseDate.getText();
        String genre = txtgenre.getText();
        String director = txtdirector.getText();
        String duration = txtduration.getText();
        int id = mop.fetchMovieId(title);
        
        movie.setDirector(director);
        movie.setDuration(duration);
        movie.setGenre(genre);
        movie.setTitle(title);
        movie.setYear(year);
        movie.setId(id);
        mop.updateMovie(movie);
        dispose();
        new MovieManagment().setVisible(true);
        });
        
        JLabel lblmovietitle = new JLabel("Movies:");
        JComboBox<String> cbmovies = new JComboBox<>();

        JButton btnBackToAdmin = new JButton("Back to Movies Tab");
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
                        
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddMovie)
                        
                    )
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
        btnBackToAdmin.setFont(font);
        
        
    }
}

