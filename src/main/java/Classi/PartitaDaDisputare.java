package Classi;
import Interfacce.StatoPartita;
import Enum.Esito;

public class PartitaDaDisputare implements StatoPartita {
    private Partita partita;


    // ********************* Costruttore
    public PartitaDaDisputare(Partita partita) {
        this.partita = partita;
    }

    @Override
    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        partita.setStatoPartita(new PartitaDisputata(partita, punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2));
    }
}
