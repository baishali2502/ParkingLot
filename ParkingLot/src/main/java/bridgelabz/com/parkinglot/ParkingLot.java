package bridgelabz.com.parkinglot;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a parking lot.
 */
public class ParkingLot {
	private int capacity;
	private List<Car> parkedCars;
	private ParkingLotOwner owner;
	private SecurityPersonnel securityPersonnel;

	/**
	 * @desc Constructs a parking lot with a specified capacity.
	 *
	 * @param capacity The maximum number of cars the parking lot can accommodate.
	 */
	public ParkingLot(int capacity) {
		this.capacity = capacity;
		this.parkedCars = new ArrayList<>();
	}

	/**
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
	 * @desc Parks a car in the parking lot.
	 *
	 * @param car The car to be parked.
	 * @return True if the car is parked successfully, false if the parking lot is
	 *         full.
	 */
	public boolean parkCar(Car car) {
		if (parkedCars.size() < capacity) {
			parkedCars.add(car);
			return true; // Car parked successfully
		} else {
			owner.notifyLotFull(); // Notify owner when the lot is full
			securityPersonnel.notifyLotFull(); // Notify security personnel when the lot is full
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
		return parkedCars.remove(car);
	}

	/**
	 * @desc Gets the number of cars currently parked in the parking lot.
	 *
	 * @return The number of parked cars.
	 */
	public int getParkedCarsCount() {
		return parkedCars.size();
	}
}
