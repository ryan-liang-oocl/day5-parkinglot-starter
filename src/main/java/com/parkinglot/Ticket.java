package com.parkinglot;

public class Ticket {

    private Integer parkingLotId;
    private boolean isUsed;

    public Ticket() {
        this.isUsed = false;
    }

    public Ticket(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
