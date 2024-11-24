package com.parkinglot.parkingstrategy;

import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingLotException;

import java.util.List;

public class SmartParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .reduce((parkingLot1, parkingLot2) -> parkingLot1.getAvailablePosition() >= parkingLot2.getAvailablePosition() ? parkingLot1 : parkingLot2)
                .orElseThrow(ParkingLotException::notEnoughPosition);
    }
}
