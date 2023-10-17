package ra.service;

import ra.config.Constants;
import ra.config.InputMethods;
import ra.model.Movie;
import ra.model.Theater;
import ra.model.Ticket;
import ra.model.Time;
import ra.util.DataBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ra.view.Navbar.userLogin;
import static ra.view.TheaterManager.*;

public class TicketService implements IGenericService<Ticket, Integer> {
    public Movie movie = new Movie();

    public List<Ticket> tickets;
    public Ticket ticket = new Ticket();
    private DataBase<Movie> movieData = new DataBase();
    private DataBase<Ticket> ticketData = new DataBase();
    List<Movie> movies = movieData.readFromFile(DataBase.MOVIE_PATH);

    public TicketService() {
        List<Ticket> list = ticketData.readFromFile(DataBase.TICKET_PATH);
        if (list == null) {
            list = new ArrayList<>();
        }
        this.tickets = list;// du lieu doc tu file
    }

    @Override
    public List<Ticket> findAll() {
        return tickets;
    }

    @Override
    public void save(Ticket ticket) {
        if (findById(ticket.getId()) == null) {
            // add
            tickets.add(ticket);
        } else {
            // update
            tickets.set(tickets.indexOf(findById(ticket.getId())), ticket);
        }
        // luu vao file
        ticketData.writeToFile(tickets, DataBase.TICKET_PATH);
    }

    @Override
    public void delete(Integer id) {
        tickets.remove(findById(id));
        ticketData.writeToFile(tickets, DataBase.TICKET_PATH);
    }

    @Override
    public Ticket findById(Integer id) {
        for (Ticket tk : tickets) {
            if (tk.getId() == id) {
                return tk;
            }
        }
        return null;
    }

