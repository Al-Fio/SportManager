package Classi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SportManager {
    private static SportManager instanceSportManager;
    private Map<Integer, Modalita> elencoModalita;
    private Map<Integer, Sport> sports;
    private Stagione stagione;

    private SportManager() {
        this.elencoModalita = new HashMap<>();
        this.sports = new HashMap<>();

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

    public void loadStagione() {
        this.stagione = new Stagione("2024_2025");
    }

    public void nuovoTorneo(String nome, int codice_sport, int codice_modalita, float quotaIscrizione){
        try {
            Sport sportTorneo = sports.get(codice_sport);
            if (sportTorneo == null)
                throw new Exception("Errore: Sport non trovato.");

            Modalita modalitaTorneo = elencoModalita.get(codice_modalita);
            if (modalitaTorneo == null)
                throw new Exception("Errore: Modalita non trovata.");

            stagione.aggiungiTorneo(nome, sportTorneo, modalitaTorneo, quotaIscrizione);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Errore: Si è verificato un errore nella creazione di un nuovo torneo.");
        }
    }

    public void nuovoRegolamento(int numeroSquadre, int numeroMaxComponentiSquadra, int punteggioVittoria, int punteggioPareggio, int punteggioSconfitta) {
        stagione.nuovoRegolamento(numeroSquadre, numeroMaxComponentiSquadra, punteggioVittoria, punteggioPareggio, punteggioSconfitta);
    }

    public void confermaTorneo(){
        stagione.confermaTorneo();
    }

    public void nuovaSquadra(int codice_sport, String nome) {
        Sport sport = sports.get(codice_sport);

        stagione.aggiungiSquadra(sport, nome);
    }

    public void nuovoComponente(String nome, String cognome, int eta, String ruolo, String CF) {
        stagione.aggiungiComponente(nome, cognome, eta, ruolo, CF);
    }

    public void confermaSquadra() {
        stagione.confermaSquadra();
    }

    public List<Sport> iscrizioneSquadra() {
        List<Sport> elencoSport = new ArrayList<>();

        for(int key : sports.keySet()) {
            elencoSport.add(sports.get(key));
        }

        return elencoSport;
    }

    public List<Torneo> selezionaSport(int codice_sport) {
        Sport sport = sports.get(codice_sport);

        return stagione.torneiPerSport(sport);
    }

    public void selezionaTorneo(int codice_torneo) {
        stagione.selezionaTorneo(codice_torneo);
    }

    public void selezionaSquadra(String nome) {
        System.out.println(stagione.selezionaSquadra(nome));
    }

    public void confermaIscrizioneSquadra() {
        stagione.confermaIscrizioneSquadra();
    }

    public static SportManager getInstanceSportManager() {
        return instanceSportManager;
    }

    public static void setInstanceSportManager(SportManager instanceSportManager) {
        SportManager.instanceSportManager = instanceSportManager;
    }

    public Stagione getStagione() {
        return stagione;
    }

    public void setStagione(Stagione stagione) {
        this.stagione = stagione;
    }

    public Map<Integer, Sport> getSports() {
        return sports;
    }

    public void setSports(Map<Integer, Sport> sports) {
        this.sports = sports;
    }

    public Map<Integer, Modalita> getElencoModalita() {
        return elencoModalita;
    }

    public void setElencoModalita(Map<Integer, Modalita> elencoModalita) {
        this.elencoModalita = elencoModalita;
    }
}