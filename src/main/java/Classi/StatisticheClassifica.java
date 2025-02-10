package Classi;

public class StatisticheClassifica {
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
}
