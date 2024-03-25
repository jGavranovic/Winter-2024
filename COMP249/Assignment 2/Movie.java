/*
 * This is a class to create Movie obecjts
 * 
 * 
 */

import java.io.Serializable;
import movieExceptions.*;

public class Movie implements Serializable{
    private String year, title, duration, genre, rating, score, director, actor1, actor2, actor3;
    private String[] validRatings  = {"PG","Unrated", "G", "R", "PG-13", "NC-17"};
    private String[] validGenres = {"musical", "comedy", "animation", "adventure", "drama","crime","biography","horror","action","documentary","fantasy","mystery","sci-fi","family","romance","thriller","western"};

    public Movie(String year, String title, String duration, String genre, String rating, String score, String director,
            String actor1, String actor2, String actor3) throws BadYearException, BadTitleException, BadDurationException, BadGenreException, BadRatingException, BadScoreException ,BadDirectorException, BadNameException{
        int yearInt = 0;
        try {yearInt = Integer.parseInt(year);} catch (NumberFormatException nfe) {throw new BadYearException();}
        if (year.equals("")|| yearInt<1990 || yearInt>1999)
            throw new BadYearException();
        this.year = year;
        if (title.equals(""))
            throw new BadTitleException();
        this.title = title;
        if (duration.equals("") || Integer.parseInt(duration)<30||Integer.parseInt(duration)>300)
                throw new BadDurationException();
        this.duration = duration;
        boolean validGenre = false;
        for (String s : validGenres) {
            if (s.equalsIgnoreCase(genre))
                validGenre = true;
        }

        if (!validGenre)
            throw new BadGenreException();
        this.genre = genre;
        boolean validRating = false;
        for (String s : validRatings) {
            if (s.equalsIgnoreCase(rating))
                validRating = true;
        }

        if (!validRating)
            throw new BadRatingException();
        this.rating = rating;
        if (score.equals("")||Double.parseDouble(score)<0 || Double.parseDouble(score)>10)
            throw new BadScoreException();
        this.score = score;
        if (director.equals(""))
            throw new BadDirectorException();
        this.director = director;
        if (actor1.equals("")||actor2.equals("")||actor3.equals(""))
            throw new BadNameException();
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
    }

    public Movie(String[] attributes) throws BadYearException, BadTitleException, BadNameException, BadDirectorException, BadScoreException, BadDurationException, BadNameException,BadGenreException,BadRatingException{
        this(attributes[0], attributes[1], attributes[2], attributes[3], attributes[4],attributes[5],attributes[6],attributes[7],attributes[8],attributes[9]);
    }
    
    public String getYear(){
        return this.year;
    }

    public void setYear(String year){
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public String getActor3() {
        return actor3;
    }

    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    @Override
    public String toString(){
        String output = "-----MOVIE INFORMATION---";
        output+="\n\tYear: "+year;
        output+="\n\tTitle: "+title;
        output+="\n\tDuration: "+duration;
        output+="\n\tGenre: "+genre;
        output+="\n\tRating: "+rating;
        output+="\n\tScore: "+score;
        output+="\n\tDirector: "+director;
        output+="\n\tActor 1: "+actor1;
        output+="\n\tActor 2: "+actor2;
        output+="\n\tActor 3: "+actor3;

        return output;
    }
    
    @Override
    public boolean equals(Object object){
        if (object == null)
            return false;
        
        if (getClass() != object.getClass())
            return false;
        
        Movie otherMovie = (Movie)object;
    
        return title.equals(otherMovie.title) && year.equals(otherMovie.year) && duration.equals(otherMovie.duration) && director.equals(otherMovie.director);
    }
}

