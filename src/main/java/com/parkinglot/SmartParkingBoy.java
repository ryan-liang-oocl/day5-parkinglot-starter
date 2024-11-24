package com.parkinglot;

public class SmartParkingBoy extends ParkingBoy {
    @Override
    public Ticket park(Car car) {
        return parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .reduce((parkingLot1, parkingLot2) -> {
                    if (parkingLot1.getAvailablePosition() >= parkingLot2.getAvailablePosition()) {
                        return parkingLot1;
                    }
                    return parkingLot2;
                })
                .map(parkingLot -> {
                    Ticket ticket = parkingLot.park(car);
                    ticketList.add(ticket);
                    return ticket;
                })
                .orElseThrow(ParkingLotException::notEnoughPosition);
    }
}
