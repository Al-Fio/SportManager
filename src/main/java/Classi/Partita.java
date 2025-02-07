package Classi;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Partita {
    private LocalDateTime data;
    private Partecipante[] partecipanti;
    private Campo campo;


    // ********************* Costruttore
    public Partita(Partecipante partecipante1, Partecipante partecipante2) {
        partecipanti = new Partecipante[2];
        partecipanti[0] = partecipante1;
        partecipanti[1] = partecipante2;

        data = null;
        campo = null;
    }


    // ********************* Getter e Setter
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }


    @Override
    public String toString() {
        return "Partita{" +
                "data=" + data +
                ", partecipanti=" + Arrays.toString(partecipanti) +
                ", campo=" + campo +
                '}';
    }
}
