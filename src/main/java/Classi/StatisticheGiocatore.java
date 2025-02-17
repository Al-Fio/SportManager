package Classi;

public class StatisticheGiocatore implements Cloneable{
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
    protected Object clone() throws CloneNotSupportedException {
        StatisticheGiocatore clone = (StatisticheGiocatore) super.clone();

        clone.giocatore = (GiocatoreSingolo) giocatore.clone();

        return clone;
    }

    @Override
    public String toString() {
        return "StatisticheGiocatore{" +
                "giocatore: " + giocatore +
                ", puntiEffettuati = " + puntiEffettuati +
                "}\n";
    }
}
