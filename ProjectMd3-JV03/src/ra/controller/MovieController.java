package ra.controller;

import ra.model.Movie;
import ra.service.MovieService;

import java.io.Serializable;
import java.util.List;

public class MovieController implements Serializable {
    private MovieService movieService;

    public MovieController() {
        movieService  = new MovieService();
    }

    public List<Movie> findAll() {
        return movieService.findAll();
    }
    public void save(Movie movie) {
        movieService.save(movie);
    }


    public void delete(Integer id) {
        movieService.delete(id);
    }


    public Movie findById(Integer id) {
        return movieService.findById(id);
    }
    public void changeStatus(int idMovie){
        movieService.changeStatus(idMovie);
    }

    public int getNewId(){
        return movieService.getNewId();
    }
    public Movie searchMovieByName(){
        return movieService.searchMovieByName();
    }
    public Movie searchMovieByGenre(){
        return movieService.searchMovieByGenre();
    }

}
