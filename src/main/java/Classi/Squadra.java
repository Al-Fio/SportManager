package Classi;

import Eccezioni.WrongPartException;

import java.util.HashMap;
import java.util.Map;

public class Squadra extends Partecipante {
    private Map<String, GiocatoreSingolo> elencoComponenti;


    // ********************* Costruttore
    public Squadra(String nome) {
        super(nome);
        this.elencoComponenti = new HashMap<>();
    }


    // ********************* Caso d'uso UC2 - Inserisci nuova Squadra/Giocatore Singolo nel Sistema
    public void aggiungiComponente(String CF, GiocatoreSingolo componente) {
        this.elencoComponenti.put(CF, componente);
    }


    // ********************* Caso d'uso UC3 - Inserisci nuova Squadra/Giocatore Singolo nel Torneo
    @Override
    public boolean verificaPresenzaComponente(String cf) throws WrongPartException {
        return this.elencoComponenti.containsKey(cf);
    }


    // ********************* Getter e Setter
    public Map<String, GiocatoreSingolo> getElencoComponenti() {
        return elencoComponenti;
    }

    @Override
    public String getId() {
        return super.getNome();
    }

    @Override
    public String toString() {
        return "Squadra {" +
                "nome = '" + super.getNome() + '\'' +
                ", ELENCO COMPONENTI: \n" + elencoComponenti +
                "}\n";
    }
}
