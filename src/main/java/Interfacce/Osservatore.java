package Interfacce;
import Classi.Partecipante;
import Classi.Partita;
import Enum.Esito;

public interface Osservatore {
    void update(StatoPartita partita, int operazione);
}
