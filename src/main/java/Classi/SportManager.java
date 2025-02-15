package Classi;

import java.time.LocalDateTime;
import java.util.*;

import Enum.Esito;

public class SportManager {
    private static SportManager instanceSportManager;

    private Map<Integer, Modalita> elencoModalita;
    private Map<Integer, Sport> sports;
    private Map<Integer, Campo> elencoCampi;
    private Stagione stagione;


    // ********************* Costruttore - Su SportManager viene applicato il pattern Singleton
    private SportManager() {
        this.elencoModalita = new HashMap<>();
        this.sports = new HashMap<>();
        this.elencoCampi = new HashMap<>();

        loadElencoCampi();
        loadElencoModalita();
        loadSports();
        loadStagione();
    }
    public static SportManager getInstance() {
        if (instanceSportManager == null) {
            System.err.println("SportManager inizializzata");
            instanceSportManager = new SportManager();
        }

        return instanceSportManager;
    }


    // ********************* Caso d'Uso d'Avviamento
    public void loadSports(){
        sports.put(1, new Sport(1, "Calcio11"));
        sports.put(2, new Sport(2, "Calcio7"));
        sports.put(3, new Sport(3, "Basket"));
        sports.put(4, new Sport(4, "Pallavolo"));
    }
    public void loadElencoModalita(){
        elencoModalita.put(1, new Modalita(1, "eliminazione_diretta"));
        elencoModalita.put(2, new Modalita(2, "gironi_eliminazione_diretta"));
        elencoModalita.put(3, new Modalita(3, "campionato"));
    }
    public void loadElencoCampi() {
        elencoCampi.put(1, new Campo(1, "campo_calcio"));
        elencoCampi.put(2, new Campo(2, "campo_basket"));
        elencoCampi.put(3, new Campo(3, "campo_pallavolo"));
    }
    public void loadStagione() {
        this.stagione = new Stagione("Stagione " + LocalDateTime.now().getYear());
    }


    // ********************* Caso d'uso UC1 - Inserisci nuovo Torneo
    public void nuovoTorneo(String nome, int codiceSport, int codiceModalita, float quotaIscrizione){
        try {
            Sport sportTorneo = sports.get(codiceSport);

            if (sportTorneo == null)
                throw new Exception("Errore: Sport non trovato.");

            Modalita modalitaTorneo = elencoModalita.get(codiceModalita);
            if (modalitaTorneo == null)
                throw new Exception("Errore: Modalita non trovata.");

            stagione.aggiungiTorneo(nome, sportTorneo, modalitaTorneo, quotaIscrizione);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Errore: Si è verificato un errore nella creazione di un nuovo torneo.");
        }
    }

    public Torneo nuovoRegolamento(int numeroSquadre, int numeroMinimoGiocatori, int punteggioVittoria, int punteggioPareggio, int punteggioSconfitta) {
        return stagione.nuovoRegolamento(numeroSquadre, numeroMinimoGiocatori, punteggioVittoria, punteggioPareggio, punteggioSconfitta);
    }

    public void confermaTorneo(){
        stagione.confermaTorneo();
    }


    // ********************* Caso d'uso UC2 - Inserisci nuova Squadra/Giocatore Singolo nel Sistema
    public boolean nuovaSquadra(String nome) {
        return stagione.nuovaSquadra(nome);
    }

    public boolean nuovoGiocatoreSingolo(String nome, String cognome, int eta, String CF) {
        return stagione.nuovoGiocatoreSingolo(nome, cognome, eta, CF);
    }

    public GiocatoreSingolo nuovoComponente(String nome, String cognome, int eta, String CF) {
        return stagione.nuovoComponente(nome, cognome, eta, CF);
    }

    public void confermaPartecipante() {
        stagione.confermaPartecipante();
    }


    // ********************* Caso d'uso UC3 - Inserisci nuova Squadra/Giocatore Singolo nel Torneo
    public void iscrizionePartecipante() {
        List<Sport> elencoSport = new ArrayList<>();

        for(int key : sports.keySet()) {
            elencoSport.add(sports.get(key));
        }

        System.out.println(elencoSport);

        // Successivamente vedere se deve restituire qualcosa alla parte grafica
        // return elencoSport
    }

    public List<Torneo> selezionaSport(int codiceSport) {
        Sport sport = sports.get(codiceSport);

        return stagione.torneiPerSport(sport);
    }

    public Torneo selezionaTorneo(int codiceTorneo) {
        return stagione.selezionaTorneo(codiceTorneo);
    }

