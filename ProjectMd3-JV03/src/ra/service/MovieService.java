package ra.service;

import ra.config.Constants;
import ra.config.InputMethods;
import ra.model.*;
import ra.util.DataBase;
import static ra.view.TheaterManager.genres;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ra.view.TheaterManager.theaters;
import static ra.view.TheaterManager.times;

public class MovieService implements IGenericService<Movie,Integer>, Serializable{
    public Movie movie =new Movie();

    public List<Movie> movies ;
    private DataBase<Movie> movieData =new  DataBase();
    public MovieService() {
        List<Movie> list= movieData.readFromFile(DataBase.MOVIE_PATH);
        if(list ==null){
            list=new ArrayList<>();
        }
        this.movies = list;// du lieu doc tu file
    }
    @Override
    public List<Movie> findAll() {
        return movies;
    }

    @Override
    public void save(Movie movie) {
        if(findById(movie.getId())== null){
            // add
            movies.add(movie);
        }else {
            // update
            movies.set(movies.indexOf(findById(movie.getId())),movie);
        }
        // luu vao file
        movieData.writeToFile(movies,DataBase.MOVIE_PATH);
    }

    @Override
    public void delete(Integer id) {
        movies.remove(findById(id));
        movieData.writeToFile(movies,DataBase.MOVIE_PATH);
    }

    @Override
    public Movie findById(Integer id) {
        for (Movie movie :movies) {
            if(movie.getId()==id){
                return movie;
            }
        }
        return null;
    }
    public int getNewId(){
        int max = 0;
        for (Movie movie: movies
        ) {
            if(movie.getId() > max){
                max= movie.getId();
            }
        }
        return max+1;
    }
    public void changeStatus(int id){
        Movie movie = findById(id);
        if (movie==null){
            System.err.println(Constants.NOT_FOUND);
            return;
        }
        movie.setMovieStatus(!movie.isMovieStatus());
        save(movie);
    }
    public Movie searchMovieByName(){
        System.out.println("Enter movie's name");
        String name = InputMethods.getString();
        for (Movie movie: movies){
            if((movie.getName()).contains(name)){
                System.out.println(movie);
                return movie;
            }

        }
        System.out.println(Constants.ERROR);
        return null;
    }
    public Movie searchMovieByGenre(){
//        this.movies = movieData.readFromFile(DataBase.MOVIE_PATH);
        String genre ="";
        System.out.println("Genre");
        for(Genre g :genres){
            g.Display();
        }
        while (true){
            System.out.println("Enter genre by ID");
            int id = InputMethods.getInteger();

            for(Genre g : genres){
                if(g.getId()==id){
                    genre=g.getNameGenre();
                  break;
                }else {
                    System.out.println(Constants.NOT_FOUND);
                }

        }
            break;
        }

        for (Movie movie: movies){

            if(movie.getGenre().getNameGenre().equals(genre)){
                System.out.println("aaa");
                System.out.println(movie);
                return movie;
            }
        }
        return null;
    }

}
