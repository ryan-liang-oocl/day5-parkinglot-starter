package com.parkinglot;

import com.parkinglot.parkingstrategy.FirstAvailableParkingStrategy;
import com.parkinglot.parkingstrategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList = new ArrayList<>();
    protected List<Ticket> ticketList = new ArrayList<>();

    private ParkingStrategy parkingStrategy = new FirstAvailableParkingStrategy();

    public ParkingBoy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public ParkingBoy() {}

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotList.add(parkingLot);
    }

    public Ticket park(Car car) {
        ParkingLot selectedParkingLot = parkingStrategy.selectParkingLot(parkingLotList);
        Ticket ticket = selectedParkingLot.park(car);
        ticketList.add(ticket);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if (!ticketList.contains(ticket)) {
            throw TicketException.wrongTicket();
        }
        return parkingLotList.get(ticket.getParkingLotId() - 1).fetch(ticket);
    }

}