    public float selezionaSquadra(String nome) {
        return stagione.selezionaSquadra(nome);
    }

    public float selezionaGiocatoreSingolo(String cf) {
        return stagione.selezionaGiocatoreSingolo(cf);
    }

    public boolean visualizzaGiocatoriSingoli() {
        Collection<GiocatoreSingolo> giocatori = stagione.visualizzaGiocatoriSingoli();

        if(giocatori.isEmpty()) {
            System.out.println("Non ci sono giocatori singoli iscritti al torneo");
            return false;
        } else {
            System.out.println(giocatori);
            return true;
        }
    }

    public boolean accorpaGiocatoreSingolo(String cf) {
        return stagione.accorpaGiocatoreSingolo(cf);
    }

    public void confermaIscrizionePartecipante() {
        stagione.confermaIscrizionePartecipante();
    }

    public boolean squadraCompleta() {
        return stagione.squadraCompleta();
    }


    // ********************* Caso d'uso UC4 - Visualizza Partecipanti
    public Map<String, Partecipante> visualizzaPartecipanti(int codiceTorneo) {
        return stagione.visualizzaPartecipanti(codiceTorneo);
    }


    // ********************* Caso d'uso UC5 - Crea il calendario di un Torneo
    public void creaCalendario(int codiceTorneo) {
        stagione.creaCalendario(codiceTorneo);
    }

    public boolean creaPartita(String nomePartecipante1, String nomePartecipante2) {
        return stagione.creaPartita(nomePartecipante1, nomePartecipante2);
    }

    public boolean selezionaDataCampo(int codiceCampo, LocalDateTime data) {
        Campo campo = elencoCampi.get(codiceCampo);

        if(stagione.verificaSovrapposizionePartite(campo, data)) {
            stagione.inizializzaPartita(campo, data);
            return true;
        } else {
            return false;
        }
    }

    public void confermaCalendario() {
        stagione.confermaCalendario();
    }

    public void modificaCalendario(int codiceTorneo) {
        stagione.modificaCalendario(codiceTorneo);
    }

    public void eliminaPartita(int codiceCampo, LocalDateTime data) {
        stagione.eliminaPartita(elencoCampi.get(codiceCampo), data);
    }


    // ********************* Caso d'uso UC6 - Visualizza il calendario di un Torneo
    public Calendario visualizzaCalendario(int codiceTorneo) {
        return stagione.visualizzaCalendario(codiceTorneo);
    }


    // ********************* Caso d'uso UC7 - Visualizza la Programmazione complessiva delle Partite
    public List<Partita> visualizzaProgrammazioneStagione() {
        return stagione.visualizzaProgrammazioneStagione();
    }


    // ********************* Caso d'uso UC8 - Inserisci i risultati di una Partita
    public void inserisciRisultatoPartita() {
        System.out.println(stagione.getElencoTornei());
    }

    // funzione per la selezione del torneo già implementata per il caso d'uso UC3.

    public boolean selezionaPartita(int codiceCampo, LocalDateTime data) {
        return stagione.selezionaPartita(elencoCampi.get(codiceCampo), data);
    }

    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        stagione.inserisciRisultato(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2);
    }


    // ********************* Caso d'uso UC9 - Inserisci Statistiche di un Giocatore
    public boolean selezionaGiocatorePartita(String CF) {
        return stagione.selezionaGiocatorePartita(CF);
    }

    public void inserisciStatisticheGiocatore(int puntiEffettuati) {
        stagione.inserisciStatisticheGiocatore(puntiEffettuati);
    }

    public void confermaInserimentoStatistichePartita() {
        stagione.confermaInserimentoStatistichePartita();
    }

    public boolean torneoASquadre() {
        if(stagione.getTorneoCorrente().getRegolamento().getNumeroMinimoGiocatori() == 1)
            return false;
        else
            return true;
    }


    // ********************* Caso d'uso UC10 - Visualizza le Statistiche di un Torneo
    public Map<Integer, Torneo> visualizzaStatisticheTorneo() {
        return stagione.getElencoTornei();
    }

    public void visualizzaClassificaTorneo(int codiceTorneo) {
        stagione.visualizzaClassificaTorneo(codiceTorneo);
    }


    // ********************* Caso d'uso UC11 - Eliminazione Stagione
    public void eliminaStagione() {
        stagione = null;
        System.out.println("Stagione eliminata con successo");

    }


    // ********************* Getter e Setter
    public Stagione getStagione() {
        return stagione;
    }
}