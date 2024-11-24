package com.parkinglot;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<Ticket, Car> parkingRecords = new HashMap<>();

    private Integer id = 0;

    private int capacity = 10;

    private int carAmount = 0;

    public ParkingLot() {
    }

    public ParkingLot(Integer id) {
        this.id = id;
    }

    public ParkingLot(Integer id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getCarAmount() {
        return carAmount;
    }

    public Ticket park(Car car) {
        if (carAmount >= capacity) {
            return null;
        }
        Ticket ticket = new Ticket(id);
        parkingRecords.put(ticket, car);
        carAmount++;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if (ticket.isUsed()) {
            throw TicketException.reusedTicket();
        }
        if (!parkingRecords.containsKey(ticket)) {
            throw TicketException.wrongTicket();
        }
        ticket.setUsed(true);
        carAmount--;
        return parkingRecords.remove(ticket);
    }

    public boolean isFull() {
        return carAmount >= capacity;
    }

    public int getAvailablePosition() {
        return capacity - carAmount;
    }

    public int getCapacity() {
        return capacity;
    }
}
