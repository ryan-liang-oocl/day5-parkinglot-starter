package com.parkinglot.parkingstrategy;

import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingLotException;

import java.util.List;

public class FirstAvailableParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLotList) {
        return parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(ParkingLotException::notEnoughPosition);
    }
}
