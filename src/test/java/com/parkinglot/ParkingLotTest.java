package com.parkinglot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void should_return_ticket_given_a_car_when_park() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_the_car_when_fetch_given_a_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //When
        Car myCar = parkingLot.fetch(ticket);
        //Then
        assertEquals(car, myCar);
    }

    @Test
    public void should_return_null_when_fetch_given_a_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        Ticket ticket = new Ticket();
        //When
        Car otherCar = parkingLot.fetch(ticket);
        //Then
        assertNull(otherCar);
    }
    
    @Test
    public void should_return_right_car_when_fetch_given_tickets() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);
        //When
        Car previousCar = parkingLot.fetch(ticket1);
        Car laterCar = parkingLot.fetch(ticket2);
        //Then
        assertEquals(car1, previousCar);
        assertEquals(car2, laterCar);
    }
}
