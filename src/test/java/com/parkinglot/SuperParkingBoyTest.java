package com.parkinglot;

import com.parkinglot.parkingstrategy.SmartParkingStrategy;
import com.parkinglot.parkingstrategy.SuperParkingStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperParkingBoyTest {
    @Test
    public void should_park_first_parkingLot_when_park_given_two_parkingLot() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(new SuperParkingStrategy());
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertEquals(1, ticket.getParkingLotId());
    }

    @Test
    public void should_park_second_parkingLot_when_park_given_two_parkingLot() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(1, 3);
        ParkingLot parkingLot2 = new ParkingLot(2, 4);
        ParkingBoy parkingBoy = new ParkingBoy(new SuperParkingStrategy());
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertEquals(2, ticket.getParkingLotId());
    }


    @Test
    public void should_return_right_car_when_fetch_given_two_parkingLot() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(2, 1);
        ParkingBoy parkingBoy = new ParkingBoy(new SuperParkingStrategy());
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        Car previousCar = new Car();
        Car laterCar = new Car();
        Ticket ticket1 = parkingBoy.park(previousCar);
        Ticket ticket2 = parkingBoy.park(laterCar);
        //When
        Car car1 = parkingBoy.fetch(ticket1);
        Car car2 = parkingBoy.fetch(ticket2);
        //Then
        assertEquals(previousCar, car1);
        assertEquals(laterCar, car2);
    }

    @Test
    public void should_throw_exception_when_fetch_given_wrong_ticket() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(2, 1);
        ParkingBoy parkingBoy = new ParkingBoy(new SuperParkingStrategy());
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        parkingBoy.park(new Car());
        Ticket wrongTicket = new Ticket();
        //When
        //Then
        assertThrows(TicketException.class, () -> parkingBoy.fetch(wrongTicket), TicketException.WRONG_TICKET_MSG);
    }

    @Test
    public void should_throw_exception_when_fetch_given_reused_ticket() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(2, 1);
        ParkingBoy parkingBoy = new ParkingBoy(new SuperParkingStrategy());
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        Ticket ticket = parkingBoy.park(new Car());
        parkingBoy.fetch(ticket);
        //When
        //Then
        assertThrows(TicketException.class, () -> parkingBoy.fetch(ticket), TicketException.REUSED_TICKET_MSG);
    }

    @Test
    public void should_throw_exception_when_park_given_full_parkingLot() {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot(2, 1);
        ParkingBoy parkingBoy = new ParkingBoy(new SuperParkingStrategy());
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        //When
        //Then
        assertThrows(ParkingLotException.class, () -> parkingBoy.park(new Car()), ParkingLotException.NOT_ENOUGH_POSITION_MSG);
    }
}
