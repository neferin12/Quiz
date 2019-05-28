import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class gui {
    private JPanel quiz;
    private JProgressBar progressBar;
    private JLabel zahl;
    private JLabel frage;
    private JButton antwort0;
    private JButton antwort1;
    private JButton antwort2;
    private JButton antwort3;
    private JButton [] antworten = new JButton[]{antwort0, antwort1, antwort2, antwort3};
    private JButton weiter;
    private JLabel ergebnis;
    private JPanel finish;
    private GameController gc;
    public static JFrame frame;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        frame = new JFrame("Quiz");
        frame.setContentPane(new gui().quiz);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public gui() {
        try {
            gc = Importer.getGCFromCSV(new File("fragen.csv"));
            gc.nächsteFrage(false);
            neueFrage();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Fragen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }


        for (int i = 0; i < antworten.length; i++) {
            final int t = i;
            antworten[i].addActionListener(e -> {
                prüfen(antworten[t], t);
            });

        }


        weiter.addActionListener(e -> {
            neueFrage();
        });
    }

    private void prüfen(JButton antwort, int i) {

        for (JButton jButton : antworten) {
            jButton.setEnabled(false);
        }

        if (gc.gibAktuelleFrage().istAntwortRichtig(i)) {
            antwort.setBackground(Color.green);
            gc.nächsteFrage(true);
            weiter.setEnabled(true);
        } else {
            antwort.setBackground(Color.red);
            switch (gc.gibAktuelleFrage().getIndexRichtigeAntwort()) {
                case 0: antwort0.setBackground(Color.green);break;
                case 1: antwort1.setBackground(Color.green);break;
                case 2: antwort2.setBackground(Color.green);break;
                case 3: antwort3.setBackground(Color.green);break;
            }
            gc.nächsteFrage(false);
            weiter.setEnabled(true);
        }
    }

    private void neueFrage() {
        try {
            Frage frage = gc.gibAktuelleFrage();
            progressBar.setMaximum(gc.fragenAnzahl());
            progressBar.setValue(gc.getAktuelleFrageNummer() - 1);
            zahl.setText(gc.getAktuelleFrageNummer() - 1 + "/" + gc.fragenAnzahl());
            this.frage.setText(frage.getFrage());
            String[] antwortenStrings = frage.getAntworten();

            for (int i = 0; i < antwortenStrings.length; i++) {
                antworten[i].setText(antwortenStrings[i]);
                antworten[i].setBackground(Color.white);
                antworten[i].setEnabled(true);
            }


            weiter.setEnabled(false);
        } catch (IndexOutOfBoundsException e) {
            progressBar.setValue(gc.getAktuelleFrageNummer() - 1);
            zahl.setText(gc.getAktuelleFrageNummer() - 1 + "/" + gc.fragenAnzahl());
            finish.setVisible(true);
            ergebnis.setText(gc.getRichtigBeantwortet()+" von "+gc.fragenAnzahl()+" richtig beantwortet");
            weiter.setVisible(false);
        }
        }
    }

