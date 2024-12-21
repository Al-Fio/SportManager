package Classi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stagione {
    private String nome;

    private Torneo torneoCorrente;
    private Map<Integer, Torneo> elencoTornei;

    private Squadra squadraCorrente;
    private Map<String, Squadra> elencoSquadre;

    public Stagione(String nome) {
        this.nome = nome;

        this.elencoTornei = new HashMap<Integer, Torneo>();
        this.elencoSquadre = new HashMap<String, Squadra>();
    }

    public void aggiungiTorneo(String nome, Sport sport, Modalita modalita, float quotaIscrizione) {
        this.torneoCorrente = new Torneo(this.elencoTornei.size()+1, nome, sport, modalita, quotaIscrizione);
    }

    public void nuovoRegolamento(int numeroSquadre, int numeroMaxComponentiSquadra, int punteggioVittoria, int punteggioPareggio, int punteggioSconfitta) {
        torneoCorrente.nuovoRegolamento(numeroSquadre, numeroMaxComponentiSquadra, punteggioVittoria, punteggioPareggio, punteggioSconfitta);
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
                "nome='" + nome + '\'' +
                ", torneoCorrente=" + torneoCorrente +
                ", elencoTornei=" + elencoTornei +
                ", squadraCorrente=" + squadraCorrente +
                ", elencoSquadre=" + elencoSquadre +
                '}';
    }
}
