package Classi;

import Eccezioni.WrongPartException;

import java.time.LocalDateTime;
import java.util.*;

import Enum.Esito;

public class Stagione {
    private String nome;

    private Torneo torneoCorrente;
    private Map<Integer, Torneo> elencoTornei;

    private Partecipante partecipanteCorrente;
    private Map<String, Partecipante> elencoPartecipanti;


    // ********************* Costruttore
    public Stagione(String nome) {
        this.nome = nome;

        this.elencoTornei = new HashMap<Integer, Torneo>();
        this.elencoPartecipanti = new HashMap<String, Partecipante>();
    }


    // ********************* Caso d'uso UC1 - Inserisci nuovo Torneo
    public void aggiungiTorneo(String nome, Sport sport, Modalita modalita, float quotaIscrizione) {
        this.torneoCorrente = new Torneo(this.elencoTornei.size()+1, nome, sport, modalita, quotaIscrizione);
    }

    public Torneo nuovoRegolamento(int numeroSquadre, int numeroMinimoGiocatori, int punteggioVittoria, int punteggioPareggio, int punteggioSconfitta) {
        torneoCorrente.nuovoRegolamento(numeroSquadre, numeroMinimoGiocatori, punteggioVittoria, punteggioPareggio, punteggioSconfitta);

        return torneoCorrente;
    }

    public void confermaTorneo() {
        int codice = torneoCorrente.getCodice();

        elencoTornei.put(codice, torneoCorrente);

        torneoCorrente = null;
    }


    // ********************* Caso d'uso UC2 - Inserisci nuova Squadra/Giocatore Singolo nel Sistema
    public boolean nuovaSquadra(String nome) {
        //Verifica unicità partecipante
        if (elencoPartecipanti.containsKey(nome)) {
            return false;
        } else {
            this.partecipanteCorrente = new Squadra(nome);
            return true;
        }
    }

    public boolean nuovoGiocatoreSingolo(String nome, String cognome, int eta, String CF) {
        if (elencoPartecipanti.containsKey(CF)) {
            return false;
        } else {
            this.partecipanteCorrente = new GiocatoreSingolo (nome, cognome, eta, CF);
            return true;
        }
    }

    public GiocatoreSingolo nuovoComponente(String nome, String cognome, int eta, String CF) {
        GiocatoreSingolo componente = new GiocatoreSingolo (nome, cognome, eta, CF);

        try {
            partecipanteCorrente.aggiungiComponente(CF, componente);
            return componente;
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public void confermaPartecipante() {
        String id = partecipanteCorrente.getId();

        this.elencoPartecipanti.put(id, partecipanteCorrente);

        partecipanteCorrente = null;
    }


    // ********************* Caso d'uso UC3 - Inserisci nuova Squadra/Giocatore Singolo nel Torneo
    public List<Torneo> torneiPerSport(Sport sport) {
        List<Torneo> tornei = new ArrayList<Torneo>();

        for(Torneo torneo : elencoTornei.values()) {
            if (torneo.getSport().equals(sport))
                tornei.add(torneo);
        }

        return tornei;
    }

    public Torneo selezionaTorneo(int codiceTorneo) {
        try {
            this.torneoCorrente = (Torneo) elencoTornei.get(codiceTorneo).clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e.getMessage());
        }
        return torneoCorrente;
    }

    public float selezionaSquadra(String nomeSquadra) {
        String cf;

        try {
            this.partecipanteCorrente = (Partecipante) elencoPartecipanti.get(nomeSquadra).clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e.getMessage());
        }

        if(partecipanteCorrente == null) {
            return -1;
        }

        // Verifica unicità componenti squadra
        try {
            for(Partecipante componente : partecipanteCorrente.getElencoComponenti().values()) {
                cf = componente.getId();

                if(torneoCorrente.verificaUnicitaPartecipanteTorneo(cf)){
                    System.out.println("Trovato giocatore nella squadra presente in un'altra squadra iscritta al torneo");
                    return -1;
                }
            }

            torneoCorrente.setPartecipanteCorrente(partecipanteCorrente);
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }

        if(!squadraCompleta()) {
            torneoCorrente.setElencoGiocatoriSingoliCorrente();
        }

        return torneoCorrente.getQuotaIscrizione();
    }

    public float selezionaGiocatoreSingolo(String cf) {
        this.partecipanteCorrente = elencoPartecipanti.get(cf);

        if(partecipanteCorrente == null) {
            return -1;
        }

        // Verifica unicità componenti squadra
        if(torneoCorrente.verificaUnicitaPartecipanteTorneo(cf)) {
            System.out.println("Trovato giocatore presente in un'altra squadra iscritta al torneo");
            return -1;
        } else{
            torneoCorrente.setPartecipanteCorrente(partecipanteCorrente);
        }

        return torneoCorrente.getQuotaIscrizione();
    }

    public Collection<GiocatoreSingolo> visualizzaGiocatoriSingoli() {
        return torneoCorrente.visualizzaGiocatoriSingoli();
    }

    public boolean accorpaGiocatoreSingolo(String cf) {
        return torneoCorrente.accorpaGiocatoreSingolo(cf);
    }

    public void confermaIscrizionePartecipante() {
        torneoCorrente.confermaIscrizionePartecipante();

        elencoTornei.replace(torneoCorrente.getCodice(), torneoCorrente);

        partecipanteCorrente = null;
        torneoCorrente = null;
    }

