package ra.view;

import ra.config.Constants;
import ra.config.InputMethods;
import ra.controller.TicketController;
import ra.controller.UserController;
import ra.model.Ticket;
import ra.model.User;
import ra.service.TicketService;

public class UserMananger {
    private UserController userController = new UserController();
    private TicketController ticketController = new TicketController();
    private TicketService ticketsService = new TicketService();

    public UserMananger() {
    }

    public UserMananger(UserController userController) {
        this.userController = userController;
        while (true){
            Navbar.menuAccountManager();
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showAllAccount();
                    break;
                case 2:
                    changeStatus();
                    break;
                case 3:
                    Navbar.menuAdmin();
                    break;
                default:
                    System.err.println("please enter number from 1 to 4");
            }

        }
    }
    public void showAllAccount(){
        for (User u: userController.findAll()) {
            System.out.println("-------------------------------------------");
            System.out.println(u);
        }
    }
    public void changeStatus(){
        // lấy ra userlogin để check quyền xem có được quyền khóa tài khoản kia không
        System.out.println("Enter Account ID");
        int id = InputMethods.getInteger();
        User user = userController.findById(id);
        if(user==null){
            System.err.println(Constants.NOT_FOUND);
        }else {
            user.setStatus(!user.isStatus());
            userController.save(user);
        }
    }
    public void bookTicket(){

      Ticket ticket = ticketsService.inputData();
      ticketController.save(ticket);
        System.out.println("Booked Successfully. Please wait admin confirm");

    }


}
