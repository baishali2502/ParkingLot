package bridgelabz.com.parkinglot;

/**
 * Represents security personnel at a parking lot.
 */
public class SecurityPersonnel {

    private String personnelName;
    private int personnelId;

    /**
     * @desc Constructs a security personnel with a specified name and ID.
     *
     * @param personnelName The name of the security personnel.
     * @param personnelId The ID of the security personnel.
     */
    public SecurityPersonnel(String personnelName, int personnelId) {
        this.personnelName = personnelName;
        this.personnelId = personnelId;
    }

    /**
     * @desc Gets the name of the security personnel.
     *
     * @return The name of the security personnel.
     */
    public String getPersonnelName() {
        return personnelName;
    }

    /**
     * @desc Gets the ID of the security personnel.
     *
     * @return The ID of the security personnel.
     */
    public int getPersonnelId() {
        return personnelId;
    }

    /**
     * @desc Notifies the security personnel that the parking lot is full.
     */
    public void notifyLotFull() {
        System.out.println("Security Personnel " + personnelName + " (ID: " + personnelId +
                           "): Parking lot is full. Redirecting security staff!");
    }
}

