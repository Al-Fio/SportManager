package Classi;
import Eccezioni.WrongPartException;
import Interfacce.StatoPartita;
import Enum.Esito;

import java.util.List;

public class PartitaDaDisputare implements StatoPartita {
    private Partita partita;


    // ********************* Costruttore
    public PartitaDaDisputare(Partita partita) {
        this.partita = partita;
    }


    @Override
    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2, boolean aSquadre) {
        partita.setStatoPartita(new PartitaDisputata(partita, punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2, aSquadre));
    }

    @Override
    public void inserisciStatisticheGiocatore(Partecipante partecipante, int puntiEffettuati) {
        try {
            throw new WrongPartException();
        } catch (WrongPartException e) {
            System.out.println(e.getMessage());
        }
    }


    // ********************* Getter e Setter
    @Override
    public int getPunteggioPartecipante1() {
        try {
            throw new WrongPartException();
        } catch (WrongPartException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    @Override
    public int getPunteggioPartecipante2() {
        try {
            throw new WrongPartException();
        } catch (WrongPartException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    @Override
    public Esito getEsitoPartecipante1() {
        try {
            throw new WrongPartException();
        } catch (WrongPartException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Esito getEsitoPartecipante2() {
        try {
            throw new WrongPartException();
        } catch (WrongPartException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<StatisticheGiocatore> getStatistiche() {
        try {
            throw new WrongPartException();
        } catch (WrongPartException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Partecipante getPartecipante1() {
        try {
            throw new WrongPartException();
        } catch (WrongPartException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Partecipante getPartecipante2() {
        try {
            throw new WrongPartException();
        } catch (WrongPartException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    @Override
    public String toString() {
        return "PartitaDaDisputare {" + partita +
                '}';
    }
}