    public Ticket inputData() {


        int idTicket = getNewId();

        // Khởi tạo đối tượng Ticket
        Ticket ticket = new Ticket();

        System.out.println("Choose Movie");
        for (Movie mv : movies) {
            System.out.println(mv.toString());
        }
        while (true) {
            System.out.println("Enter Movie by ID");
            int id = InputMethods.getInteger();
            boolean flag = false;
            for (Movie mv : movies) {
                if (mv.getId() == id) {
                    ticket.setMovie(mv);
                    flag = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy phim
                }
            }
            if (flag) {
                break;
            }
        }

        // Lưu trữ thông tin phim đã chọn vào biến riêng
        Movie selectedMovie = ticket.getMovie();

        System.out.println("Choose Theater");
        for (Theater tt : theaters) {
            System.out.println(tt);
        }
        while (true) {

            System.out.println("Enter Theater by ID");
            int id = InputMethods.getInteger();
            boolean flag = false;
            for (Theater tt : theaters) {
                if (tt.getId() == id) {
                    ticket.setTheater(tt);
                    flag = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy rạp chiếu
                }
            }
            if (flag) {
                break;
            }
        }
        int day;
        int month;
        while (true) {
            System.out.println("Enter the month");
            month = InputMethods.getInteger();
            if (1 > month || month > 12) {
                System.out.println("Month must be between 1 and 12");
            } else {
                ticket.setMonth(month);
                break;
            }
        }
        boolean check = true;

            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    while (true) {
                        System.out.println("Enter the day");
                        day = InputMethods.getInteger();
                    if (day < 1 || day > 31) {
                        System.err.println("Day must be between 1 and 31");
                    } else {
                        ticket.setDay(day);
                        break;
                    }
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    while (true) {
                        System.out.println("Enter the day");
                        day = InputMethods.getInteger();
                        if (day < 1 || day > 30) {
                            System.err.println("Day must be between 1 and 30");
                        } else {
                            ticket.setDay(day);
                            break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("Enter the day");
                        day = InputMethods.getInteger();
                        if (day < 1 || day > 28) {
                            System.err.println("Day must be between 1 and 28 ");
                        } else {
                            ticket.setDay(day);
                            break;
                        }
                    }
                    break;
                default:
                    System.err.println("Please enter day");
            }


        System.out.println("Choose Time");
        for (Time time : times) {
            System.out.println(time);
        }
        while (true) {
            System.out.println("Enter Time by ID");
            int id = InputMethods.getInteger();
            boolean flag = false;
            for (Time time : times) {
                if (time.getId() == id) {
                    ticket.setTime(time);
                    flag = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy thời gian chiếu
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println("Cinema Layout");
        System.out.println("---------------------------Screen---------------------------");
        System.out.println();

        int numRows = 6; // Số hàng
        int numSeatsPerRow = 11; // Số ghế trên mỗi hàng

        char rowLabel = 'A'; // Nhãn hàng bắt đầu từ A

        for (int row = 1; row <= numRows; row++) {
            System.out.print(rowLabel + ": ");
            for (int seat = 1; seat <= numSeatsPerRow; seat++) {
                System.out.printf("[%c%d] ", rowLabel, seat);
            }
            System.out.println();
            rowLabel++; // Di chuyển tới nhãn hàng tiếp theo
        }
        while (true) {
            List<String> seats = new ArrayList<>();
            System.out.println("Enter seats");
            String chosenSeats = InputMethods.getString();
            String[] chosenSeatArray = chosenSeats.split(",");
//                String pattern = "[A-F][1-9][0-1]?,[A-F][1-9][0-1]?";
            String pattern = "[A-F][1-9]";
            boolean isInvalidFormat = true;
//                boolean isInvalidFormat = !chosenSeats.matches(pattern);
            for (String str : chosenSeatArray
            ) {
                if (!str.matches(pattern)) {
                    isInvalidFormat = false;
                    break;
                }
            }
            if (!isInvalidFormat) {
                System.err.println("Invalid seat format. Please enter seats in the format A1,A2,B1,B2...");
                continue;
            }


            for (String seat : chosenSeatArray) {
                seats.add(seat.trim());
            }
            ticket.setSeats(seats);
            boolean isBooked = checkIfTicketIsBooked(ticket);
            if (isBooked) {
                System.out.println("Your chosen seats have been booked.");
                // Yêu cầu nhập lại thông tin vé
            } else {
                int quantity = seats.size();
                System.out.println("Quantity of your seat: " + quantity);
                if (quantity > selectedMovie.getQuantity()) { // So sánh với số lượng phim đã chọn
                    System.err.println("Not enough stock of this film's tickets");
                } else {
                    ticket.setQuantity(quantity);
                    selectedMovie.setQuantity(selectedMovie.getQuantity() - ticket.getQuantity());
                    if (selectedMovie.getQuantity() == 0) {
                        selectedMovie.setMovieStatus(false);
                    }
                    movieController.save(selectedMovie);
                    break;
                }
            }

        }
        String currentUser = userLogin.getUsername();
        // Gán giá trị cho các thuộc tính khác của đối tượng Ticket
        ticket.setId(idTicket);
        ticket.setUserName(currentUser);
        ticket.setTicketStatus(0);

        return ticket;
    }

    public int getNewId() {
        int max = 0;
        for (Ticket tk : tickets
        ) {
            if (tk.getId() > max) {
                max = tk.getId();
            }
        }
        return max + 1;
    }

    public void changeTicketStatus(int id) {
        Ticket tk = findById(id);
        if (tk == null) {
            System.err.println(Constants.NOT_FOUND);
            return;
        } else {
            while (true) {
                System.out.println("Enter new ticket status");
                System.out.println("0. Confirming");
                System.out.println("1. Confirmed/Succeeded");
                System.out.println("2. Failed");
                int status = InputMethods.getInteger();
                switch (status) {
                    case 0:
                        ticket.setTicketStatus(0);
                        save(tk);
                        break;
                    case 1:
                        ticket.setTicketStatus(1);
                        save(tk);
                        break;
                    case 2:
                        ticket.setTicketStatus(2);
                        save(tk);
                        break;
                    default:
                        System.out.println("Please choose 0-2");
                }

            }

        }


    }

    public void myHistory() {
        this.tickets = ticketData.readFromFile(DataBase.TICKET_PATH);
        boolean check = false;
        for (Ticket ticket : tickets) {
            if (userLogin.getUsername().equals(ticket.getUserName()) && tickets != null) {
                check = true;
                System.out.println(ticket);
            }
        }
        if (!check) {
            System.out.println("You haven`t booked any tickets");
        }
    }

    private boolean checkIfTicketIsBooked(Ticket ticket) {
        this.tickets = ticketData.readFromFile(DataBase.TICKET_PATH);
        for (Ticket t : tickets) {
            if (t.getMovie().getName().equals(ticket.getMovie().getName()) && t.getDay() == ticket.getDay()
                    && t.getMonth() == ticket.getMonth() && t.getTheater().getName().equals(ticket.getTheater().getName())
                    && t.getTime().getTime().equals(ticket.getTime().getTime()) && checkIfSeatsOverlap(t.getSeats(), ticket.getSeats())
            && t.getTicketStatus()!=2) {
                return true; // Vé đã được đặt
            }
        }
        return false; // Vé chưa được đặt
    }

    private boolean checkIfSeatsOverlap(List<String> seats1, List<String> seats2) {
        for (String seat1 : seats1) {
            for (String seat2 : seats2) {
                if (seat1.equals(seat2)) {
                    return true; // Ghế đã bị trùng lặp
                }
            }
        }
        return false; // Không có ghế nào trùng lặp

    }
}
