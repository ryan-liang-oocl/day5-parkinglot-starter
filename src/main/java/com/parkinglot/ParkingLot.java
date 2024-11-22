package com.parkinglot;

import java.util.HashMap;

public class ParkingLot {
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
        capacity++;
        return parkingRecords.remove(ticket);
    }
}
