package com.parkinglot;

public class SuperParkingBoy extends ParkingBoy {

    public double getRate(ParkingLot parkingLot) {
        return (double) parkingLot.getAvailablePosition() / parkingLot.getCapacity();
    }

    @Override
    public Ticket park(Car car) {
        return parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .reduce((parkingLot1, parkingLot2) -> getRate(parkingLot1) >= getRate(parkingLot2) ? parkingLot1 : parkingLot2)
                .map(parkingLot -> {
                    Ticket ticket = parkingLot.park(car);
                    ticketList.add(ticket);
                    return ticket;
                })
                .orElseThrow(ParkingLotException::notEnoughPosition);
    }

}
