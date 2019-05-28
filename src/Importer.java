import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Importer {
    static GameController getGCFromCSV(File csv) throws FileNotFoundException {

        ArrayList<Frage> fragen = new ArrayList<>();
        Scanner scanner = new Scanner(csv);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitted = line.split(";");
            String frage = splitted[0].trim();
            String[] antworten = new String[4];
            System.arraycopy(splitted, 1, antworten, 0, 4);
            int richtigeAntwort = Integer.parseInt(splitted[5]);

            fragen.add(new Frage(frage, antworten, richtigeAntwort));
        }
        scanner.close();

        return new GameController(fragen);
    }
}
