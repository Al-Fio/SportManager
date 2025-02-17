package Classi;

public class StatisticheClassifica implements Cloneable {
    Partecipante partecipante;
    int punteggio;
    int puntiEffettuati;
    int puntiSubiti;


    // ********************* Costruttore
    public StatisticheClassifica(Partecipante partecipante, int punteggio, int puntiEffettuati, int puntiSubiti) {
        this.partecipante = partecipante;
        this.punteggio = punteggio;
        this.puntiEffettuati = puntiEffettuati;
        this.puntiSubiti = puntiSubiti;
    }


    // ********************* Getter e Setter
    public void setPunteggio(int punteggio) {
        this.punteggio += punteggio;
    }

    public void setPuntiEffettuati(int puntiEffettuati) {
        this.puntiEffettuati += puntiEffettuati;
    }

    public void setPuntiSubiti(int puntiSubiti) {
        this.puntiSubiti += puntiSubiti;
    }

    public Partecipante getPartecipante() {
        return partecipante;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        StatisticheClassifica clone = (StatisticheClassifica) super.clone();

        clone.partecipante = (Partecipante) partecipante.clone();

        return clone;
    }


    @Override
    public String toString() {
        return "StatisticheClassifica{" +
                "partecipante: " + partecipante +
                ", punteggio = " + punteggio +
                ", punti Effettuati = " + puntiEffettuati +
                ", punti Subiti = " + puntiSubiti +
                "} \n";
    }
}

