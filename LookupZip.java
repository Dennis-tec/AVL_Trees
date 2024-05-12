import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** This files looks of an inputed zip code.
 */
public class LookupZip {

    /** This is a static method that reads a line.
     * @param line content of a line to be read
     * @return a new instance of place is returned
     */
    public static Place parseLine(String line) {
        String[] parts = line.split(",", 10);
        String zip = parts[0];
        String town = parts[1];
        String state = parts[2];
        String population = parts[3];
        
        if (population.isEmpty()) {
            return new Place(zip, town, state);
        } else {
            return new PopulatedPlace(zip, town, state,
                0, 0, Integer.parseInt(population));
        }
    }

    /** Updates places contents by adding file 2 items.
     * @param line a new line to read
     * @param places an array of places to update
     */
    public static void updateLocation(String line, 
        AVLTree<Place> places) {
        String[] parts = line.split(",");
        String zip = parts[0].replace("\"", "");
        String town = parts[2];
        String state = parts[3];
        String latitude = parts[5];
        String longitude = parts[6];
        Place foundPlace = places.get(new Place(zip, "", ""));

        if (foundPlace != null) {
            zip = foundPlace.getZip();
            town = foundPlace.getTown();
            state = foundPlace.getState();
        }

        if (foundPlace instanceof PopulatedPlace) {
            PopulatedPlace updatedPlace = 
                (PopulatedPlace) foundPlace;
            places.insert(new PopulatedPlace(
                zip, town, state, 
                Double.parseDouble(latitude), 
                Double.parseDouble(longitude), 
                updatedPlace.getPopulation()));
        } else if (!latitude.isEmpty() && 
            !longitude.isEmpty()) {
            places.insert(new LocatedPlace(
                zip, town, state, 
                Double.parseDouble(latitude), 
                Double.parseDouble(longitude)));
        } else {
            places.insert(new Place(zip, town, state));
        } 
    }

    /** Read the content of the two files.
     * @param filename name of the first file
     * @param filename2 name of the second file
     * @return  an array of places 
     * @throws FileNotFoundException if file doesn't exist
     */
    public static AVLTree<Place> readZipCodes(String filename, 
        String filename2) throws FileNotFoundException {
        AVLTree<Place> places = new AVLTree<>();
        File file = new File(filename);
        Scanner input = new Scanner(file);
        input.nextLine();
        
        File file2 = new File(filename2);
        Scanner input2 = new Scanner(file2);
        input2.nextLine();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            Place place = parseLine(line);
            places.insert(place);
        }
        input.close();

        while (input2.hasNextLine()) {
            String line = input2.nextLine();
            updateLocation(line, places);
        }
        input2.close();

        return places;
    }

    /** Looks up a user inputed zipcode.
     * @param places an array containing places
     * @param zip an inputed zip code to search
     * @return the Place that matches the zipcode if found
     */
    public static Place lookupZip(AVLTree<Place> places, String zip) {
        Place searchPlace = new Place(zip, "", "");
        return places.get(searchPlace);
    }
}