package bridgelabz.com.parkinglot;

import java.time.Instant;

/*
 * Represents a car.
 */
public class Car {

    private String licensePlate;
    private String make;
    private String model;
    private String color;
    private Instant parkedTime;
    private Instant unparkedTime; // New attribute for tracking the time when the car was unparked
    private String parkingAttendantName;

    /**
     * @desc Constructs a car with the specified details.
     *
     * @param licensePlate The license plate of the car.
     * @param make The make of the car (e.g., Toyota, Honda).
     * @param model The model of the car.
     * @param color The color of the car.
     */
    public Car(String licensePlate, String make, String model, String color) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.color = color;
    }
    /**
     * @desc Constructs a car with the specified details.
     *
     * @param licensePlate The license plate of the car.
     * @param make The make of the car (e.g., Toyota, Honda).
     * @param model The model of the car.
     * @param color The color of the car.
     */
    public Car(String licensePlate, String make, String model, String color,String parkingAttendantName) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.color = color;
        this.parkingAttendantName = parkingAttendantName;
    }

    /**
     * @desc Gets the license plate of the car.
     *
     * @return The license plate.
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @desc Gets the make of the car.
     *
     * @return The make.
     */
    public String getMake() {
        return make;
    }

    /**
     * @desc Gets the model of the car.
     *
     * @return The model.
     */
    public String getModel() {
        return model;
    }

    /**
     * @desc Gets the color of the car.
     *
     * @return The color.
     */
    public String getColor() {
        return color;
    }
    // @desc Getter and setter for parkedTime
    public Instant getParkedTime() {
        return parkedTime;
    }

    public void setParkedTime(Instant parkedTime) {
        this.parkedTime = parkedTime;
    }

    // @desc Getter and setter for unparkedTime
    public Instant getUnparkedTime() {
        return unparkedTime;
    }

    public void setUnparkedTime(Instant unparkedTime) {
        this.unparkedTime = unparkedTime;
    }

	public String getParkingAttendantName() 
	{        		
		return parkingAttendantName;
	}
}
