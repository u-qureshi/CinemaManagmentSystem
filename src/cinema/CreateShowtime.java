/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

public class CreateShowtime extends JFrame  {
    private Movie movie;

    public CreateShowtime(Movie movie) {
        this.movie = movie;
        MovieOperations mop = new MovieOperations();
        setTitle("Edit Movie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        JLabel lblHintTime = new JLabel("Start Time and End time should be in yyyy-MM-dd HH:mm:ss format");
        JLabel lblStartTime = new JLabel("Start Time:");
        JTextField txtStartTime = new JTextField();
        txtStartTime.setColumns(10);

        JLabel lblEndTime = new JLabel("End Time:");
        JTextField txtEndTime = new JTextField();
        txtEndTime.setColumns(10);
        
        
        JButton btnAddShowTime = new JButton("Add");
        btnAddShowTime.addActionListener(e -> {
        String startTime = txtStartTime.getText();
        String endTime = txtEndTime.getText();
        
        int id = mop.fetchMovieId(movie.getTitle());
        
        Showtime st = new Showtime(1, id, startTime, endTime);
        
        String format = "yyyy-MM-dd HH:mm:ss";
        boolean isValidStartTime = DateValidator.isValidDateTime(startTime, format);
        boolean isValidEndTime = DateValidator.isValidDateTime(endTime, format);
        
       if (isValidStartTime && isValidEndTime) {
            mop.createShowtime(st);
            dispose();
            new MovieManagment().setVisible(true);
        } else {
           JOptionPane.showMessageDialog(null, "You have entered Incorrect time, Please follow the hint");
        }
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
                    .addComponent(lblHintTime)                  
                               
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblStartTime)
                    .addComponent(lblEndTime)
                   
                               
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtStartTime)
                    .addComponent(txtEndTime)
                        
                        
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddShowTime)
                        
                    )
                    .addComponent(btnBackToAdmin))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHintTime))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    
                        .addComponent(lblStartTime)
                    .addComponent(txtStartTime))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEndTime)
                    .addComponent(txtEndTime))
               
                
                
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddShowTime)
                    )
                .addComponent(btnBackToAdmin)
        );

        Font font = new Font("Arial", Font.PLAIN, 14);
        Font hintfont = new Font("Arial", Font.PLAIN, 8);
        lblStartTime.setFont(font);
        lblHintTime.setFont(hintfont);
        txtStartTime.setFont(font);
        lblEndTime.setFont(font);
        txtEndTime.setFont(font);
        btnAddShowTime.setFont(font);
        btnBackToAdmin.setFont(font);
        
        
    }
    
}
