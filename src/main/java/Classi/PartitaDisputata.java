package Classi;

import Eccezioni.WrongPartException;
import Enum.Esito;
import Interfacce.StatoPartita;

public class PartitaDisputata implements StatoPartita {
    private int punteggioPartecipante1;
    private int punteggioPartecipante2;
    private Esito esitoPartecipante1;
    private Esito esitoPartecipante2;

    private Partita partita;


    // ********************* Costruttore
    public PartitaDisputata(Partita partita, int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        this.punteggioPartecipante1 = punteggioPartecipante1;
        this.punteggioPartecipante2 = punteggioPartecipante2;
        this.esitoPartecipante2 = esitoPartecipante2;
        this.esitoPartecipante1 = esitoPartecipante1;
    }


    @Override
    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        try {
            throw new WrongPartException();
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }
    }
}
