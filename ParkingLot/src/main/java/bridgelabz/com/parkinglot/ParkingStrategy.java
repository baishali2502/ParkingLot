package bridgelabz.com.parkinglot;
/**
 * Interface for defining parking strategies.
 */
public interface ParkingStrategy {

    /**
     * Parks a car at a particular position.
     *
     * @param car The car to be parked.
     * @param position The position where the car should be parked.
     * @return True if the car is parked successfully, false otherwise.
     */
    boolean parkCarAtPosition(Car car, int position);
}

