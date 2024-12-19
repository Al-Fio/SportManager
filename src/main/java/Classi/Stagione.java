package Classi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stagione {
    private Torneo torneoCorrente;
    private Map<Integer, Torneo> elencoTornei;

    private Squadra squadraCorrente;
    private Map<String, Squadra> elencoSquadre;

    private static int codice;
    private static Stagione instanceStagione;
    private String nome;

    private Stagione(String nome) {
        this.nome = nome;
        this.codice = 1;

        this.elencoTornei = new HashMap<Integer, Torneo>();
        this.elencoSquadre = new HashMap<String, Squadra>();
    }

    // Chiedere se si può passare
    public static Stagione getInstance(String nome) {
        if (instanceStagione == null) {
            System.err.println("Stagione inizializzata");
            instanceStagione = new Stagione(nome);
        }

        return instanceStagione;
    }

    public void aggiungiTorneo(String nome, Sport sport, Regolamento regolamento, Modalita modalita, float quotaIscrizione) {
        this.torneoCorrente = new Torneo(codice++, nome, sport, regolamento, modalita, quotaIscrizione);
    }

    public void confermaTorneo() {
        int codice = torneoCorrente.getCodice();

        elencoTornei.put(codice, torneoCorrente);

        torneoCorrente = null;
    }

    public void aggiungiSquadra(Sport sport, String nome) {
        this.squadraCorrente = new Squadra(sport, nome);
    }

    public void aggiungiComponente(String nome, String cognome, int eta, String ruolo, String CF) {
        squadraCorrente.aggiungiComponente(nome, cognome, eta, ruolo, CF);
    }

    public void confermaSquadra() {
        String nome = squadraCorrente.getNome();

        this.elencoSquadre.put(nome, squadraCorrente);

        squadraCorrente = null;
    }

    public List<Torneo> torneiPerSport(Sport sport) {
        List<Torneo> tornei = new ArrayList<Torneo>();
        Torneo torneo;

        for(int key : elencoTornei.keySet()) {

            torneo = elencoTornei.get(key);
            if (torneo.getSport().equals(sport) && torneo.getStato())
                tornei.add(elencoTornei.get(key));
        }

        return tornei;
    }

    public void selezionaTorneo(int codiceTorneo) {
        this.torneoCorrente = elencoTornei.get(codiceTorneo);
    }

    public float selezionaSquadra(String nomeSquadra) {
        this.squadraCorrente = elencoSquadre.get(nomeSquadra);

        return torneoCorrente.getQuotaIscrizione();
    }

    public void confermaIscrizioneSquadra() {
        torneoCorrente.iscriviSquadra(squadraCorrente.getNome(), squadraCorrente);
        elencoTornei.replace(torneoCorrente.getCodice(), torneoCorrente);

        torneoCorrente = null;
        squadraCorrente = null;
    }

    public Torneo getTorneoCorrente() {
        return torneoCorrente;
    }

    public void setTorneoCorrente(Torneo torneoCorrente) {
        this.torneoCorrente = torneoCorrente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static Stagione getInstanceStagione() {
        return instanceStagione;
    }

    public static void setInstanceStagione(Stagione instanceStagione) {
        Stagione.instanceStagione = instanceStagione;
    }

    public static int getCodice() {
        return codice;
    }

    public static void setCodice(int codice) {
        Stagione.codice = codice;
    }

    public Map<String, Squadra> getElencoSquadre() {
        return elencoSquadre;
    }

    public void setElencoSquadre(Map<String, Squadra> elencoSquadre) {
        this.elencoSquadre = elencoSquadre;
    }

    public Squadra getSquadraCorrente() {
        return squadraCorrente;
    }

    public void setSquadraCorrente(Squadra squadraCorrente) {
        this.squadraCorrente = squadraCorrente;
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
                "torneoCorrente=" + torneoCorrente +
                ", elencoTornei=" + elencoTornei +
                ", squadraCorrente=" + squadraCorrente +
                ", elencoSquadre=" + elencoSquadre +
                ", nome='" + nome + '\'' +
                '}';
    }
}
