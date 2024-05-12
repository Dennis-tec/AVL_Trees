/** Contain details about a place population.
 */
public class PopulatedPlace extends LocatedPlace {
    /** Population of a place */
    private int population;

    /** Cconstructor of the populated class.
     * @param zip place zipcode
     * @param town town of place
     * @param state state of place 
     * @param latitude latitude location
     * @param longitude longitude location
     * @param population total population of a place
     */
    public PopulatedPlace(String zip, String town, String state, 
        double latitude, double longitude, int population) {
        super(zip, town, state, latitude, longitude);
        this.population = population;
    }

    /** Get the current total of a place.
     * @return population of a place
     */
    public int getPopulation() {
        return population;
    }
    
    /** String content of a place.
     * @return place as a string
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
        String ts = super.details() + " " + Integer.toString(population);
        return ts;
    }
}
