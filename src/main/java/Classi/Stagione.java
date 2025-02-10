package Classi;

import Eccezioni.WrongPartException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void nuovoRegolamento(int numeroSquadre, int numeroMinimoGiocatori, int punteggioVittoria, int punteggioPareggio, int punteggioSconfitta) {
        torneoCorrente.nuovoRegolamento(numeroSquadre, numeroMinimoGiocatori, punteggioVittoria, punteggioPareggio, punteggioSconfitta);
    }

    public void confermaTorneo() {
        int codice = torneoCorrente.getCodice();

        elencoTornei.put(codice, torneoCorrente);

        torneoCorrente = null;
    }


    // ********************* Caso d'uso UC2 - Inserisci nuova Squadra/Giocatore Singolo nel Sistema
    public void nuovaSquadra(String nome) {
        //Verifica unicità partecipante
        if (elencoPartecipanti.containsKey(nome)) {
            System.err.println("Squadra già inserita nel sistema");
        } else {
            this.partecipanteCorrente = new Squadra(nome);
        }
    }

    public void nuovoGiocatoreSingolo(String nome, String cognome, int eta, String CF) {
        if (elencoPartecipanti.containsKey(CF)) {
            System.err.println("Giocatore già inserito nel sistema");
        } else {
            this.partecipanteCorrente = new GiocatoreSingolo (nome, cognome, eta, CF);
        }
    }

    public void nuovoComponente(String nome, String cognome, int eta, String CF) {
        GiocatoreSingolo componente = new GiocatoreSingolo (nome, cognome, eta, CF);

        try {
            partecipanteCorrente.aggiungiComponente(CF, componente);
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }
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
            if (torneo.getSport().equals(sport) && torneo.getStato())
                tornei.add(torneo);
        }

        return tornei;
    }

    public void selezionaTorneo(int codiceTorneo) {
        this.torneoCorrente = elencoTornei.get(codiceTorneo);
    }

    public float selezionaSquadra(String nomeSquadra) {
        this.partecipanteCorrente = elencoPartecipanti.get(nomeSquadra);
        String cf;

        // Verifica unicità componenti squadra
        try {
            for(Partecipante componente : partecipanteCorrente.getElencoComponenti().values()) {
                cf = componente.getId();

                if(torneoCorrente.verificaUnicitaPartecipanteTorneo(cf)){
                    System.out.println("Trovato giocatore nella squadra presente in un'altra squadra iscritta al torneo");
                    return 0;
                } else{
                    torneoCorrente.setPartecipanteCorrente(partecipanteCorrente);
                }
            }
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }

        try {
            if(torneoCorrente.numeroMinimoGiocatori() > partecipanteCorrente.getElencoComponenti().size()){
                System.out.println("La squadra non ha il numero minimo di giocatori.");
            }
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }

        return torneoCorrente.getQuotaIscrizione();
    }

    public float selezionaGiocatoreSingolo(String cf) {
        this.partecipanteCorrente = elencoPartecipanti.get(cf);

        // Verifica unicità componenti squadra
        if(torneoCorrente.verificaUnicitaPartecipanteTorneo(cf)) {
            System.out.println("Trovato giocatore presente in un'altra squadra iscritta al torneo");
            return 0;
        } else{
            torneoCorrente.setPartecipanteCorrente(partecipanteCorrente);
        }

        return torneoCorrente.getQuotaIscrizione();
    }

    public List<GiocatoreSingolo> visualizzaGiocatoriSingoli() {
        return torneoCorrente.visualizzaGiocatoriSingoli();
    }

    public void accorpaGiocatoreSingolo(String cf) {
        torneoCorrente.accorpaGiocatoreSingolo(cf);
    }

    public void confermaIscrizionePartecipante() {
        torneoCorrente.confermaIscrizionePartecipante();

        elencoTornei.replace(torneoCorrente.getCodice(), torneoCorrente);

        partecipanteCorrente = null;
        torneoCorrente = null;
    }


    // ********************* Caso d'uso UC4 - Visualizza Partecipanti
    public Map<String, Partecipante> visualizzaPartecipanti(int codiceTorneo) {
        Map<String, Partecipante> partecipanti = new HashMap<>();
        torneoCorrente = elencoTornei.get(codiceTorneo);

        if(torneoCorrente != null){
            partecipanti = torneoCorrente.getElencoPartecipanti();

            if(partecipanti == null){
                System.out.println("Nessun partecipante iscritto al torneo");
            }

        } else {
            System.out.println("Torneo non trovato");
            partecipanti = null;
        }

        torneoCorrente = null;

        return partecipanti;
    }


    // ********************* Caso d'uso UC5 - Crea il calendario di un Torneo
    public void creaCalendario(int codiceTorneo) {
       torneoCorrente = elencoTornei.get(codiceTorneo);

       torneoCorrente.creaCalendario();
    }

    public void creaPartita(String nomePartecipante1, String nomePartecipante2) {
        torneoCorrente.creaPartita(nomePartecipante1, nomePartecipante2);
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
        torneoCorrente = elencoTornei.get(codiceTorneo);

        torneoCorrente.modificaCalendario();
    }

    public void eliminaPartita(Campo campo, LocalDateTime data) {
        torneoCorrente.eliminaPartita(campo, data);
    }

    public boolean verificaSovrapposizionePartite(Campo campo, LocalDateTime data) {
        Boolean verifica = true;

        for(Torneo torneo : elencoTornei.values()) {
            for(Partita partita : torneo.getCalendario().getElencoPartite()) {
                if(partita.getCampo().equals(campo) && partita.getData().equals(data)) {
                    verifica = false;

                    return verifica;
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

        for(Torneo torneo : elencoTornei.values()) {
            programmazione.addAll(torneo.getCalendario().getElencoPartite());
        }

        return programmazione;
    }


    // ********************* Caso d'uso UC8 - Inserisci i risultati di una Partita
    public void selezionaPartita(Campo campo, LocalDateTime data) {
        torneoCorrente.selezionaPartita(campo, data);
    }

    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        torneoCorrente.inserisciRisultato(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2);
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
