package Classi;

public class StatisticheGiocatore {
    GiocatoreSingolo giocatore;
    int puntiEffettuati;


    // ********************* Costruttore
    public StatisticheGiocatore(GiocatoreSingolo giocatore, int puntiEffettuati) {
        this.giocatore = giocatore;
        this.puntiEffettuati = puntiEffettuati;
    }


    // ********************* Getter e Setter
    public int getPuntiEffettuati() {
        return puntiEffettuati;
    }

    public GiocatoreSingolo getGiocatore() {
        return giocatore;
    }

    public void setPuntiEffettuati(int puntiEffettuati) {
        this.puntiEffettuati = puntiEffettuati;
    }


    @Override
    public String toString() {
        return "StatisticheGiocatore{" +
                "giocatore: " + giocatore +
                ", puntiEffettuati = " + puntiEffettuati +
                "}\n";
    }
}
