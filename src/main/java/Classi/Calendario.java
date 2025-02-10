package Classi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import Enum.Esito;
import Interfacce.Osservatore;

import java.util.List;


public class Calendario {
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

    public void inizializzaPartita(Campo campo, LocalDateTime data, Osservatore osservatore) {
        partitaCorrente.setCampo(campo);
        partitaCorrente.setData(data);

        partitaCorrente.attach(osservatore);

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
    public void selezionaPartita(Campo campo, LocalDateTime data) {
        for (Partita p : elencoPartite) {
            if(p.getCampo().equals(campo) && p.getData().equals(data)) {
                partitaCorrente = p;
                break;
            }
        }
    }

    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        Partita p = partitaCorrente;

        partitaCorrente.inserisciRisultato(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2);

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
    public String toString() {
        return "Calendario{" +
                "codice=" + codice +
                ", elencoPartite=" + elencoPartite +
                '}';
    }
}
