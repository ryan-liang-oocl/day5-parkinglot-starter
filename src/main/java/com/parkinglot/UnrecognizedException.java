package com.parkinglot;

public class UnrecognizedException extends RuntimeException {
    public static final String WRONG_TICKET_MSG = "wrong ticket.";
    public static final String REUSED_TICKET_MSG = "reused ticket.";

    public UnrecognizedException(String message) {
        super(message);
    }

    public static UnrecognizedException wrongTicket () {
        return new UnrecognizedException(WRONG_TICKET_MSG);
    }

    public static UnrecognizedException reusedTicket () {
        return new UnrecognizedException(REUSED_TICKET_MSG);
    }

}
