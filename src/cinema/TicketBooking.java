package cinema;

public class TicketBooking {
    private int id;
    private int showtime_id;
    private int seats;
    private int user_id;

    public TicketBooking(int id, int showtime_id, int seats, int user_id) {
        this.id = id;
        this.showtime_id = showtime_id;
        this.seats = seats;
       
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public int getShowtimeId() {
        return showtime_id;
    }

    public int getNumSeats() {
        return seats;
    }
    
     public int getUserId() {
        return user_id;
    }
    
    
}
