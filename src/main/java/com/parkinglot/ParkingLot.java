package com.parkinglot;

import java.util.HashMap;

public class ParkingLot {
    public static final String UNRECOGNIZED_PARKING_TICKET_MSG = "Unrecognized parking ticket";
    private HashMap<Ticket, Car> parkingRecords = new HashMap<>();

    private int capacity;

    public ParkingLot() {
        capacity = 10;
    }

    public Ticket park(Car car) {
        if (capacity == 0) {
            return null;
        }
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        capacity--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = parkingRecords.remove(ticket);
        if (car == null) {
            throw new RuntimeException(UNRECOGNIZED_PARKING_TICKET_MSG);
        } else  {
            capacity++;
        }
        return car;
    }
}
