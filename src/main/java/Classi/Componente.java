package Classi;

public class Componente {
    private String CF;
    private String nome;
    private String cognome;
    private String ruolo;
    private int eta;

    public Componente(String nome, String cognome, int eta, String ruolo, String CF) {
        this.CF = CF;
        this.eta = eta;
        this.ruolo = ruolo;
        this.cognome = cognome;
        this.nome = nome;
    }
}
