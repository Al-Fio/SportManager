package Classi;

import Exceptions.WrongPartException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Torneo {
    private String nome;
    private float quotaIscrizione;
    private int codice;
    private boolean stato;

    private Sport sport;
    private Regolamento regolamento;
    private Modalita modalita;

    private Map<String, Partecipante> elencoPartecipanti;
    private Partecipante partecipanteCorrente;

    private Calendario calendario;
    private Calendario calendarioCorrente;


    // ********************* Costruttore
    public Torneo(int codice, String nome, Sport sport, Modalita modalita, float quotaIscrizione) {
        this.codice = codice;
        this.nome = nome;
        this.sport = sport;
        this.regolamento = null;
        this.modalita = modalita;
        this.quotaIscrizione = quotaIscrizione;
        this.stato = true;
        this.calendario = null;

        elencoPartecipanti = new HashMap<String, Partecipante>();
    }


    // ********************* Caso d'uso UC1 - Inserisci nuovo Torneo
    public void nuovoRegolamento(int numeroSquadre, int numeroMinimoGiocatori, int punteggioVittoria, int punteggioPareggio, int punteggioSconfitta) {
        regolamento = new Regolamento(numeroSquadre, numeroMinimoGiocatori, punteggioVittoria, punteggioPareggio, punteggioSconfitta);
    }


    // ********************* Caso d'uso UC3 - Inserisci nuova Squadra/Giocatore Singolo nel Torneo
    public boolean verificaUnicitaPartecipanteTorneo(String cf) {
        boolean verifica = false;

        for (Partecipante partecipante : elencoPartecipanti.values()) {
            if (partecipante.getClass().equals(Squadra.class)) {
                try {
                    verifica = partecipante.verificaPresenzaComponente(cf);
                } catch (WrongPartException e) {
                    throw new RuntimeException(e);
                }
            } else {
                verifica = partecipante.getId().equals(cf);
            }

            if (verifica) return true;
        }

        return false;
    }

    public int numeroMinimoGiocatori() {
        return regolamento.getNumeroMinimoGiocatori();
    }

    public List<GiocatoreSingolo> visualizzaGiocatoriSingoli() {
        List<GiocatoreSingolo> giocatoriSingoli = new ArrayList<GiocatoreSingolo>();

        for(Partecipante partecipante : elencoPartecipanti.values()) {
            if (partecipante.getClass().equals(GiocatoreSingolo.class)) {
                giocatoriSingoli.add((GiocatoreSingolo) partecipante);
            }
        }

        return giocatoriSingoli;
    }

    public void accorpaGiocatoreSingolo(String cf) {
        GiocatoreSingolo giocatore = (GiocatoreSingolo) elencoPartecipanti.get(cf);

        try {
            partecipanteCorrente.aggiungiComponente(cf, giocatore);
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }

        elencoPartecipanti.remove(cf);
    }

    public void confermaIscrizionePartecipante() {
        String id = partecipanteCorrente.getId();

        elencoPartecipanti.put(id, partecipanteCorrente);

        partecipanteCorrente = null;
    }


    // ********************* Caso d'uso UC5 - Crea il calendario di un Torneo
    public void creaCalendario(){
        calendarioCorrente = new Calendario(codice);
    }

    public void creaPartita(String nomePartecipante1, String nomePartecipante2) {
        Partecipante partecipante1 = elencoPartecipanti.get(nomePartecipante1);
        Partecipante partecipante2 = elencoPartecipanti.get(nomePartecipante2);

        calendarioCorrente.creaPartita(partecipante1, partecipante2);
    }

    public void inizializzaPartita(Campo campo, LocalDateTime data) {
        calendarioCorrente.inizializzaPartita(campo, data);
    }

    public void confermaCalendario() {
        calendario = calendarioCorrente;

        calendarioCorrente = null;
    }


    // ********************* Caso d'uso UC6 - Visualizza il calendario di un Torneo
    public Calendario visualizzaCalendario() {
        if (calendario == null) {
            System.out.println("Calendario non trovato");
        }

        if(calendario.getElencoPartite() == null) {
            System.out.println("Non è stata inserita nessuna partita nel calendario");
        }

        return calendario;
    }


    // ********************* Getter e Setter
    public Map<String, Partecipante> getElencoPartecipanti() {
        return elencoPartecipanti;
    }

    public Regolamento getRegolamento() {
        return regolamento;
    }

    public Sport getSport() {
        return sport;
    }

    public int getCodice() {
        return codice;
    }

    public float getQuotaIscrizione() {
        return quotaIscrizione;
    }

    public boolean getStato() {
        return stato;
    }

    public void setPartecipanteCorrente(Partecipante partecipanteCorrente) {
        this.partecipanteCorrente = partecipanteCorrente;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public Calendario getCalendarioCorrente() {
        return calendarioCorrente;
    }

    @Override
    public String toString() {
        return "Torneo{" +
                "codice='" + codice + '\'' +
                ", nome=" + nome +
                ", quotaIscrizione=" + quotaIscrizione +
                ", sport=" + sport +
                ", regolamento=" + regolamento +
                ", modalita=" + modalita +
                ", elencoSquadre=" + elencoPartecipanti +
                ", stato=" + stato +
                '}';
    }
}
