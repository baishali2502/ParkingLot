package bridgelabz.com.parkinglot;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

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
        parkingAttendant = new ParkingAttendant();
      
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
	   ParkingAttendant parkingAttendant1 = new ParkingAttendant();
       ParkingLot parkingLot = new ParkingLot(5, parkingLotOwner, securityPersonnel, parkingAttendant1);
       Car car = new Car("ABC123", "Toyota", "Camry", "Blue");
       parkingAttendant1.setParkingLots(Arrays.asList(parkingLot));
      
       boolean result = parkingAttendant1.parkCarAtPosition(car,0);

       // Assert
       assertTrue("Car should be parked successfully", result);
       
   }
   /**
    * Test case: Driver Finds Car by Plate Number
    *
    * @desc Ensures that a driver can find their car in the parking lot based on the plate number.
    * @params ParkingLot with a capacity, owner, security personnel, and a parked car with a known plate number.
    * @returns The position of the found car if found, -1 otherwise.
    */
   @Test
   public void testDriverFindsCarByPlateNumber() {
       // Arrange
       ParkingLot parkingLot = new ParkingLot(10, parkingLotOwner, securityPersonnel, parkingAttendant);

       // Updated Car constructor with additional parameters
       Car car = new Car("ABC123", "Toyota", "Camry", "Blue");
       parkingLot.parkCar(car);

       // Act
       int result = parkingLot.findCarPositionByPlateNumber("ABC123");

       // Assert
       assertEquals("Found car position should match", 1, result);
   }
   /**
    * Test case: Parking Lot Records Timestamp, Unparks Car, and Calculates Duration
    *
    * @desc Ensures that the parking lot records the timestamp when a car is parked,
    *       unparks the car, and calculates the parking duration.
    * @params ParkingLot with a capacity, owner, security personnel, and a parked car.
    */
   @Test
   public void testCalculatesDuration() throws InterruptedException {
       // Arrange
       ParkingLot parkingLot = new ParkingLot(10, parkingLotOwner, securityPersonnel, parkingAttendant);
       Car car = new Car("ABC234", "Toyota", "Camry", "Blue");

       // Act
       boolean isParked = parkingLot.parkCar(car);
       Thread.sleep(2000); // Simulate 2 seconds parking duration
       boolean isUnparked = parkingLot.unparkCar(car);
       long parkingDuration = parkingLot.calculateParkingDuration(car);
       // Assert
       assertTrue("Car should be parked successfully", isParked);
       assertNotNull("Car's parked time should not be null", car.getParkedTime());
       assertTrue("Parked time should be within a reasonable duration",
               Duration.between(car.getParkedTime(), Instant.now()).getSeconds() < 5);

       assertTrue("Car should be unparked successfully", isUnparked);
       assertNotNull("Car's unparked time should not be null", car.getUnparkedTime());
       assertTrue("Unparked time should be within a reasonable duration",
               Duration.between(car.getUnparkedTime(), Instant.now()).getSeconds() < 5);

       // Calculate and check the parking duration
       assertTrue("Parking duration should be approximately 2 seconds",
               parkingDuration > 1 && parkingDuration < 3);
   }
   /**
    * Test case: Parking Attendant evenly parks cars accross all parking lots
    *
    * @desc Ensures that the parking lot parks cars using the attendant with a round-robin strategy.
    * @params ParkingLot with a capacity, owner, security personnel, and parking attendant.
    */
   @Test
   public void testEvenDistribution() {
	// Create three parking lots and a parking attendant
	    ParkingLot lotA = new ParkingLot(5, parkingLotOwner, securityPersonnel, parkingAttendant);
	    ParkingLot lotB = new ParkingLot(5, parkingLotOwner, securityPersonnel, parkingAttendant);
	    ParkingLot lotC = new ParkingLot(5, parkingLotOwner, securityPersonnel, parkingAttendant);
        
	    
	    // Set up the parking lots in the attendant
	    parkingAttendant.setParkingLots(Arrays.asList(lotA, lotB, lotC));

	    // Create three cars
	    Car car1 = new Car("ABC123", "Toyota", "Camry", "Blue");
	    Car car2 = new Car("XYZ456", "Honda", "Civic", "Red");
	    Car car3 = new Car("DEF789", "Ford", "Focus", "Green");

	    // Park the cars through the ParkingAttendant
	    int lotAsize = parkingAttendant.parkCarWithRoundRobin(car1);
	    int lotBsize = parkingAttendant.parkCarWithRoundRobin(car2);
	    int lotCsize = parkingAttendant.parkCarWithRoundRobin(car3);
        
	    // Assert
	    assertEquals("Lot A should have 1 parked car", 1, lotAsize);
	    assertEquals("Lot B should have 1 parked car", 1, lotBsize);
	    assertEquals("Lot C should have 1 parked car", 1, lotCsize);

	    // Verify interactions with mocks
	    verify(parkingLotOwner, never()).notifyLotFull(); // No notifications expected in this case
	    verify(securityPersonnel, never()).notifyLotFull(); // No notifications expected in this case
   }
   
   /**
    * Test case: Parking Attendant parks three cars for handicap drivers with nearest available space
    *
    * @desc Ensures that the parking attendant parks three cars for handicap drivers using a round-robin strategy
    * among the managed parking lots, placing each car in the lot with the nearest available space.
    * @params ParkingLot with a capacity, owner, security personnel, and parking attendant.
    * @returns True if all three cars are parked successfully, false otherwise.
    */
   @Test
   public void testParkCarForHandicapDriver() {
       ParkingAttendant handicapParkingAttendant = new ParkingAttendant();
	// Create three parking lots and a parking attendant
	    ParkingLot lotA = new ParkingLot(5, parkingLotOwner, securityPersonnel, handicapParkingAttendant);
	    ParkingLot lotB = new ParkingLot(5, parkingLotOwner, securityPersonnel, handicapParkingAttendant);
	    ParkingLot lotC = new ParkingLot(5, parkingLotOwner, securityPersonnel, handicapParkingAttendant);
       
	    
	    // Set up the parking lots in the attendant
	    handicapParkingAttendant.setParkingLots(Arrays.asList(lotA, lotB, lotC));

	    // Create three cars
	    Car car1 = new Car("ABC123", "Toyota", "Camry", "Blue");
	    Car car2 = new Car("XYZ456", "Honda", "Civic", "Red");
	    Car car3 = new Car("DEF789", "Ford", "Focus", "Green");

	    // Park the cars through the ParkingAttendant
	    int lotAsize = handicapParkingAttendant.parkCarWithRoundRobin(car1);
	    int lotBsize = handicapParkingAttendant.parkCarWithRoundRobin(car2);
	    int lotCsize = handicapParkingAttendant.parkCarWithRoundRobin(car3);
       
	    // Assert
	    assertEquals("Lot A should have 1 parked car", 1, lotAsize);
	    assertEquals("Lot B should have 1 parked car", 1, lotBsize);
	    assertEquals("Lot C should have 1 parked car", 1, lotCsize);

	    // Verify interactions with mocks
	    verify(parkingLotOwner, never()).notifyLotFull(); // No notifications expected in this case
	    verify(securityPersonnel, never()).notifyLotFull(); // No notifications expected in this case
   }
   /**
    * Test case: Parking Attendant directs large cars to lots with the highest number of free spaces.
    *
    * @desc Ensures that the parking attendant prioritizes lots with the highest free spaces for parking large cars.
    * @params ParkingLot with various capacities, owner, security personnel, and parking attendant.
    * @returns Verify that large cars are parked in lots with the highest free spaces.
    */
   @Test
   public void testParkingLargeCars() 
   {
	   ParkingAttendant parkingAttendantForLargeCars = new ParkingAttendant();
       // Create three parking lots and a parking attendant
       ParkingLot lotA = new ParkingLot(5, parkingLotOwner, securityPersonnel, parkingAttendantForLargeCars);
       ParkingLot lotB = new ParkingLot(4, parkingLotOwner, securityPersonnel, parkingAttendantForLargeCars);
       ParkingLot lotC = new ParkingLot(10, parkingLotOwner, securityPersonnel, parkingAttendantForLargeCars);

       // Set up the parking lots in the attendant
       parkingAttendantForLargeCars.setParkingLots(Arrays.asList(lotA, lotB, lotC));

       // Create three large cars
       Car largeCar1 = new Car("ABC123", "Toyota", "Camry", "Blue");
       Car largeCar2 = new Car("XYZ456", "Honda", "Civic", "Red");
       Car largeCar3 = new Car("DEF789", "Ford", "Focus", "Green");       

       // Park the large cars through the ParkingAttendant
       parkingAttendantForLargeCars.parkCarForLargeCar(largeCar1);
       parkingAttendantForLargeCars.parkCarForLargeCar(largeCar2);
       parkingAttendantForLargeCars.parkCarForLargeCar(largeCar3);

       // Assert
       assertEquals("Lot A should have 0 parked large car", 0, lotA.getLargeCars());
       assertEquals("Lot B should have 0 parked large cars", 0, lotB.getLargeCars());
       assertEquals("Lot C should have 3 parked large cars", 3, lotC.getLargeCars());

       // Verify interactions with mocks
       verify(parkingLotOwner, never()).notifyLotFull(); // No notifications expected in this case
       verify(securityPersonnel, never()).notifyLotFull(); // No notifications expected in this case
   }
   /**
    * Test case: Police Department finds the location of all parked white cars.
    *
    * @desc Ensures that the Police Department can retrieve the positions of all parked white cars.
    * @params ParkingLot with various cars parked, including white cars.
    * @returns Verify the positions of parked white cars are correctly retrieved.
    */
   @Test
   public void testFindLocationsOfParkedWhiteCars() {
       // Create a parking lot
       ParkingLot parkingLot = new ParkingLot(10, parkingLotOwner, securityPersonnel, null);

       // Create three cars, one of them is white
       Car car1 = new Car("ABC123", "Toyota", "Camry", "Blue");
       Car car2 = new Car("XYZ456", "Honda", "Civic", "White");
       Car car3 = new Car("DEF789", "Ford", "Focus", "Red");

       // Park the cars
       parkingLot.parkCar(car1);
       parkingLot.parkCar(car2);
       parkingLot.parkCar(car3);

       // Find the locations of parked white cars
       List<Integer> whiteCarPositions = parkingLot.findLocationsOfParkedWhiteCars();

       // Assert
       assertEquals("There should be 1 parked white car", 1, whiteCarPositions.size());
       assertEquals("The white car should be at position 2", 2, whiteCarPositions.get(0).intValue());
   }
   
   /**
    * Test case: Police Department finds the location, plate number, and parking attendant name of all parked blue Toyota cars.
    *
    * @desc Ensures that the Police Department can retrieve the details of all parked blue Toyota cars.
    * @params ParkingLot with various cars parked, including blue Toyota cars.
    * @returns Verify the details of parked blue Toyota cars are correctly retrieved.
    */
   @Test
   public void testFindDetailsOfParkedBlueToyotaCars() {
       // Create a parking lot
	   ParkingAttendant parkingAttendant = new ParkingAttendant("John");
       ParkingLot parkingLot = new ParkingLot(10, parkingLotOwner, securityPersonnel, parkingAttendant);

       // Create three cars, two of them are blue Toyota cars
       Car car1 = new Car("ABC123", "Toyota", "Camry", "Blue","John");
       Car car2 = new Car("XYZ456", "Honda", "Civic", "White","John");
       Car car3 = new Car("DEF789", "Toyota", "Corolla", "Blue","John");

       // Park the cars
       parkingLot.parkCar(car1);
       parkingLot.parkCar(car2);
       parkingLot.parkCar(car3);

       // Find the details of parked blue Toyota cars
       List<String> detailsList = parkingLot.findDetailsOfParkedBlueToyotaCars();

       // Assert
       assertEquals("There should be 2 parked blue Toyota cars", 2, detailsList.size());
       assertEquals("The details of the first blue Toyota car should be correct",
               "Location: 1, Plate Number: ABC123, Attendant: John", detailsList.get(0));
       assertEquals("The details of the second blue Toyota car should be correct",
               "Location: 3, Plate Number: DEF789, Attendant: John", detailsList.get(1));
   }
   
   /**
    * Test case: Police Department finds the details of all parked BMW cars.
    *
    * @desc Ensures that the Police Department can retrieve the details of all parked BMW cars.
    * @params ParkingLot with various cars parked, including BMW cars.
    * @returns Verify the details of parked BMW cars are correctly retrieved.
    */
   @Test
   public void testFindDetailsOfParkedBMW() {
       // Create a parking lot
	   ParkingAttendant parkingAttendant = new ParkingAttendant("John");
       ParkingLot parkingLot = new ParkingLot(10, parkingLotOwner, securityPersonnel, parkingAttendant);

       // Create three cars, two of them are BMW cars
       Car car1 = new Car("ABC123", "Toyota", "Camry", "Blue","John");
       Car car2 = new Car("XYZ456", "BMW", "X5", "White","John");
       Car car3 = new Car("DEF789", "BMW", "M3", "Black","John");

       // Park the cars
       parkingLot.parkCar(car1);
       parkingLot.parkCar(car2);
       parkingLot.parkCar(car3);

       // Find the details of parked BMW cars
       List<String> detailsList = parkingLot.findDetailsOfParkedBMW();

       // Assert
       assertEquals("There should be 2 parked BMW cars", 2, detailsList.size());
       assertEquals("The details of the first BMW car should be correct",
               "Location: 2, Plate Number: XYZ456, Attendant: John", detailsList.get(0));
       assertEquals("The details of the second BMW car should be correct",
               "Location: 3, Plate Number: DEF789, Attendant: John", detailsList.get(1));
   }
  
}