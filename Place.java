/** Contains and Store the Place content.
 */
public class Place implements Comparable<Place>  {
    private String zip;
    private String town;
    private String state;

    /** Constructor for the Place class.
     * @param zip zipcode of the place
     * @param town town of that place
     * @param state state of the place
     */
    public Place(String zip, String town, String state) {
        this.zip = zip;
        this.town = town;
        this.state = state;
    }

    /** Returns the content of a place as a string.
     * @return string content of the place
     */
    @Override
    public String toString() {
        return zip;
    }

    /** Getter for the zip code of the place.
     * @return get zip code of the place
     */
    public String getZip() {
        return zip;
    }

    /** Getter for the town of the place.
     * @return get twon of the place
     */
    public String getTown() {
        return town;
    }

    /** Getter for the state of the place.
     * @return get state of the place
     */
    public String getState() {
        return state;
    }
    
    /** Compare the current place zip code to a user's zip code.
     * @return 0 if equals, 1 if current is greater else -1
     */
    @Override
    public int compareTo(Place other) {
        return this.getZip().compareTo(other.getZip());
    }

    /**
     * Returns the town and state of a place.
     * @return town and state combine
     */
    public String details() {
        String ts = town + ", " + state;
        return ts;
    }
}
