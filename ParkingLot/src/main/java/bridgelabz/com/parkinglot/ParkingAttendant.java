package bridgelabz.com.parkinglot;

import java.util.*;

/**
 * Represents a parking attendant at a parking lot.
 */
public class ParkingAttendant implements ParkingStrategy {

	private String name="";
    private List<ParkingLot> parkingLots;
    private int currentLotIndex;
    private List<Car> parkedCars; // List of cars parked in the current lot

    public ParkingAttendant() {
        this.parkingLots = new ArrayList<>();
        this.currentLotIndex = 0;
        this.parkedCars = new ArrayList<>();
    }
    public ParkingAttendant(String name) {
        this.name = name;
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
    public boolean parkCarAtPosition(Car car, int position) 
    {
    	parkedCars = parkingLots.get(0).getParkedCars();
    	
        if (position < parkingLots.get(0).getCapacity())
        {
            parkedCars.add(position, car);
            return true; // Car parked successfully
        } else 
        {
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
    /**
     * @desc Finds the parking lot with the nearest free space.
     *
     * @return The parking lot with the nearest free space.
     */
    private ParkingLot findLotWithNearestFreeSpace() 
    {
    	int min=Integer.MAX_VALUE;
    	ParkingLot lot = null;
        for(ParkingLot p : parkingLots)
        {
        	if(min>p.nearestFreeSpace())
        	{
        		min = p.nearestFreeSpace();
        		lot = p;
        	}
        }
        return lot;
    }
    /**
     * @desc Parks a car for a handicap driver using a round-robin strategy among the managed parking lots.
     * The car is parked in the lot with the nearest available space.
     *
     * @param car The car to be parked.
     * @return True if the car is parked successfully, false otherwise.
     */
    
    public int parkCarForHandicapDriver(Car car)
    {
    	if (parkingLots.isEmpty()) {
            throw new IllegalStateException("No parking lots available");
        }

        // Use round-robin strategy to distribute cars among parking lots
        ParkingLot currentLot = findLotWithNearestFreeSpace();
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
    /*
     * Parks a large car in the parking lot, prioritizing the lot with the highest number of free spaces.
     *
     * @param car The large car to be parked.
     * @return True if the car is parked successfully, false if the parking lot is full.
     */
    public int parkCarForLargeCar(Car car) 
    {
        ParkingLot targetLot = findLotWithHighestFreeSpace();
        
        if (targetLot != null) {
            boolean parked = targetLot.parkCar(car);
            if (parked) {
                // Add the parked car to the list maintained by the attendant for the target lot
            	targetLot.setLargeCars(targetLot.getLargeCars()+1);
                List<Car> parkedCarsInTargetLot = targetLot.getParkedCars();
                parkedCarsInTargetLot.add(car);
            } else {
                // Handle the case when the target lot is full
                System.out.println("Parking Attendant: The target lot is full. Unable to park car " + car.getLicensePlate());
            }
        } else {
            // Handle the case when there are no parking lots available
            System.out.println("Parking Attendant: No parking lots available. Unable to park car " + car.getLicensePlate());
        }
        
        return targetLot.getLargeCars(); // Parking lot is full or no available lots
    }

    /**
     * Finds the parking lot with the highest number of free spaces.
     *
     * @return The parking lot with the highest number of free spaces, or null if no lots are available.
     */
    private ParkingLot findLotWithHighestFreeSpace() {
        ParkingLot targetLot = null;
        int maxFreeSpace = -1;

        for (ParkingLot lot : parkingLots) {
           if(lot.nearestFreeSpace()>maxFreeSpace)
           {
        	   maxFreeSpace = lot.nearestFreeSpace();
        	   targetLot = lot;
           }
        }

        return targetLot;
    }


}

