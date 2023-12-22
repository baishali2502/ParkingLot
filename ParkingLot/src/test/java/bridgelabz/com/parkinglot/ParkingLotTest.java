package bridgelabz.com.parkinglot;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for the ParkingLot class.
 */
public class ParkingLotTest {

    /**
     * Test case: Park a Car
     *
     * @desc Ensures that a driver can successfully park their car in the parking lot.
     * @params ParkingLot with a capacity and a mocked Car instance.
     * @returns True if the car is parked successfully, false if the parking lot is full.
     */
    @Test
    public void testParkCar() {
        // Arrange
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = mock(Car.class);

        // Act
        boolean result = parkingLot.parkCar(car);

        // Assert
        assertTrue("Car should be parked successfully", result);
        assertEquals("Parking lot should have one parked car", 1, parkingLot.getParkedCarsCount());
    }
    /**
     * Test case: Unpark a Car
     *
     * @desc Ensures that a driver can successfully unpark their car from the parking lot.
     * @params ParkingLot with a parked car and a mocked Car instance.
     * @returns True if the car is unparked successfully, false if the car is not found.
     */
    @Test
    public void testUnparkCar() {
        // Arrange
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = mock(Car.class);
        parkingLot.parkCar(car);

        // Act
        boolean result = parkingLot.unparkCar(car);

        // Assert
        assertTrue("Car should be unparked successfully", result);
        assertEquals("Parking lot should have no parked cars", 0, parkingLot.getParkedCarsCount());
    }
}