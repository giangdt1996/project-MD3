package ra.model;

import ra.config.InputMethods;
import ra.controller.MovieController;
import ra.service.MovieService;

import java.io.Serializable;

import static ra.view.TheaterManager.genres;


public class Movie implements Serializable {

    private int id;
    private String name;
    private String director;
    private int quantity;
    private double price;
    private Genre genre;
    private boolean movieStatus;


    public Movie() {
    }

    public Movie(int id, String name, String director, int quantity, double price, Genre genre, boolean movieStatus) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.quantity = quantity;
        this.price = price;
        this.genre = genre;
        this.movieStatus = movieStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isMovieStatus() {
        return movieStatus;
    }

    public void setMovieStatus(boolean movieStatus) {
        this.movieStatus = movieStatus;
    }
    public void inputData(){

        System.out.println("Enter name");
        name = InputMethods.getString();
        System.out.println("Enter name director");
        director = InputMethods.getString();
        System.out.println("Enter quantity");
        quantity = InputMethods.getInteger();
        System.out.println("Enter price");
        price = InputMethods.getDouble();
        System.out.println("Choose genre");
        for(Genre g :genres){
            g.Display();
        }
        while (true){
            System.out.println("Enter genre by ID");
            int id = InputMethods.getInteger();
            boolean flag=false;
            for(Genre g : genres){
                if(g.getId()==id){
                    this.genre=g;
                    flag=true;
                }
            }
            if(flag){
                break;
            }
        }
        System.out.println("Enter movieStatus (true/false)");
        movieStatus=InputMethods.getBoolean();
    }
    @Override
    public String toString() {
        return "Movie: "  + "Id=" + id + " | " + "Name: " + name + " | " + "Director: " + director + " | " + "Quantity: " + quantity + " | " + "Price/1: " + price +"$" + " | " + "Genre: " + genre.getNameGenre()  + " | " + "MovieStatus: " + ((movieStatus)?"Available":"Sold Out") ;
    }
}
