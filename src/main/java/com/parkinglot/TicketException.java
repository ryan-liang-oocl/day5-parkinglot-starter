package com.parkinglot;

public class TicketException extends RuntimeException {
    public static final String WRONG_TICKET_MSG = "wrong ticket.";
    public static final String REUSED_TICKET_MSG = "reused ticket.";

    public TicketException(String message) {
        super(message);
    }

    public static TicketException wrongTicket () {
        return new TicketException(WRONG_TICKET_MSG);
    }

    public static TicketException reusedTicket () {
        return new TicketException(REUSED_TICKET_MSG);
    }

}
