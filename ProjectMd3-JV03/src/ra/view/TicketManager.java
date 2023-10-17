package ra.view;

import ra.config.Constants;
import ra.config.InputMethods;
import ra.controller.MovieController;
import ra.controller.TicketController;
import ra.model.Movie;
import ra.model.Ticket;
import ra.model.User;
import ra.service.TicketService;

public class TicketManager {
    private static TicketService ticketsService = new TicketService();
    private static TicketController ticketsController = new TicketController();
    private static MovieController movieController = new MovieController();

    public TicketManager() {
    }

    public TicketManager(TicketController ticketsController) {
        this.ticketsController = ticketsController;
        while (true) {
            Navbar.menuTicketManager();
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showAllTicket();
                    break;
                case 2:
                    allConfirmedTicket();
                    break;
                case 3:
                    allUnconfirmedTicket();
                    break;
                case 4:
                    allCanceledTicket();
                    break;
                case 5:
                    ticketAction();
                    break;
                case 6:
                    Navbar.menuAdmin();
                    break;
                default:
                    System.out.println("Please choose a choice");

            }

        }
    }

    public static void showAllTicket() {
        for (Ticket tk : ticketsController.findAll()) {
            System.out.println("-------------------------------------------");
            System.out.println(tk);
        }
    }

    ;

    public static void allConfirmedTicket() {
        for (Ticket tk : ticketsController.findAll()) {
            if (tk.getTicketStatus() == 1) {
                System.out.println("-------------------------------------------");
                System.out.println(tk);
            }
        }
    }

    public static void allUnconfirmedTicket() {
        for (Ticket tk : ticketsController.findAll()) {
            if (tk.getTicketStatus() == 0) {
                System.out.println("-------------------------------------------");
                System.out.println(tk);
            }
        }
    }

    public static void allCanceledTicket() {
        for (Ticket tk : ticketsController.findAll()) {
            if (tk.getTicketStatus() == 2) {
                System.out.println("-------------------------------------------");
                System.out.println(tk);
            }
        }
    }

    public static void ticketAction() {
        System.out.println("Enter ticket ID");
        int id = InputMethods.getInteger();

        Ticket ticket = ticketsController.findById(id);
        if (ticket == null) {
            System.err.println(Constants.NOT_FOUND);
        } else {
            System.out.println(ticket);
            Movie movie = ticket.getMovie();
            System.out.println("Choose action");
            System.out.println("1. Confirm");
            System.out.println("2. Cancel");
            System.out.println("3. Unconfirmed");
            System.out.println("4. Back");
            while(true){
                int choice = InputMethods.getInteger();
                switch (choice){
                    case 1:
                        ticket.setTicketStatus(1);
                        System.out.println("Changed Success!!!");
                        System.out.println(ticket);
                        System.out.println("4. Back");
                        ticketsController.save(ticket);
                        break;
                    case 2:
                        ticket.setTicketStatus(2);
                        movie.setQuantity(movie.getQuantity()+ ticket.getQuantity());
                        System.out.println("Changed Success!!!");
                        System.out.println(ticket);
                        System.out.println("4. Back");
                        ticketsController.save(ticket);
                        movieController.save(movie);
                        break;
                    case 3:
                        ticket.setTicketStatus(0);
                        System.out.println("Changed Success!!!");
                        System.out.println(ticket);
                        System.out.println("4. Back");
                        ticketsController.save(ticket);
                        break;
                    case 4:
                        Navbar.menuAdmin();
                        break;
                    default:
                        System.out.println("Please choose a action from 1-4");

                }
            }
        }
    }
}
