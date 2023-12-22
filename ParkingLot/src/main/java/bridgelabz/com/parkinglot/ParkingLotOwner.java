package bridgelabz.com.parkinglot;
/**
 * Represents the owner of a parking lot.
 */
public class ParkingLotOwner {

    private String ownerName;

    /**
     * @desc Constructs a parking lot owner with a specified name.
     *
     * @param ownerName The name of the parking lot owner.
     */
    public ParkingLotOwner(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @desc Gets the name of the parking lot owner.
     *
     * @return The owner's name.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @desc Notifies the owner that the parking lot is full.
     */
    public void notifyLotFull() {
        System.out.println("Owner " + ownerName + ": Parking lot is full. Put out the full sign!");
    }

    /*
    * @desc Notifies the owner that the parking lot has space again.
    */
   public void notifyLotHasSpaceAgain() {
       System.out.println("Owner " + ownerName + ": Parking lot has space again. Take down the full sign!");
   }
}

