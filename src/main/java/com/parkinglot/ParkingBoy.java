package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;
    protected List<Ticket> ticketList;

    public ParkingBoy() {
        parkingLotList = new ArrayList<>();
        ticketList = new ArrayList<>();
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotList.add(parkingLot);
    }

    public Ticket park(Car car) {
        return parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .map(parkingLot -> {
                    Ticket ticket = parkingLot.park(car);
                    ticketList.add(ticket);
                    return ticket;
                })
                .orElseThrow(ParkingLotException::notEnoughPosition);
    }

    public Car fetch(Ticket ticket) {
        if (!ticketList.contains(ticket)) {
            throw TicketException.wrongTicket();
        }
        return parkingLotList.get(ticket.getParkingLotId() - 1).fetch(ticket);
    }
}
