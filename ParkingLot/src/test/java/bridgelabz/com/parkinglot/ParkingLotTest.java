package bridgelabz.com.parkinglot;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for the ParkingLot class.
 */
public class ParkingLotTest 
{
	private ParkingLotOwner parkingLotOwner;
    private SecurityPersonnel securityPersonnel;
    private ParkingAttendant parkingAttendant;

    @Before
    public void setUp() {
        parkingLotOwner = mock(ParkingLotOwner.class);
        securityPersonnel = mock(SecurityPersonnel.class);
        parkingAttendant = mock(ParkingAttendant.class);
    }

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
    	SecurityPersonnel securityPersonnel = mock(SecurityPersonnel.class);
        ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner, securityPersonnel); // Set capacity to 1 for testing
        Car car = mock(Car.class);
        
        parkingLot.parkCar(car); // park a car

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
    	SecurityPersonnel securityPersonnel = mock(SecurityPersonnel.class);
        ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
        ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner, securityPersonnel); // Set capacity to 1 for testing
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
       ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
       ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner, securityPersonnel); // Set capacity to 1 for testing
       Car car = mock(Car.class);

       // Act
       parkingLot.parkCar(car); // Park the first car
       boolean result = parkingLot.parkCar(mock(Car.class)); // Attempt to park the second car
       System.out.println(result);
       // result should be false , indicating 2nd car cannot be parked as lot is full
       // Assert
       assertFalse("Security personnel should be notified when the lot is full", result);
       verify(securityPersonnel, times(1)).notifyLotFull(); // Verify that security personnel is notified
   }
   /**
    * Test case: Notify Owner When Lot Has Space Again
    *            i.e notify whenever a car unparks
    *
    * @desc Ensures that the parking lot owner is notified when the lot has space again.
    * @params ParkingLot with a capacity, owner, and a previously parked car.
    * @returns True if the owner is notified when the lot has space again, false otherwise.
    */
   @Test
   public void testNotifyOwnerWhenLotHasSpaceAgain() {
       // Arrange
	   SecurityPersonnel securityPersonnel = mock(SecurityPersonnel.class);
       ParkingLotOwner parkingLotOwner = mock(ParkingLotOwner.class);
       ParkingLot parkingLot = new ParkingLot(1, parkingLotOwner, securityPersonnel); // Set capacity to 1 for testing
       Car car = mock(Car.class);
       
       parkingLot.parkCar(car);

       // Act
       parkingLot.unparkCar(car); // Unpark the car

       // Assert
       verify(parkingLotOwner, times(1)).notifyLotHasSpaceAgain(); // Verify that the owner is notified
   }
   /**
    * Test case: Parking Attendant Parks Cars with Position
    *
    * @desc Ensures that the parking attendant can park cars with a specified position.
    * @params ParkingLot with a capacity, owner, security personnel, and a mocked ParkingAttendant instance.
    * @returns True if the parking attendant can park cars with a specified position, false otherwise.
    */
   @Test
   public void testParkingAttendantWithPosition() {
       // Arrange
       ParkingLot parkingLot = new ParkingLot(10, parkingLotOwner, securityPersonnel, parkingAttendant);
       Car car = mock(Car.class);

       // Act
       when(parkingAttendant.parkCarAtPosition(car, 3)).thenReturn(true); // Mock parking result
       
       boolean result = parkingLot.parkCarWithAttendant(car, 3);

       // Assert
       assertTrue("Car should be parked successfully", result);
       verify(parkingAttendant, times(1)).parkCarAtPosition(car, 3); // Verify that parkingAttendant is called with the correct parameters
   }
}