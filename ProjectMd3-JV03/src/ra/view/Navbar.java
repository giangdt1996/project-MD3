package ra.view;

import ra.config.Constants;
import ra.config.InputMethods;
import ra.controller.MovieController;
import ra.controller.TicketController;
import ra.controller.UserController;
import ra.model.*;
import ra.service.TicketService;


import java.util.Arrays;
import java.util.List;


public class Navbar {
    private static UserController userController = new UserController();
    private static MovieController movieController = new MovieController();
    private static TicketController ticketController = new TicketController();
    private static MovieManager movieManager;
    private static UserMananger userMananger = new UserMananger();

    private static TicketService ticketService = new TicketService();
    public static User userLogin;
    // tat ca menu dao dien dieu huong

    public static void menuStore() {
        while (true) {
            System.out.println("-------------Menu-Store-------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Search Movie By Name");
            System.out.println("4. Search Movie By Genre");
            System.out.println("5. Exit");
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    movieController.searchMovieByName();
                    break;
                case 4:
                    movieController.searchMovieByGenre();
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.err.println("please enter number from 1 to 4");
            }

        }
    }

    public static void menuUser() {
        while (true) {
            System.out.println("-------------Menu-User-------------");
            System.out.println("1. Change Password");
            System.out.println("2. Change Name");
            System.out.println("3. View History");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("0. Log Out");
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                 changePassword();
                    break;
                case 2:
                changeName();
                    break;
                case 3:
                ticketService.myHistory();
                    break;
                case 4:
                    userMananger.bookTicket();
                    break;
                case 5:
                    cancelTicket();
                    break;
                case 0:
                    logOut();
                    break;
                default:
                    System.err.println("please enter number from 1 to 4");
            }
            if (choice == 0) {
                break;
            }
        }
    }

    public static void menuAdmin() {
        while (true){
        System.out.println("-------------Menu-Admin-------------");
        System.out.println("1. Account Manager");
        System.out.println("2. Movie Manager");
        System.out.println("3. Ticket Manager");
        System.out.println("4. Create Account");
        System.out.println("0. Logout");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                // Quản lí tài khoản người dùng
                new UserMananger(userController);
                break;
            case 2:
                new MovieManager(movieController);
                break;
            case 3:
                new TicketManager(ticketController);
                break;
                case 4:
            register();
                break;
            case 0:

                logOut();
                break;
            default:
                System.err.println("please enter number from 1 to 4");
        }
        if (choice == 0) {
            break;
        }
    }
    }
    public  static void menuAccountManager(){
        System.out.println("-------------Menu-Account-Manager-------------");
        System.out.println("1. Show All Acount");
        System.out.println("2. Block/Unblock Acount");
        System.out.println("3. Back");
    }
    public  static void menuMovieManager(){
        System.out.println("-------------Menu-Movie-Manager-------------");
        System.out.println("1. Show All Movie");
        System.out.println("2. Change Status");
        System.out.println("3. Add New Movie");
        System.out.println("4. Delete Movie");
        System.out.println("5. Edit Movie");
        System.out.println("6. Back");
    }
    public static void menuTicketManager(){
        System.out.println("-------------Menu-Ticket-Manager-------------");
        System.out.println("1. Show All Ticket");
        System.out.println("2. Show All Confirmed Ticket");
        System.out.println("3. Show All Unconfirmed Ticket");
        System.out.println("4. Show All Failed Ticket");
        System.out.println("5. Confirmed/Cancel Ticket");
        System.out.println("6. Back");
    }
    public static void login() {
        System.out.println("-------------Sign-In-------------");
        System.out.println("Enter username");
        String username = InputMethods.getString();
        System.out.println("Enter password");
        String password = InputMethods.getString();
        // kiem tra dang nhap
        User user = userController.login(username, password);
        if (user == null) {
            System.err.println("Wrong username or password");
        } else {
            if (user.getRoles().contains(RoleName.ADMIN)) {
                userLogin = user;
                menuAdmin();

            } else {
                if (user.isStatus()) {
                    userLogin = user;
                    menuUser();
                } else {
                    System.err.println("Your account is blocked");
                    login();
                }
            }
        }

    }

    ;

    public static void register() {
        System.out.println("-------------Create an account-------------");
        User user = new User();
        user.setId(userController.getNewId());
        System.out.println("ID : " + user.getId());
        System.out.println("Enter Name");
        user.setName(InputMethods.getString());
        System.out.println("Enter Username");
        user.setUsername(InputMethods.getString());
        System.out.println("Enter Password");
        user.setPassword(InputMethods.getString());
        System.out.println("Enter Roles: (etc: user,admin,...)");
        String roles = InputMethods.getString();
        String[] stringRoles = roles.split(",");
        List<String> listRoles = Arrays.asList(stringRoles);
        for (String r : stringRoles) {
            // loi dụng co che break
            switch (r) {
                case "admin":
                    user.getRoles().add(RoleName.ADMIN);
                case "manager":
                    user.getRoles().add(RoleName.MANAGER);
                case "user":
                    user.getRoles().add(RoleName.USER);
                default:
                    user.getRoles().add(RoleName.USER);
            }
        }
//        user.getRoles() = listRoles.stream().map(
//                r->{
//                    //
//                }
//        ).collect(Collectors.toList());
        userController.save(user);
        System.out.println(Constants.SUCCESS);
        login();

    }
    public static void registerUser() {
        System.out.println("-------------Sign_Up-------------");
        User user = new User();
        user.setId(userController.getNewId());
        System.out.println("ID : " + user.getId());
        System.out.println("Enter Name");
        user.setName(InputMethods.getString());
        System.out.println("Enter Username");
        user.setUsername(InputMethods.getString());
        System.out.println("Enter Password");
        user.setPassword(InputMethods.getString());
        user.getRoles().add(RoleName.USER);
        userController.save(user);
        System.out.println(Constants.SUCCESS);


    }
    public static void changePassword(){

           System.out.println("Enter new password");
           String newPassword = InputMethods.getString();
         userLogin.setPassword(newPassword);
        userController.save(userLogin);
        System.out.println(Constants.SUCCESS);
    }
    public static void changeName(){
        System.out.println("Enter new name");
        String newName = InputMethods.getString();
        userLogin.setName(newName);
        userController.save(userLogin);
        System.out.println(Constants.SUCCESS);
    }


    public static void logOut() {
        userLogin = null;
        menuStore();

    }
    public static void cancelTicket(){
        TicketController ticketController = new TicketController();
       while(true){ System.out.println("Enter ID ticket");
        int id = InputMethods.getInteger();
        Ticket ticket = ticketController.findById(id);
        if(ticket != null&& ticket.getUserName().equals(userLogin.getName())){
            Movie selectedMovie = ticket.getMovie();
            ticket.setTicketStatus(2);
            selectedMovie.setQuantity(selectedMovie.getQuantity()+ ticket.getQuantity());
            ticketController.save(ticket);
            movieController.save(selectedMovie);
            System.out.println(Constants.SUCCESS);
           break;
        }else{
            System.out.println(Constants.NOT_FOUND);;
        }
    }}

}
