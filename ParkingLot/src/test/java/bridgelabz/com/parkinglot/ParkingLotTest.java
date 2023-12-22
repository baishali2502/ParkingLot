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
    /**
     * Test case: Notify When Lot is Full
     *
     * @desc Ensures that the parking lot owner is notified when the lot is full.
     * @params ParkingLot with a capacity and a mocked ParkingLotOwner instance.
     * @returns True if the owner is notified when the lot is full, false otherwise.
     */
    @Test
    public void testNotifyWhenLotIsFull() {
        // Arrange
        ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner); // Set capacity to 1 for testing
        Car car = mock(Car.class);

        // Act
        parkingLot.parkCar(car); // Park the first car
        boolean result = parkingLot.parkCar(mock(Car.class)); // Attempt to park the second car
        System.out.print(result); // returns false , i.e 2nd park cannot be parked
        // Assert
        assertFalse("Owner should be notified when the lot is full", result);
        verify(parkingLotOwner, times(1)).notifyLotFull(); // Verify that the owner is notified
    }
    /*
    * Test case: Notify Security When Lot is Full
    *
    * @desc Ensures that the security personnel is notified when the parking lot is full.
    * @params ParkingLot with a capacity and a mocked SecurityPersonnel instance.
    * @returns True if the security personnel is notified when the lot is full, false otherwise.
    */
   @Test
   public void testNotifySecurityWhenLotIsFull() {
       // Arrange
       SecurityPersonnel securityPersonnel = mock(SecurityPersonnel.class);
       ParkingLot parkingLot = new ParkingLot(1, securityPersonnel); // Set capacity to 1 for testing
       Car car = mock(Car.class);

       // Act
       parkingLot.parkCar(car); // Park the first car
       boolean result = parkingLot.parkCar(mock(Car.class)); // Attempt to park the second car
       // result should be false , indicating 2nd car cannot be parked as lot is full
       // Assert
       assertFalse("Security personnel should be notified when the lot is full", result);
       verify(securityPersonnel, times(1)).notifyLotFull(); // Verify that security personnel is notified
   }
}