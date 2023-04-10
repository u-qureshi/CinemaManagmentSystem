package cinema;
import java.util.Date;

public class Showtime {
    private int id;
    private int movie_id;
    private String startTime;
    private String endTime;

    public Showtime(int id, int movie_id, String startTime, String endTime) {
        this.id = id;
        this.movie_id = movie_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public int getMovie() {
        return movie_id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
