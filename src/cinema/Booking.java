package cinema;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Booking extends JFrame {
    private int user_id;
    private int fetchMovieId(String title){

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
    
    
private int fetchShowTimeId(String start_time){

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
    
private List<Movie> fetchMovies(JComboBox<String> cbMovie) {
    List<Movie> movies = new ArrayList<>();

    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";

    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query = "SELECT * FROM movies";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String genre = resultSet.getString("genre");
            String director = resultSet.getString("director");
            String duration = resultSet.getString("duration");
            String releaseDate = resultSet.getString("year");

            Movie movie = new Movie(id, title, genre, director, duration, releaseDate);
            movies.add(movie);
            cbMovie.addItem(title);
            
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return movies;
}

private List<Showtime> fetchShowtimes(int movieId, JComboBox<String> cbtimes) {
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

private boolean makeBooking(TicketBooking booking) {
    String url = "jdbc:mysql://localhost:3306/cinema";
    String user = "root";
    String dbPassword = "admin";

    try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
        String query1 = "select * from bookings where user_id='"+booking.getUserId()+"' and showtime_id='"+booking.getShowtimeId()+"'";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        ResultSet resultSet = statement1.executeQuery();
        if(resultSet.next()){
            return false;
        }
        
        String query = "INSERT INTO bookings (user_id, showtime_id, num_seats) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, booking.getUserId());
        statement.setInt(2, booking.getShowtimeId());
        statement.setInt(3, booking.getNumSeats());

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



    public Booking(int user_id) {
        this.user_id = user_id;
        setTitle("Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        JLabel lblMovie = new JLabel("Movie:");
        JComboBox<String> cbMovie = new JComboBox<>();
        
        JLabel lbltimes = new JLabel("Show times:");
        JComboBox<String> cbtimes = new JComboBox<>();
        // Add available movies to the combo box
        fetchMovies(cbMovie);
        cbMovie.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Code to handle the selection change event
            String selectedMovie = cbMovie.getSelectedItem().toString();
            System.out.println("Selected movie: " + selectedMovie);
            int movie_id = fetchMovieId(selectedMovie);
            if (movie_id != 0){
                fetchShowtimes(movie_id,cbtimes);
            }
        }
    }
});
        
        

        JLabel lblSeats = new JLabel("Number of seats:");
        JTextField txtSeats = new JTextField();
        txtSeats.setColumns(5);

        JButton btnBook = new JButton("Book");
        btnBook.addActionListener(e -> {
            try{
                int seats = Integer.parseInt(txtSeats.getText());
                int current_user_id = user_id; 
                int show_time =  fetchShowTimeId(cbtimes.getSelectedItem().toString());
                TicketBooking book = new TicketBooking(1,show_time, seats, current_user_id );
                boolean is_booked= makeBooking(book);
                if(is_booked){
                    JOptionPane.showMessageDialog(null, "Booked Successfully");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Can not book this movie, May be you already have booked");
                }
            }
            catch (Exception excep){
            System.out.println(excep);
            }
        });
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
            });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblMovie)
                        .addComponent(lbltimes)
                    .addComponent(lblSeats))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cbMovie)
                        .addComponent(cbtimes)
                    .addComponent(txtSeats)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBook)
                        .addComponent(btnLogout)
                    ))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMovie)
                    .addComponent(cbMovie))
                    
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltimes)
                    .addComponent(cbtimes))
               
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSeats)
                    .addComponent(txtSeats))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBook)
                    .addComponent(btnLogout))
        );

        Font font = new Font("Arial", Font.PLAIN, 14);
        lblMovie.setFont(font);
        cbMovie.setFont(font);
        lbltimes.setFont(font);
        cbtimes.setFont(font);
        lblSeats.setFont(font);
        txtSeats.setFont(font);
        btnBook.setFont(font);
        btnLogout.setFont(font);
    }
}
