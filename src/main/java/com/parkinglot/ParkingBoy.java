package com.parkinglot;

import com.parkinglot.parkingstrategy.FirstAvailableParkingStrategy;
import com.parkinglot.parkingstrategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;
    protected List<Ticket> ticketList;

    private ParkingStrategy parkingStrategy = new FirstAvailableParkingStrategy();

    public ParkingBoy(ParkingStrategy parkingStrategy) {
        parkingLotList = new ArrayList<>();
        ticketList = new ArrayList<>();
        this.parkingStrategy = parkingStrategy;
    }

    public ParkingBoy() {
        parkingLotList = new ArrayList<>();
        ticketList = new ArrayList<>();
    }

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
