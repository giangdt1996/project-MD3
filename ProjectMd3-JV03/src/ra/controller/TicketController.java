package ra.controller;

import ra.model.Ticket;
import ra.service.TicketService;

import java.io.Serializable;
import java.util.List;

public class TicketController implements Serializable {
    private TicketService ticketService;

    public TicketController() {
        ticketService  = new TicketService();
    }

    public List<Ticket> findAll() {
        return ticketService.findAll();
    }
    public void save(Ticket ticket) {
        ticketService.save(ticket);
    }


    public void delete(Integer id) {
        ticketService.delete(id);
    }


    public Ticket findById(Integer id) {
        return ticketService.findById(id);
    }
    public void changeTicketStatus(int idTicket){
        ticketService.changeTicketStatus(idTicket);
    }

    public int getNewId(){
        return ticketService.getNewId();
    }

}

