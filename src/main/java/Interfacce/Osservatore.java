package Interfacce;
import Classi.Partecipante;
import Enum.Esito;

public interface Osservatore {
    void update(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2, Partecipante partecipante1, Partecipante partecipante2);
}
