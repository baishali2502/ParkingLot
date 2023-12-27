package bridgelabz.com.parkinglot;

import java.util.*;

/**
 * Represents a parking attendant at a parking lot.
 */
public class ParkingAttendant implements ParkingStrategy {

     private List<ParkingLot> parkingLots;
    private int currentLotIndex;
    private List<Car> parkedCars; // List of cars parked in the current lot

    public ParkingAttendant() {
        this.parkingLots = new ArrayList<>();
        this.currentLotIndex = 0;
        this.parkedCars = new ArrayList<>();
    }

    /**
     * Sets the parking lots managed by the attendant.
     *
     * @param parkingLots The list of parking lots.
     */
    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.currentLotIndex = 0;
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
     * Parks a car using a round-robin strategy among the managed parking lots.
     *
     * @param car The car to be parked.
     * @return True if the car is parked successfully, false otherwise.
     */
    public int parkCarWithRoundRobin(Car car) {
        if (parkingLots.isEmpty()) {
            throw new IllegalStateException("No parking lots available");
        }

        // Use round-robin strategy to distribute cars among parking lots
        ParkingLot currentLot = parkingLots.get(currentLotIndex);
        boolean parked = currentLot.parkCar(car);

        if (parked) {
            // Add the parked car to the list maintained by the attendant for the current lot
            List<Car> parkedCarsInCurrentLot = currentLot.getParkedCars();
            parkedCars.addAll(parkedCarsInCurrentLot);
        } else {
            // Handle the case when the current lot is full
            System.out.println("Parking Attendant: All lots are full. Unable to park car " + car.getLicensePlate());
        }

        // Move to the next lot
        currentLotIndex = (currentLotIndex + 1) % parkingLots.size();
        return currentLot.getParkedCarsCount();
    }

    /**
     * Gets the list of cars parked in the current lot by the attendant.
     *
     * @return The list of parked cars in the current lot.
     */
    public List<Car> getCurrentLotParkedCars() {
        return new ArrayList<>(parkedCars); // Return a copy to avoid external modifications
    }
    public List<ParkingLot> getParkingLots()
    {
    	return parkingLots;
    }
}

