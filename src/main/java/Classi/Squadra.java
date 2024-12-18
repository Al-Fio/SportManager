package Classi;

import java.util.HashMap;
import java.util.Map;

public class Squadra {
    private String nome;
    private Map<String, Componente> elencoComponenti;
    private Sport sport;

    public Squadra(Sport sport, String nome) {
        this.nome = nome;
        this.sport = sport;
        this.elencoComponenti = new HashMap<>();
    }

    public void aggiungiComponente(String nome, String cognome, int eta, String ruolo, String CF) {
        Componente componente = new Componente(nome, cognome, eta, ruolo, CF);
        this.elencoComponenti.put(CF, componente);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Componente> getElencoComponenti() {
        return elencoComponenti;
    }

    public void setElencoComponenti(Map<String, Componente> elencoComponenti) {
        this.elencoComponenti = elencoComponenti;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
