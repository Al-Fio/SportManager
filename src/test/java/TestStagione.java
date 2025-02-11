import Classi.Campo;
import Classi.Modalita;
import Classi.Sport;
import Classi.Stagione;
import Eccezioni.WrongPartException;
import org.junit.*;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TestStagione {
    static Stagione stagione;

    @BeforeClass
    public static void initTest() {
        stagione = new Stagione("2024_2025");
    }

    @After
    public void clearTest(){
        stagione.getElencoTornei().clear();
        stagione.getElencoPartecipanti().clear();
    }


    // ********************* Test Caso d'uso UC1 - Inserisci nuovo Torneo
    // Metodo di inizializzazione per il caso d'uso UC1
    private void useCaseUC1(String nomeTorneo, int codSport, String nomeSport, int codModalita, String nomeModalita) {
        Sport sport = new Sport(codSport, nomeSport);
        Modalita modalita = new Modalita(codModalita, nomeModalita);

        stagione.aggiungiTorneo(nomeTorneo, sport, modalita, 150);
    }


    // ********************* Test Caso d'uso UC2 - Inserisci nuova Squadra/Giocatore Singolo nel Sistema


    // ********************* Caso d'uso UC3 - Inserisci nuova Squadra/Giocatore Singolo nel Torneo
    // Metodo di inizializzazione per il caso d'uso UC3
    private void useCaseUC3() {
        // Creazione Tornei
        useCaseUC1("WorldCup", 1, "Calcio", 1, "campionato");
        stagione.nuovoRegolamento(20, 8, 3, 1, 0);
        stagione.confermaTorneo();

        useCaseUC1("WorldCup2025", 2, "Calcio5", 1, "campionato");
        stagione.nuovoRegolamento(20, 8, 3, 1, 0);
        stagione.confermaTorneo();

        // Creazione Partecipanti
        stagione.nuovoGiocatoreSingolo("Mario", "Rossi", 18, "MRORSS18");
        stagione.confermaPartecipante();

        stagione.nuovoGiocatoreSingolo("Marco", "Berti", 35, "MRCBRT35");
        stagione.confermaPartecipante();

        stagione.nuovoGiocatoreSingolo("Marcello", "Storti", 45, "MRCSTR45");
        stagione.confermaPartecipante();

        stagione.nuovaSquadra("Bari");
        stagione.nuovoComponente("Paperino", "Paperinopoli", 18, "PPRPP18");
        stagione.confermaPartecipante();

        stagione.nuovaSquadra("Catania");
        stagione.nuovoComponente("Pippo", "Paperinopoli", 18, "PPNPL18");
        stagione.confermaPartecipante();
    }

    @Test
    public void testSelezionaSquadra() {
        useCaseUC3();

        stagione.selezionaTorneo(1);
        stagione.selezionaSquadra("Bari");

        assertNotNull(stagione.getPartecipanteCorrente());
    }

    @Test
    public void testSelezionaGiocatoreSingolo() {
        useCaseUC3();

        stagione.selezionaTorneo(1);

        stagione.selezionaGiocatoreSingolo("MRORSS18");

        assertNotNull(stagione.getPartecipanteCorrente());
    }


    // ********************* Test Caso d'uso UC4 - Visualizza Partecipanti
    // Metodo di inizializzazione per il caso d'uso UC4 - UC5
    private void useCaseUC4() {
        useCaseUC3();

        stagione.selezionaTorneo(1);
        stagione.selezionaSquadra("Bari");
        stagione.confermaIscrizionePartecipante();

        stagione.selezionaTorneo(1);
        stagione.selezionaSquadra("Catania");
        stagione.confermaIscrizionePartecipante();

        stagione.selezionaTorneo(1);
        stagione.selezionaGiocatoreSingolo("MRCBRT35");
        stagione.confermaIscrizionePartecipante();

        stagione.selezionaTorneo(1);
        stagione.selezionaGiocatoreSingolo("MRORSS18");
        stagione.confermaIscrizionePartecipante();
    }


    // ********************* Test Caso d'uso UC5 - Crea il calendario di un Torneo
    @Test
    public void testVerificaSovrapposzionePartite() {
        // DA IMPLEMENTARE
        useCaseUC4();
        stagione.creaCalendario(1);
        stagione.creaPartita("Bari", "Catania");
        Campo campo = new Campo(1, "Calcio");
        stagione.inizializzaPartita(campo, LocalDateTime.of(2025, 2, 14, 15, 00));
        stagione.confermaCalendario();

        boolean bool = stagione.verificaSovrapposizionePartite(campo, LocalDateTime.of(2025, 2, 14, 15, 00));
        assertEquals(false, bool);

        bool = stagione.verificaSovrapposizionePartite(campo, LocalDateTime.of(2025, 2, 16, 15, 00));
        assertEquals(true, bool);

    }


    // ********************* Test Caso d'uso UC8 - Inserisci i risultati di una Partita
    // Metodo di inizializzazione per il caso d'uso UC8
    private void useCaseUC8() {
        useCaseUC4();
        stagione.creaCalendario(1);
        stagione.creaPartita("Bari", "Catania");
        Campo campo = new Campo(1, "Calcio");
        stagione.inizializzaPartita(campo, LocalDateTime.of(2025, 2, 14, 15, 00));
        stagione.confermaCalendario();
    }
}
