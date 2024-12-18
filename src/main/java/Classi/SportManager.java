package Classi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SportManager {

    private static SportManager instanceSportManager;
    private Map<Integer, Modalita> elencoModalita;
    private Map<Integer, Regolamento> elencoRegolamenti;
    private Map<Integer, Sport> sports;
    private Stagione stagione;


    private SportManager() {
        this.elencoModalita = new HashMap<>();
        this.elencoRegolamenti = new HashMap<>();
        this.sports = new HashMap<>();

        loadElencoModalita();
        loadElencoRegolamento();
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

    // Da chiedere come implementare il caricamento nel caso d'uso d'avviamento
    public void loadSports(){
        sports.put(1, new Sport(1, "Calcio"));
    }

    public void loadElencoModalita(){
        elencoModalita.put(1, new Modalita(1));
    }

    public void loadElencoRegolamento(){
        elencoRegolamenti.put(1, new Regolamento(1));
    }

    public void loadStagione() {
        this.stagione = Stagione.getInstance("2024");
    }
    //

    public void nuovoTorneo(String nome, int codice_sport, int codice_regolamento, int codice_modalita, float quota_iscrizione){
        try {
            Sport sportTorneo = sports.get(codice_sport);
            if (sportTorneo == null)
                throw new Exception("Errore: Sport non trovato.");

            Regolamento regolamentoTorneo = elencoRegolamenti.get(codice_regolamento);
            if (regolamentoTorneo == null)
                throw new Exception("Errore: Regolamento non trovato.");

            Modalita modalitaTorneo = elencoModalita.get(codice_modalita);
            if (modalitaTorneo == null)
                throw new Exception("Errore: Modalita non trovata.");

            stagione.aggiungiTorneo(nome, sportTorneo, regolamentoTorneo, modalitaTorneo, quota_iscrizione);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Errore: Si è verificato un errore nella creazione di un nuovo torneo.");
        }
    }

    public void confermaTorneo(){ //stagione dovrà confermare il torneo corrente
        stagione.confermaTorneo();
    }

    public void nuovaSquadra(int codice_sport, String nome) { //meglio sarebbe usare nome_squadra

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
}