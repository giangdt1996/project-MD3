package ra.model;

import ra.config.InputMethods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ra.view.TheaterManager.*;

public class Ticket implements Serializable {
   private int id;
   private Movie movie;
   private Theater theater;
   private Time time;
   private int quantity;
   private int day;
   private int month;
   private String userName;

   private int ticketStatus;
   public List<String> seats = new ArrayList<>();

    public Ticket() {
    }

    public Ticket(int id, Movie movie, Theater theater, Time time, int quantity, int day, int month, String userName, int ticketStatus, List<String> seats) {
        this.id = id;
        this.movie = movie;
        this.theater = theater;
        this.time = time;
        this.quantity = quantity;
        this.day = day;
        this.month = month;
        this.userName = userName;
        this.ticketStatus = ticketStatus;
        this.seats = seats;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public Ticket(int id, Movie movie, Theater theater, Time time, int quantity, int day, int month, String userName, int ticketStatus) {
        this.id = id;
        this.movie = movie;
        this.theater = theater;
        this.time = time;
        this.quantity = quantity;
        this.day = day;
        this.month = month;
        this.userName = userName;
        this.ticketStatus = ticketStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(int ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        String ticketStatusString = "";
        if (ticketStatus == 0) {
            ticketStatusString = "Confirming Waiting";
        } else if (ticketStatus == 1) {
            ticketStatusString = "Confirmed";
        } else {
            ticketStatusString = "Canceled";
        }

        String border = "+--------------------------+\n";
        return border +
                "|       Ticket              |\n" +
                border +
                String.format("| ID:      %8d        |\n", id) +
                String.format("| Movie:   %-15s |\n", movie.getName()) +
                String.format("| Theater: %-15s |\n", theater.getName()) +
                String.format("| Time:    %-10s |\n", time.getTime()) +
                String.format("| Date:    %2d/%-10d |\n", day, month) +
                String.format("|Seats:    %-15s |\n",seats)+
                String.format("| Quantity:%8d        |\n", seats.size()) +
                String.format("| Ticket Status: %-12s |\n", ticketStatusString) +
                border;
    }
}
