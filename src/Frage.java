public class Frage {
    private String frage;
    private String[] antworten;
    private int indexRichtigeAntwort;

    public Frage(String frage, String[] antworten, int indexRichtigeAntwort) {
        this.frage = frage;
        this.antworten = antworten;
        this.indexRichtigeAntwort = indexRichtigeAntwort;
    }

    boolean istAntwortRichtig(int i) {
        return i == indexRichtigeAntwort;
    }

    public int getIndexRichtigeAntwort() {
        return indexRichtigeAntwort;
    }

    public String getFrage() {
        return frage;
    }

    public String[] getAntworten() {
        return antworten;
    }
}
