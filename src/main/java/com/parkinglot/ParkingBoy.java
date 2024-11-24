package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;
    private List<Ticket> ticketList;

    public ParkingBoy() {
        parkingLotList = new ArrayList<>();
        ticketList = new ArrayList<>();
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotList.add(parkingLot);
    }

    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLotList) {
            if (!parkingLot.isFull()) {
                Ticket ticket = parkingLot.park(car);
                ticketList.add(ticket);
                return ticket;
            }
        }
        throw ParkingLotException.notEnoughPosition();
    }

    public Car fetch(Ticket ticket) {
        if (!ticketList.contains(ticket)) {
            throw TicketException.wrongTicket();
        }
        return parkingLotList.get(ticket.getParkingLotId() - 1).fetch(ticket);
    }
}
