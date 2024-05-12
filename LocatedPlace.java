/** Contains details about place location.
 */
public class LocatedPlace extends Place {
    private double latitude;
    private double longitude;

    /** Constructor of the located place class.
     * @param zip place zipcode
     * @param town town of the place
     * @param state state of the place
     * @param latitude location latitude of the place
     * @param longitude location longitude of the place
     */
    public LocatedPlace(String zip, String town, 
        String state, double latitude, double longitude) {
        super(zip, town, state);

        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    /** Getter for the longitude of the place.
     * @return get longitude of the place
     */
    public double longitude() {
        return longitude;
    }

    /** Getter for the latitude of the place.
     * @return get latitude of the place
     */
    public double latitude() {
        return latitude;
    }
    
    /** Returns the content of a place as a string.
     * @return string content of the place
     */
    @Override
    public String toString() {
        String ts = super.toString();
        return ts;
    }

    
    /** Returns the details of a place as a string.
     * @return string details of the place
     */
    @Override
    public String details() {
        String ts = super.details() + " " + Double.toString(latitude) 
            + " " + Double.toString(longitude);
        return ts;
    }
}
