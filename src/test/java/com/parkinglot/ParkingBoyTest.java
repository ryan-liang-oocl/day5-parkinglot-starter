package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingBoyTest {
    @Test
    public void should_return_ticket_given_a_car_when_park() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_the_car_when_fetch_given_a_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = parkingBoy.park(car);
        //When
        Car myCar = parkingBoy.fetch(ticket);
        //Then
        assertEquals(car, myCar);
    }

    @Test
    public void should_return_null_when_fetch_given_a_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(car);
        Ticket ticket = new Ticket();
        //When
        Car otherCar = null;
        try {
            otherCar = parkingBoy.fetch(ticket);
        } catch (Exception e) {
            //do nothing
        }
        //Then
        assertNull(otherCar);
    }

    @Test
    public void should_return_right_car_when_fetch_given_tickets() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        //When
        Car previousCar = parkingBoy.fetch(ticket1);
        Car laterCar = parkingBoy.fetch(ticket2);
        //Then
        assertEquals(car1, previousCar);
        assertEquals(car2, laterCar);
    }

    @Test
    public void should_return_null_when_fetch_given_a_used_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        //When
        Car myCar = null;
        try {
            myCar = parkingBoy.fetch(ticket);
        } catch (Exception e) {
            //do nothing
        }
        //Then
        assertNull(myCar);
    }

    @Test
    public void should_return_null_when_park_given_no_position_parking() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        IntStream.range(0, 10).forEach(i -> parkingBoy.park(new Car()));
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNull(ticket);
    }

    @Test
    public void should_throw_exception_when_park_given_unrecognized_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = new Ticket();
        //When
        //Then
        assertThrows(RuntimeException.class, () -> parkingBoy.fetch(ticket), UnrecognizedException.WRONG_TICKET_MSG);
    }

    @Test
    public void should_throw_exception_when_park_given_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = new Ticket();
        //When
        //Then
        assertThrows(RuntimeException.class, () -> parkingBoy.fetch(ticket), UnrecognizedException.WRONG_TICKET_MSG);
    }

    @Test
    public void should_throw_exception_when_park_given_reused_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        //When
        //Then
        assertThrows(RuntimeException.class, () -> parkingBoy.fetch(ticket), UnrecognizedException.WRONG_TICKET_MSG);
    }
}
