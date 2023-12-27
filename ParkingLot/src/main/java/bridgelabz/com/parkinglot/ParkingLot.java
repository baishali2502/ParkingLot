package bridgelabz.com.parkinglot;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a parking lot.
 */
public class ParkingLot 
{
	private int capacity;
	private List<Car> parkedCars;
	private ParkingLotOwner owner;
	private SecurityPersonnel securityPersonnel;
	private ParkingAttendant parkingAttendant;
	private int currentLotIndex;
	
	/*
	 * @desc Constructs a parking lot with a specified capacity.
	 *
	 * @param capacity The maximum number of cars the parking lot can accommodate.
	 */
	public ParkingLot(int capacity) {
		this.capacity = capacity;
		this.parkedCars = new ArrayList<>();
	}

	/*
	 * @desc Constructs a parking lot with a specified capacity and owner.
	 *
	 * @param capacity The maximum number of cars the parking lot can accommodate.
	 * @param owner    The owner of the parking lot.
	 */
	public ParkingLot(int capacity, ParkingLotOwner owner) {
		this.capacity = capacity;
		this.parkedCars = new ArrayList<>();
		this.owner = owner;
	}

	/*
	 * @desc Constructs a parking lot with a specified capacity, owner, and security
	 * personnel.
	 *
	 * @param capacity The maximum number of cars the parking lot can accommodate.
	 * 
	 * @param owner The owner of the parking lot.
	 * 
	 * @param securityPersonnel The security personnel at the parking lot.
	 */
	public ParkingLot(int capacity, ParkingLotOwner owner, SecurityPersonnel securityPersonnel) {
		this.capacity = capacity;
		this.parkedCars = new ArrayList<>();
		this.owner = owner;
		this.securityPersonnel = securityPersonnel;
	}
	/**
     * @desc Constructs a parking lot with a specified capacity, owner, security personnel,
     * and parking attendant.
     *
     * @param capacity         The maximum number of cars the parking lot can accommodate.
     * @param owner            The owner of the parking lot.
     * @param securityPersonnel The security personnel at the parking lot.
     * @param parkingAttendant The parking attendant responsible for parking cars in this lot.
     */
    public ParkingLot(int capacity, ParkingLotOwner owner, SecurityPersonnel securityPersonnel,
                      ParkingAttendant parkingAttendant) {
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
        this.owner = owner;
        this.securityPersonnel = securityPersonnel;
        this.parkingAttendant = parkingAttendant;
       
    }
    /**
     * @desc Parks a car in the parking lot using the specified parking attendant.
     *
     * @param car      The car to be parked.
     * @param position The position where the car should be parked.
     * @return True if the car is parked successfully, false otherwise.
     */
    public boolean parkCarWithAttendant(Car car, int position) {
        boolean result = parkingAttendant.parkCarAtPosition(car, position);
        if (result) {
            owner.notifyLotHasSpaceAgain(); // Notify owner when the lot has space again
        } else {
            owner.notifyLotFull(); // Notify owner when the lot is full
        }
        return result;
    }
    
	/**
	 * @desc Parks a car in the parking lot.
	 *
	 * @param car The car to be parked.
	 * @return True if the car is parked successfully, false if the parking lot is
	 *         full.
	 */
    public boolean parkCar(Car car) {
        if (parkedCars.size() < capacity) {
            car.setParkedTime(Instant.now()); // Set the current timestamp
            parkedCars.add(car);
            return true; // Car parked successfully
        } else {
            owner.notifyLotFull(); // Notify owner when the lot is full
            securityPersonnel.notifyLotFull(); // Notify security personnel when the lot is full
            System.out.println("Lot full");
            return false; // Parking lot is full
        }
    }

	/**
	 * @desc Unparks a car from the parking lot.
	 *
	 * @param car The car to be unparked.
	 * @return True if the car is unparked successfully, false if the car is not
	 *         found.
	 */
    public boolean unparkCar(Car car) {
        boolean result = parkedCars.remove(car);
        if (result) {
            car.setUnparkedTime(Instant.now()); // Set the unparked time
            owner.notifyLotHasSpaceAgain(); // Notify owner when the lot has space again
        }
        return result;
    }
    
    /*
     * @desc Calculates the parking duration for a car.
     *
     * @param car The car for which to calculate the parking duration.
     * @return The parking duration in seconds, or -1 if the car is not found.
     */
    public long calculateParkingDuration(Car car) {
        if (car.getParkedTime() != null && car.getUnparkedTime() != null) {
            return Duration.between(car.getParkedTime(), car.getUnparkedTime()).getSeconds();
        } else {
            return -1; // Missing time information
        }
    }


	/*
	 * @desc Gets the number of cars currently parked in the parking lot.
	 *
	 * @return The number of parked cars.
	 */
	public int getParkedCarsCount() {
		return parkedCars.size();
	}
	
	public List<Car> getParkedCars()
	{
		return parkedCars;
	}
	/**
     * Finds a car in the parking lot based on its plate number.
     *
     * @param plateNumber The plate number of the car to be found.
     * @return The position of the found car if found, -1 otherwise.
     */
    public int findCarPositionByPlateNumber(String plateNumber) {
        for (int i = 0; i < parkedCars.size(); i++) {
            Car car = parkedCars.get(i);
            if (car.getLicensePlate().equals(plateNumber)) {
                return i + 1; // Car found, return position
            }
        }
        return -1; // Car not found
    }	
}
