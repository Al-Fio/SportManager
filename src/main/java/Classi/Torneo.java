package Classi;

import Eccezioni.WrongPartException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Enum.Esito;

public class Torneo {
    private String nome;
    private float quotaIscrizione;
    private int codice;

    private Sport sport;
    private Regolamento regolamento;
    private Modalita modalita;

    private Map<String, Partecipante> elencoPartecipanti;
    private Partecipante partecipanteCorrente;

    private Calendario calendario;
    private Calendario calendarioCorrente;

    private Classifica classifica;

    // ********************* Costruttore
    public Torneo(int codice, String nome, Sport sport, Modalita modalita, float quotaIscrizione) {
        this.codice = codice;
        this.nome = nome;
        this.sport = sport;
        this.regolamento = null;
        this.modalita = modalita;
        this.quotaIscrizione = quotaIscrizione;
        this.calendario = null;

        this.classifica = new Classifica();

        elencoPartecipanti = new HashMap<String, Partecipante>();
    }


    // ********************* Caso d'uso UC1 - Inserisci nuovo Torneo
    public void nuovoRegolamento(int numeroSquadre, int numeroMinimoGiocatori, int punteggioVittoria, int punteggioPareggio, int punteggioSconfitta) {
        regolamento = new Regolamento(numeroSquadre, numeroMinimoGiocatori, punteggioVittoria, punteggioPareggio, punteggioSconfitta);

        classifica.setRegolamento( regolamento );
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

        if (partecipante1 == null || partecipante2 == null) {
            System.out.println("Partecipante non trovato");
            return;
        } else {
            calendarioCorrente.creaPartita(partecipante1, partecipante2);
        }
    }

    public void inizializzaPartita(Campo campo, LocalDateTime data) {
        calendarioCorrente.inizializzaPartita(campo, data, this.classifica);
    }

    public void confermaCalendario() {
        calendario = calendarioCorrente;

        calendarioCorrente = null;
    }

    public void modificaCalendario() {
        calendarioCorrente = calendario;
    }

    public void eliminaPartita(Campo campo, LocalDateTime data) {
        calendarioCorrente.eliminaPartita(campo, data);
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


    // ********************* Caso d'uso UC8 - Inserisci i risultati di una Partita
    public void selezionaPartita(Campo campo, LocalDateTime data) {
        calendario.selezionaPartita(campo, data);
    }

    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        calendario.inserisciRisultato(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2);
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
                '}';
    }
}