    public boolean squadraCompleta() {
        try {
            if(torneoCorrente.numeroMinimoGiocatori() > torneoCorrente.getPartecipanteCorrente().getElencoComponenti().size()){
                return false;
            }
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }


    // ********************* Caso d'uso UC4 - Visualizza Partecipanti
    public Map<String, Partecipante> visualizzaPartecipanti(int codiceTorneo) {
        Map<String, Partecipante> partecipanti = new HashMap<>();
        torneoCorrente = elencoTornei.get(codiceTorneo);

        if(torneoCorrente != null){
            partecipanti = torneoCorrente.getElencoPartecipanti();
        } else {
            partecipanti = null;
        }

        torneoCorrente = null;

        return partecipanti;
    }


    // ********************* Caso d'uso UC5 - Crea il calendario di un Torneo
    public void creaCalendario(int codiceTorneo) {
        try {
            torneoCorrente = (Torneo) elencoTornei.get(codiceTorneo).clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        torneoCorrente.creaCalendario();
    }

    public boolean creaPartita(String nomePartecipante1, String nomePartecipante2) {
        return torneoCorrente.creaPartita(nomePartecipante1, nomePartecipante2);
    }

    public void inizializzaPartita(Campo campo, LocalDateTime data) {
        torneoCorrente.inizializzaPartita(campo, data);
    }

    public void confermaCalendario() {
        torneoCorrente.confermaCalendario();

        elencoTornei.replace(torneoCorrente.getCodice(), torneoCorrente);

        torneoCorrente = null;
    }

    public void modificaCalendario(int codiceTorneo) {
        try {
            torneoCorrente = (Torneo) elencoTornei.get(codiceTorneo).clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e.getMessage());
        }

        torneoCorrente.modificaCalendario();
    }

    public void eliminaPartita(Campo campo, LocalDateTime data) {
        torneoCorrente.eliminaPartita(campo, data);
    }

    public boolean verificaSovrapposizionePartite(Campo campo, LocalDateTime data) {
        Boolean verifica = true;

        for(Torneo torneo : elencoTornei.values()) {
            if(torneo.getCalendario() != null) {
                for (Partita partita : torneo.getCalendario().getElencoPartite()) {
                    if (partita.getCampo().equals(campo) && partita.getData().equals(data)) {
                        verifica = false;

                        return verifica;
                    }
                }
            }
        }

        return verifica;
    }


    // ********************* Caso d'uso UC6 - Visualizza il calendario di un Torneo
    public Calendario visualizzaCalendario(int codiceTorneo) {
        torneoCorrente = elencoTornei.get(codiceTorneo);
        Calendario calendario;

        if(torneoCorrente != null){
            calendario =  torneoCorrente.visualizzaCalendario();
        } else {
            System.out.println("Torneo non trovato");
            calendario = null;
        }

        torneoCorrente = null;

        return calendario;
    }


    // ********************* Caso d'uso UC7 - Visualizza la Programmazione complessiva delle Partite
    public List<Partita> visualizzaProgrammazioneStagione() {
        List<Partita> programmazione = new ArrayList<>();

        if(elencoTornei != null)
            for(Torneo torneo : elencoTornei.values())
                if(torneo.getCalendario() != null)
                    programmazione.addAll(torneo.getCalendario().getElencoPartite());
        else
            return null;

        return programmazione;
    }


    // ********************* Caso d'uso UC8 - Inserisci i risultati di una Partita
    public boolean selezionaPartita(Campo campo, LocalDateTime data) {
        return torneoCorrente.selezionaPartita(campo, data);
    }

    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        torneoCorrente.inserisciRisultato(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2);
    }


    // ********************* Caso d'uso UC9 - Inserisci Statistiche di un Giocatore
    public boolean selezionaGiocatorePartita(String CF) {
        return torneoCorrente.selezionaGiocatorePartita(CF);
    }

    public void inserisciStatisticheGiocatore(int puntiEffettuati) {
        torneoCorrente.inserisciStatisticheGiocatore(puntiEffettuati);
    }

    public void confermaInserimentoStatistichePartita() {
        torneoCorrente.confermaInserimentoStatistichePartita();

        elencoTornei.replace(torneoCorrente.getCodice(), torneoCorrente);
    }


    // ********************* Caso d'uso UC10 - Visualizza le Statistiche di un Torneo
    public void visualizzaClassificaTorneo(int codiceTorneo) {
        torneoCorrente = elencoTornei.get(codiceTorneo);

        if(torneoCorrente == null) {
            System.out.println("Torneo non trovato");
            return;
        }

        Classifica classifica = torneoCorrente.getClassifica();

        if(!classifica.getListaClassifica().isEmpty())
            System.out.println(classifica);
        else
            System.out.println("Classifica vuota");

        ClassificaGiocatori classificaGiocatori = torneoCorrente.getClassificaGiocatori();
        if(classificaGiocatori != null)
            System.out.println(classificaGiocatori);

        torneoCorrente = null;
    }


    // ********************* Getter Setter
    public Torneo getTorneoCorrente() {
        return torneoCorrente;
    }

    public Map<String, Partecipante> getElencoPartecipanti() {
        return elencoPartecipanti;
    }

    public Partecipante getPartecipanteCorrente() {
        return partecipanteCorrente;
    }

    public Map<Integer, Torneo> getElencoTornei() {
        return elencoTornei;
    }

    public void setElencoTornei(Map<Integer, Torneo> elencoTornei) {
        this.elencoTornei = elencoTornei;
    }


    @Override
    public String toString() {
        return "Stagione{" +
                "nome='" + nome + '\'' +
                ", elencoTornei=" + elencoTornei +
                ", elencoPartecipanti=" + elencoPartecipanti +
                '}';
    }
}
