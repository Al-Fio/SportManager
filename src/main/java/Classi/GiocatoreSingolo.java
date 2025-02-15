package Classi;

public class GiocatoreSingolo extends Partecipante{
    private String CF;
    private String cognome;
    private int eta;

    // ********************* Costruttore
    public GiocatoreSingolo(String nome, String cognome, int eta, String CF) {
        super(nome);
        this.CF = CF;
        this.eta = eta;
        this.cognome = cognome;
    }


    // ********************* Getter e Setter
    public String getId() {
        return CF;
    }


    @Override
    public String toString() {
        return "GiocatoreSingolo [" +
                "Codice fiscale = '" + CF + '\'' +
                ", nome = '" + super.getNome() + '\'' +
                ", cognome = '" + cognome + '\'' +
                ", eta = " + eta +
                "]\n";
    }
}
