import java.util.ArrayList;

public class GameController {
    private int aktuelleFrage = -1;
    private ArrayList<Frage> fragen;
    private int richtigBeantwortet = 0;

    public GameController(ArrayList<Frage> fragen) {
        this.fragen = fragen;
    }

    void nächsteFrage(boolean letzteRichtig) {
        aktuelleFrage++;
        if(letzteRichtig) richtigBeantwortet++;
    }

    int fragenAnzahl() {
        return fragen.size();
    }

    Frage gibAktuelleFrage() {
        return fragen.get(aktuelleFrage);
    }

    int getAktuelleFrageNummer() {
        return aktuelleFrage + 1;
    }

    public int getRichtigBeantwortet() {
        return richtigBeantwortet;
    }
}
