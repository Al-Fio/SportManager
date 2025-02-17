package Classi;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Eccezioni.WrongPartException;
import Enum.Esito;
import Interfacce.Osservatore;

import java.util.List;


public class Calendario implements Cloneable{
    public int codice;

    private List<Partita> elencoPartite;
    private Partita partitaCorrente;


    // ********************* Costruttore
    public Calendario(int codice) {
        this.codice = codice;
        this.elencoPartite = new ArrayList<>();
    }


    // ********************* Caso d'uso UC5 - Crea il calendario di un Torneo
    public void creaPartita(Partecipante partecipante1, Partecipante partecipante2) {
        partitaCorrente = new Partita(partecipante1, partecipante2);
    }

    public void inizializzaPartita(Campo campo, LocalDateTime data, Osservatore osservatore1, Osservatore osservatore2) {
        partitaCorrente.setCampo(campo);
        partitaCorrente.setData(data);

        partitaCorrente.attach(osservatore1);

        if(osservatore2 != null) partitaCorrente.attach(osservatore2);

        elencoPartite.add(partitaCorrente);

        partitaCorrente = null;
    }

    public void eliminaPartita(Campo campo, LocalDateTime data) {
        boolean trovato = false;

        for (Partita p : elencoPartite) {
            if(p.getCampo().equals(campo) && p.getData().equals(data)) {
                elencoPartite.remove(p);
                trovato = true;
                break;
            }
        }

        if(!trovato) {
            System.out.println("Partita non trovata");
        }
    }


    // ********************* Caso d'uso UC8 - Inserisci i risultati di una Partita
    public boolean selezionaPartita(Campo campo, LocalDateTime data) {
        for (Partita p : elencoPartite) {
            if(p.getCampo().equals(campo) && p.getData().equals(data)) {
                try {
                    partitaCorrente = (Partita) p.clone();
                } catch (CloneNotSupportedException e) {
                    System.err.println(e.getMessage());
                }
                return true;
            }
        }

        return false;
    }

    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2, boolean aSquadre) {
        partitaCorrente.inserisciRisultato(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2, aSquadre);
    }


    // ********************* Caso d'uso UC9 - Inserisci Statistiche di un Giocatore
    public Partecipante selezionaGiocatorePartita(String CF) {
        return partitaCorrente.selezionaGiocatorePartita(CF);
    }

    public void inserisciStatisticheGiocatore(Partecipante partecipante, int puntiEffettuati) {
        try {
            partitaCorrente.inserisciStatisticheGiocatore(partecipante, puntiEffettuati);
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }
    }

    public void confermaInserimentoStatistichePartita() {
        Partita p = partitaCorrente;

        elencoPartite.remove(p);
        elencoPartite.add(partitaCorrente);

        partitaCorrente = null;
    }


    // ********************* Getter e Setter
    public List<Partita> getElencoPartite() {
        return elencoPartite;
    }

    public Partita getPartitaCorrente() {
        return partitaCorrente;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Calendario clone = (Calendario) super.clone();

        clone.elencoPartite = new ArrayList<>();

        if(elencoPartite != null) {
            for(Partita p : elencoPartite) {
                clone.elencoPartite.add((Partita) p.clone());
            }
        }

        return clone;
    }


    @Override
    public String toString() {
        return "Calendario {" +
                "codice = " + codice +
                ", \nELENCO PARTITE:\n" + elencoPartite +
                '}';
    }
}
