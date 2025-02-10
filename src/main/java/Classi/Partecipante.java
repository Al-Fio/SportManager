package Classi;

import Eccezioni.WrongPartException;

import java.util.HashMap;
import java.util.Map;

public abstract class Partecipante {
    private String nome;


    // ********************* Costruttore
    public Partecipante(String nome) {
        this.nome = nome;
    }


    public void aggiungiComponente(String CF, GiocatoreSingolo componente) throws WrongPartException {
        if(this instanceof GiocatoreSingolo) throw new WrongPartException();
    }


    public boolean verificaPresenzaComponente(String cf) throws WrongPartException{
        if(this instanceof GiocatoreSingolo) throw new WrongPartException();
        return false;
    }


    // ********************* Getter e Setter
    public abstract String getId();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, GiocatoreSingolo> getElencoComponenti() throws WrongPartException{
        if(this instanceof GiocatoreSingolo) throw new WrongPartException();
        return new HashMap<String, GiocatoreSingolo>();
    }
}
