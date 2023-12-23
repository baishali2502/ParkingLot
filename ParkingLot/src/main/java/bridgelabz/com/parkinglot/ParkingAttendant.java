package bridgelabz.com.parkinglot;

import java.util.List;

/**
 * Represents a parking attendant at a parking lot.
 */
public class ParkingAttendant implements ParkingStrategy {

    private List<Car> parkedCars; // List of cars parked by the attendant

    /**
     * Constructs a parking attendant with the list of parked cars provided by the parking lot.
     *
     * @param parkedCars The list of cars parked in the associated parking lot.
     */
    public ParkingAttendant(List<Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    /**
     * Parks a car at a particular position.
     *
     * @param car      The car to be parked.
     * @param position The position where the car should be parked.
     * @return True if the car is parked successfully, false otherwise.
     */
    @Override
    public boolean parkCarAtPosition(Car car, int position) {
        // Assuming position starts from 1 (not 0)
        if (position > 0 && position <= parkedCars.size() + 1) {
            parkedCars.add(position - 1, car);
            return true; // Car parked successfully
        } else {
            return false; // Invalid position
        }
    }

    /**
     * Retrieves the list of cars parked by the attendant.
     *
     * @return The list of cars parked by the attendant.
     */
    public List<Car> getParkedCars() {
        return parkedCars;
    }
}

