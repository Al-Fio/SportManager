package Classi;

import java.util.HashMap;
import java.util.Map;

public class Torneo {
    private String nome;
    private float quotaIscrizione;
    private int codice;
    private Sport sport;
    private Regolamento regolamento;
    private Modalita modalita;
    private Map<String, Squadra> elencoSquadre;
    private boolean stato;

    public Torneo(int codice, String nome, Sport sport, Regolamento regolamento, Modalita modalita, float quotaIscrizione) {
        this.codice = codice;
        this.nome = nome;
        this.sport = sport;
        this.regolamento = regolamento;
        this.modalita = modalita;
        this.quotaIscrizione = quotaIscrizione;
        this.stato = true;

        elencoSquadre = new HashMap<String, Squadra>();
    }

    public void iscriviSquadra(String nome, Squadra squadra) {
        elencoSquadre.put(nome, squadra);
    }

    public Map<String, Squadra> getElencoSquadre() {
        return elencoSquadre;
    }

    public void setElencoSquadre(Map<String, Squadra> elencoSquadre) {
        this.elencoSquadre = elencoSquadre;
    }

    public Modalita getModalita() {
        return modalita;
    }

    public void setModalita(Modalita modalita) {
        this.modalita = modalita;
    }

    public Regolamento getRegolamento() {
        return regolamento;
    }

    public void setRegolamento(Regolamento regolamento) {
        this.regolamento = regolamento;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public float getQuotaIscrizione() {
        return quotaIscrizione;
    }

    public void setQuotaIscrizione(float quotaIscrizione) {
        this.quotaIscrizione = quotaIscrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
}
