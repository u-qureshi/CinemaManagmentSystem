package cinema;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import cinema.MovieOperations;


public class Booking extends JFrame implements TicketOperations {
    private int user_id;
    public static boolean isNumber(String str) {
       try {
           Double.parseDouble(str);
           return true;
       } catch (NumberFormatException e) {
           return false;
       }
   }
    @Override
    public boolean bookTicket(TicketBooking booking) {
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
        MovieOperations mop = new MovieOperations();
        setTitle("Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        JLabel lblMovie = new JLabel("Movie:");
        JComboBox<String> cbMovie = new JComboBox<>();
        
        JLabel lbltimes = new JLabel("Show times:");
        JComboBox<String> cbtimes = new JComboBox<>();
        // Add available movies to the combo box
        mop.fetchMovies(cbMovie);
        cbMovie.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Code to handle the selection change event
            String selectedMovie = cbMovie.getSelectedItem().toString();
            System.out.println("Selected movie: " + selectedMovie);
            int movie_id = mop.fetchMovieId(selectedMovie);
            if (movie_id != 0){
                mop.fetchShowtimes(movie_id,cbtimes);
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
                if(txtSeats.getText() == null || isNumber(txtSeats.getText()) != true){
                JOptionPane.showMessageDialog(null, "Please Choose a valid number for seats");
                }
                else{
                int seats = Integer.parseInt(txtSeats.getText());
                int current_user_id = user_id; 
                if(cbtimes.getSelectedItem() != null){
                     int show_time =  mop.fetchShowTimeId(cbtimes.getSelectedItem().toString());
                TicketBooking book = new TicketBooking(1,show_time, seats, current_user_id );
                boolean is_booked= bookTicket(book);
                if(is_booked){
                    JOptionPane.showMessageDialog(null, "Booked Successfully");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Can not book this movie, May be you already have booked");
                }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Showtimes for this movie are not available yet. Or maybe you did not select the showtime");
                }}
               
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
