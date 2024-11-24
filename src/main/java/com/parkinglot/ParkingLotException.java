package com.parkinglot;

public class ParkingLotException extends RuntimeException{
    public static final String NOT_ENOUGH_POSITION_MSG = "Not enough position.";

    public ParkingLotException(String message) {
        super(message);
    }

    public static ParkingLotException notEnoughPosition() {
        return new ParkingLotException(NOT_ENOUGH_POSITION_MSG);
    }
}
