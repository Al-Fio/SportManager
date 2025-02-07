package Classi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void inizializzaPartita(Campo campo, LocalDateTime data) {
        partitaCorrente.setCampo(campo);
        partitaCorrente.setData(data);

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
