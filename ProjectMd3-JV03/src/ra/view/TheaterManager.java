package ra.view;

import ra.controller.MovieController;
import ra.model.Genre;
import ra.model.Movie;
import ra.model.Theater;
import ra.model.Time;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class TheaterManager {
    public static List<Genre> genres = new ArrayList<>();

    public static List<Theater> theaters = new ArrayList<>();
    public static List<Time> times = new ArrayList<>();
    public static MovieController movieController = new MovieController();

    public static void main(String[] args) {
        System.out.println("------------NOW SHOWING MOVIES------------");
        showAllMovies();


        Genre genre = new Genre(1,"Horror");
        Genre genre2 = new Genre(2,"Sitcom");
        Genre genre3 = new Genre(3,"Action");
        Genre genre4 = new Genre(4,"Cartoon");
        Genre genre5 = new Genre(5,"Drama");
        genres.add(genre);
        genres.add(genre2);
        genres.add(genre3);
        genres.add(genre4);
        genres.add(genre5);

        Theater theater = new Theater(1,"CGV Tran Duy Hung");
        Theater theater2 = new Theater(2,"CGV Royal");
        Theater theater3 = new Theater(3,"CGV Long Bien");
        Theater theater4 = new Theater(4,"CGV Pham Hung");
        Theater theater5 = new Theater(5,"CGV SkyLake");
       theaters.add(theater);
       theaters.add(theater2);
       theaters.add(theater3);
       theaters.add(theater4);
       theaters.add(theater5);
       Time time = new Time(1,"10:30");
       Time time2 = new Time(2,"12:00");
       Time time3 = new Time(3,"13:30");
       Time time4 = new Time(4,"15:00");
       Time time5 = new Time(5,"17:30");
       Time time6 = new Time(6,"19:00");
       Time time7 = new Time(7,"20:30");
       Time time8 = new Time(8,"22:00");
       times.add(time);
       times.add(time2);
       times.add(time3);
       times.add(time4);
       times.add(time5);
       times.add(time6);
       times.add(time7);
       times.add(time8);

        Navbar.menuStore();
    }

    public static void showAllMovies(){
        for (Movie u: movieController.findAll()) {
            System.out.println("-------------------------------------------");
            System.out.println(u);;
        }}


}
