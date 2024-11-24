package com.parkinglot.parkingstrategy;

import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingLotException;

import java.util.List;

public class SuperParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .reduce((parkingLot1, parkingLot2) -> (double) parkingLot1.getAvailablePosition() / parkingLot1.getCapacity() >= (double) parkingLot2.getAvailablePosition() / parkingLot2.getCapacity() ? parkingLot1 : parkingLot2)
                .orElseThrow(ParkingLotException::notEnoughPosition);
    }
}
