package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    public static final String UNRECOGNIZED_PARKING_TICKET_MSG = "Unrecognized parking ticket";

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private String systemOut() {
        return outContent.toString();
    }

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

    @Test
    public void should_return_null_when_fetch_given_a_used_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        //When
        Car myCar = parkingLot.fetch(ticket);
        //Then
        assertNull(myCar);
    }

    @Test
    public void should_return_null_when_park_given_no_position_parking() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        IntStream.range(0, 10).forEach(i -> parkingLot.park(new Car()));
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNull(ticket);
    }

    @Test
    public void should_throw_error_when_park_given_unrecognized_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = new Ticket();
        //When
        //Then
        assertThrows(RuntimeException.class, () -> parkingLot.fetch(ticket), UNRECOGNIZED_PARKING_TICKET_MSG);
    }
}
