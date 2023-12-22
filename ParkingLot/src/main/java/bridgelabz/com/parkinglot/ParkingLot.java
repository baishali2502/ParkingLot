package bridgelabz.com.parkinglot;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a parking lot.
 */
public class ParkingLot {
    private int capacity;
    private List<Car> parkedCars;

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
     * @desc Parks a car in the parking lot.
     *
     * @param car The car to be parked.
     * @return True if the car is parked successfully, false if the parking lot is full.
     */
    public boolean parkCar(Car car) {
        if (parkedCars.size() < capacity) {
            parkedCars.add(car);
            return true; // Car parked successfully
        } else {
            return false; // Parking lot is full
        }
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
