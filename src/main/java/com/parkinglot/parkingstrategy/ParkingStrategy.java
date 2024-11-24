package com.parkinglot.parkingstrategy;

import com.parkinglot.ParkingLot;

import java.util.List;

public interface ParkingStrategy {
    ParkingLot selectParkingLot(List<ParkingLot> parkingLotList);
}
