package cinema;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private String director;
    private String year;
    private String duration;

    public Movie(int id, String title, String genre, String director, String year, String duration) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getYear() {
        return year;
    }

    public String getDuration() {
        return duration;
    }
    
    
    
    public void setTitle(String title) {
        this.title=title;
    }

    public void setGenre(String genre) {
        this.genre=genre;
    }

    public void setDirector(String director) {
        this.director=director;
    }

    public void setYear(String year) {
       this.year=year;
    }

    public void setDuration(String duration) {
        this.duration=duration;
    }
    
    public void setId(int id) {
        this.id=id;
    }
}

