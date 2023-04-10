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

public class MovieManagment extends JFrame {

    public MovieManagment() {
        MovieOperations mop = new MovieOperations();
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
            mop.addMovie(movie);
            JOptionPane.showMessageDialog(null, "Movie Added");
            
            mop.fetchMovies(cbmovies);
          txtMovieName.setText("");
        txtReleaseDate.setText("");
        txtgenre.setText("");
        txtdirector.setText("");
        txtduration.setText("");
        });
        mop.fetchMovies(cbmovies);
        
        

        JButton btnEditMovie = new JButton("Edit Movie");
        btnEditMovie.addActionListener(e -> {
        
        dispose();
        new editMovie(mop.fetchMovie(cbmovies.getSelectedItem().toString())).setVisible(true);
        
        });

        JButton btnDeleteMovie = new JButton("Delete Movie");
        btnDeleteMovie.addActionListener(e -> {
        mop.deleteMovie(mop.fetchMovie(cbmovies.getSelectedItem().toString()).getId());
        JOptionPane.showMessageDialog(null, "Movie Deleted");
        mop.fetchMovies(cbmovies);
        
        });

        JButton btnBackToAdmin = new JButton("Back to Admin");
        btnBackToAdmin.addActionListener(e -> {
            dispose();
            new Admin().setVisible(true);
        });
        add(btnBackToAdmin);

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

