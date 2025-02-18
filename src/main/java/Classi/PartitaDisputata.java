package Classi;

import Eccezioni.WrongPartException;
import Enum.Esito;
import Interfacce.Osservatore;
import Interfacce.StatoPartita;

import java.util.ArrayList;
import java.util.List;

public class PartitaDisputata implements StatoPartita {
    private int punteggioPartecipante1;
    private int punteggioPartecipante2;
    private Esito esitoPartecipante1;
    private Esito esitoPartecipante2;

    private Partita partita;

    private List<StatisticheGiocatore> statistiche;


    // ********************* Costruttore
    public PartitaDisputata(Partita partita, int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2, boolean aSquadre) {
        this.punteggioPartecipante1 = punteggioPartecipante1;
        this.punteggioPartecipante2 = punteggioPartecipante2;
        this.esitoPartecipante2 = esitoPartecipante2;
        this.esitoPartecipante1 = esitoPartecipante1;
        this.partita = partita;

        if(aSquadre) {
            statistiche = new ArrayList<>();
        }
    }


    @Override
    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2, boolean aSquadre) {
        try {
            throw new WrongPartException();
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }
    }


    // ********************* Caso d'uso UC9 - Inserisci Statistiche di un Giocatore
    public void inserisciStatisticheGiocatore(Partecipante partecipante, int puntiEffettuati) {
        boolean trovato = false;

        if(statistiche.size() != 0) {
            for(StatisticheGiocatore stat : statistiche) {
                if(stat.getGiocatore().equals(partecipante)) {
                    stat.setPuntiEffettuati(puntiEffettuati + stat.getPuntiEffettuati());
                    trovato = true;
                }
            }
        }

        if(!trovato)
            statistiche.add(new StatisticheGiocatore((GiocatoreSingolo) partecipante, puntiEffettuati));
    }


    // ********************* Getter e Setter
    public int getPunteggioPartecipante1() {
        return punteggioPartecipante1;
    }

    public int getPunteggioPartecipante2() {
        return punteggioPartecipante2;
    }

    public Esito getEsitoPartecipante1() {
        return esitoPartecipante1;
    }

    public Esito getEsitoPartecipante2() {
        return esitoPartecipante2;
    }

    public List<StatisticheGiocatore> getStatistiche() {
        return statistiche;
    }

    @Override
    public Partecipante getPartecipante1() {
        return partita.getPartecipante1();
    }

    @Override
    public Partecipante getPartecipante2() {
        return partita.getPartecipante2();
    }


    @Override
    public String toString() {
        return "PartitaDisputata [" + partita +
                "punteggioPartecipante1 = " + punteggioPartecipante1 +
                ", punteggioPartecipante2 = " + punteggioPartecipante2 +
                ", esitoPartecipante1 = " + esitoPartecipante1 +
                ", esitoPartecipante2 = " + esitoPartecipante2 +
                "]\n";
    }
}
