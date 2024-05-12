import java.util.Scanner;
import java.io.FileNotFoundException;

/** This is the executable file for this program.
 */
public class Program {

    /** This is the main entry to the program.
     * @param args a list of items from the command line
     * @throws FileNotFoundException throw file not found exception
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            System.out.println("Require two or more commandline arguments");
        } else {
            boolean dFlag = false;
            String[] files = new String[2];
            int i = 0;
            for (String string : args) {
                if ("-d".equals(string)) {
                    dFlag = true;
                } else {
                    files[i++] = string;
                }
            }
            String filename = files[0];
            String filename2 = files[1];

            AVLTree<Place> places = 
                LookupZip.readZipCodes(filename, filename2);
            
            Scanner input = new Scanner(System.in);
            while (true) {
                if (dFlag) {
                    System.out.println(places.height());
                    System.out.println(places.toString());
                }
                System.out.print("zipcode: ");
                String zipcode = input.nextLine();

                if ("00000".equals(zipcode)) {
                    System.out.println("Good Bye!");
                    break;
                }
                Place foundPlace = LookupZip.lookupZip(places, zipcode);

                if (foundPlace != null) {
                    System.out.println(foundPlace.toString());
                } else {
                    System.out.println("No such zipcode");
                }
                System.out.println();
            }
            input.close();
        }
    }
}
