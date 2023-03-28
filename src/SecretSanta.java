import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecretSanta {
    public static void main(String [] args) throws IOException {
        ArrayList<String> linNames = new ArrayList<>();
        ArrayList<String> kocherNames = new ArrayList<>();

        // Add names to the Lin secret santa group
        linNames.add("Adam");
        linNames.add("Dana");
        linNames.add("Kathryn");
        linNames.add("Andy");
        linNames.add("Caleb");
        linNames.add("Tiana");

        // Add names to the Kocher secret santa group
        kocherNames.add("Adam");
        kocherNames.add("Dana");
        kocherNames.add("Caleb");
        kocherNames.add("Tiana");
        kocherNames.add("James");
        kocherNames.add("Madeline");

        // Shuffle the names using the static Collections class
        Collections.shuffle(linNames);
        Collections.shuffle(kocherNames);

        // These arraylists keep a pair of people, using a string array
        ArrayList<String[]> linCircle = new ArrayList<>();
        ArrayList<String[]> kocherCircle = new ArrayList<>();

        // Each person is assigned the person at the next index, then wraps around to the beginning via the modulo
        for(int i = 0 ; i < linNames.size(); i++) {
           linCircle.add(new String[] { linNames.get(i), linNames.get((i + 1) % (linNames.size())) });
           kocherCircle.add(new String[] { kocherNames.get(i), kocherNames.get((i + 1) % (kocherNames.size())) });
        }

        // re-shuffle lists, if there are duplicates
        while(hasDuplicates(linCircle, kocherCircle)) {
            // Shuffle the names using the static Collections class
            Collections.shuffle(linNames);
            Collections.shuffle(kocherNames);

            linCircle.clear();
            kocherCircle.clear();

            for(int i = 0 ; i < linNames.size(); i++) {
                linCircle.add(new String[] { linNames.get(i), linNames.get((i + 1) % (linNames.size())) });
                kocherCircle.add(new String[] { kocherNames.get(i), kocherNames.get((i + 1) % (kocherNames.size())) });
            }
        }

        // Write each pair to a file, where the first name is the name of the file, and the content of the file is the second name
        FileWriter myWriter;
        for(int i = 0; i < linCircle.size(); i++) {
            myWriter = new FileWriter("C:\\Users\\adamn\\Desktop\\SecretSanta(L)\\" + linCircle.get(i)[0] + ".txt");
            myWriter.write("You have " + linCircle.get(i)[1] + " for Secret Santa :)");
            myWriter.close();
        }

        // Same thing for the second group
        for(int i = 0; i < kocherCircle.size(); i++) {
            myWriter = new FileWriter("C:\\Users\\adamn\\Desktop\\SecretSanta(K)\\" + kocherCircle.get(i)[0] + ".txt");
            myWriter.write("You have " + kocherCircle.get(i)[1] + " for Secret Santa :)");
            myWriter.close();
        }

//         For output and debugging
        for(int i = 0; i < kocherCircle.size(); i++) {
            System.out.println(kocherCircle.get(i)[0] + " has " + kocherCircle.get(i)[1]);
        }
        System.out.println("--------------------------");
        for(int i = 0; i < linCircle.size(); i++) {
            System.out.println(linCircle.get(i)[0] + " has " + linCircle.get(i)[1]);
        }
        System.out.println(hasDuplicates(linCircle, kocherCircle));
    }

    public static boolean hasDuplicates(List<String[]> list1, List<String[]> list2) {
        for(int i = 0; i < list1.size(); i++) {
            for(int j = 0; j < list2.size(); j++) {
                if(list1.get(i)[0].equals(list2.get(j)[0]) && list1.get(i)[1].equals(list2.get(j)[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
