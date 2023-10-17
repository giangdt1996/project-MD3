package ra.view;

import ra.config.Constants;
import ra.config.InputMethods;
import ra.controller.MovieController;
import ra.model.Movie;
import ra.service.MovieService;

public class MovieManager {


    private MovieController movieController;
    private MovieService movieService;

    public MovieManager(MovieController movieController) {
        this.movieController = movieController;
        while (true){
            Navbar.menuMovieManager();
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showAllMovie();
                    break;
                case 2:
                    changeStatus();
                    break;
                case 3:
                    addNewMovie();
                    break;
                case 4:
                    deleteMovie();
                    break;
                case 5:
                    editMovie();
                    break;
                case 6:
                    Navbar.menuAdmin();
                    break;
                default:
                    System.err.println("please enter number from 1 to 4");
            }

        }
    }

    public MovieManager() {
    }

    public void showAllMovie(){
        for (Movie u: movieController.findAll()) {
            System.out.println("-------------------------------------------");
            System.out.println(u);
        }
    }
    public void changeStatus(){
        // lấy ra movielogin để check quyền xem có được quyền khóa tài khoản kia không
        System.out.println("Enter Movie ID");
        int id = InputMethods.getInteger();
        Movie movie = movieController.findById(id);
        if(movie==null){
            System.err.println(Constants.NOT_FOUND);
        }else {
            movie.setMovieStatus(!movie.isMovieStatus());
            movieController.save(movie);
            System.out.println(Constants.SUCCESS);
        }

    }
    public void addNewMovie(){
        Movie movie = new Movie();

        movie.inputData();
        movie.setId(new MovieService().getNewId());
        movieController.save(movie);

    }
    public void deleteMovie(){
        showAllMovie();
        System.out.println("Enter Movie ID");
        int idMovie = InputMethods.getInteger();
        movieController.delete(idMovie);

    }
    public void editMovie(){
        showAllMovie();
        System.out.println("Enter Movie ID");
        int idMovie = InputMethods.getInteger();

      Movie movie =  movieController.findById(idMovie);
      if(movie==null){
          System.out.println(Constants.NOT_FOUND);
      }else{
          movie.inputData();
          movie.setId(idMovie);
          movieController.save(movie);
      }
    }
}
