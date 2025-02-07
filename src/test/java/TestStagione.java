import Classi.Campo;
import Classi.Modalita;
import Classi.Sport;
import Classi.Stagione;
import Exceptions.WrongPartException;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

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

    @Test
    public void testAggiungiTorneo() {
        useCaseUC1("Champions", 1, "Calcio", 1, "campionato");

        assertNotNull(stagione.getTorneoCorrente());
    }

    @Test
    public void testNuovoRegolamento() {
        useCaseUC1("Champions", 1, "Calcio", 1, "campionato");

        stagione.nuovoRegolamento(20, 8, 3, 1, 0);

        assertNotNull(stagione.getTorneoCorrente().getRegolamento());
    }

    @Test
    public void testConfermaTorneo() {
        useCaseUC1("Champions", 1, "Calcio", 1, "campionato");
        stagione.nuovoRegolamento(20, 8, 3, 1, 0);

        stagione.confermaTorneo();

        assertNull(stagione.getTorneoCorrente());
        assertEquals(1, stagione.getElencoTornei().size());
    }


    // ********************* Test Caso d'uso UC2 - Inserisci nuova Squadra/Giocatore Singolo nel Sistema
    @Test
    public void testNuovaSquadra() {
        stagione.nuovaSquadra("Catania");

        assertNotNull(stagione.getPartecipanteCorrente());
    }

    @Test
    public void testNuovoGiocatoreSingolo() {
        stagione.nuovoGiocatoreSingolo("Mario", "Rossi", 18, "MRORSS18");

        assertNotNull(stagione.getPartecipanteCorrente());
    }

    @Test
    public void testNuovoComponente() {
        stagione.nuovaSquadra("Catania");
        stagione.nuovoComponente("Mario", "Rossi", 18, "MRORSS18");

        try {
            assertNotNull(stagione.getPartecipanteCorrente().getElencoComponenti());
            System.out.println(stagione.getPartecipanteCorrente().getElencoComponenti());
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testConfermaPartecipanteSquadra() {
        stagione.nuovaSquadra("Bari");
        stagione.nuovoComponente("Mario", "Rossi", 18, "MRORSS18");
        stagione.confermaPartecipante();

        assertNull(stagione.getPartecipanteCorrente());
        assertEquals(1, stagione.getElencoPartecipanti().size());
    }

    @Test
    public void testConfermaPartecipanteGiocatoreSingolo() {
        stagione.nuovoGiocatoreSingolo("Mario", "Rossi", 18, "MRORSS18");
        stagione.confermaPartecipante();

        assertNull(stagione.getPartecipanteCorrente());
        assertEquals(1, stagione.getElencoPartecipanti().size());
    }


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
    }

    @Test
    public void testTorneiPerSport() {
        useCaseUC3();

        Sport sport = new Sport(1, "Calcio");

        System.out.println(stagione.torneiPerSport(sport));
    }

    @Test
    public void testSelezionaTorneo() {
        useCaseUC3();

        stagione.selezionaTorneo(1);

        assertNotNull(stagione.getTorneoCorrente());
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

    @Test
    public void testConfermaIscrizioneSquadra() {
        useCaseUC3();

        stagione.selezionaTorneo(1);
        stagione.selezionaSquadra("Bari");
        stagione.confermaIscrizionePartecipante();

        assertNull(stagione.getPartecipanteCorrente());
        assertNull(stagione.getTorneoCorrente());
        assertEquals(1, stagione.getElencoTornei().get(1).getElencoPartecipanti().size());
    }

    @Test
    public void testVisualizzaGiocatoriSingoli() {
        useCaseUC3();

        stagione.selezionaTorneo(1);
        stagione.selezionaGiocatoreSingolo("MRORSS18");
        stagione.confermaIscrizionePartecipante();

        stagione.selezionaTorneo(1);
        stagione.selezionaGiocatoreSingolo("MRCBRT35");
        stagione.confermaIscrizionePartecipante();

        stagione.selezionaTorneo(1);
        System.out.println(stagione.visualizzaGiocatoriSingoli());
    }

    @Test
    public void testAccorpaGiocatoreSingolo() {
        useCaseUC3();

        stagione.selezionaTorneo(1);
        stagione.selezionaGiocatoreSingolo("MRCBRT35");
        stagione.confermaIscrizionePartecipante();

        stagione.selezionaTorneo(1);
        stagione.selezionaGiocatoreSingolo("MRORSS18");
        stagione.confermaIscrizionePartecipante();

        int currentSize = stagione.getElencoTornei().get(1).getElencoPartecipanti().size();

        stagione.selezionaTorneo(1);
        stagione.selezionaSquadra("Bari");

        stagione.visualizzaGiocatoriSingoli();
        stagione.accorpaGiocatoreSingolo("MRCBRT35");
        stagione.confermaIscrizionePartecipante();

        assertEquals(currentSize-1, stagione.getElencoTornei().get(1).getElencoPartecipanti().size());
        try {
            assertNotNull(stagione.getElencoTornei().get(1).getElencoPartecipanti().get("Bari").getElencoComponenti());
        } catch (WrongPartException e) {
            System.out.println(e.getMessage());
        }
    }

    // ********************* Test Caso d'uso UC4 - Visualizza Partecipanti
    // Metodo di inizializzazione per il caso d'uso UC4 - UC5
    private void useCaseUC4() {
        useCaseUC3();

        stagione.selezionaTorneo(1);
        stagione.selezionaSquadra("Bari");
        stagione.confermaIscrizionePartecipante();

        stagione.selezionaTorneo(1);
        stagione.selezionaGiocatoreSingolo("MRCBRT35");
        stagione.confermaIscrizionePartecipante();

        stagione.selezionaTorneo(1);
        stagione.selezionaGiocatoreSingolo("MRORSS18");
        stagione.confermaIscrizionePartecipante();
    }

    @Test
    public void testVisualizzaGiocatoreSingoli() {
        useCaseUC4();

        stagione.visualizzaPartecipanti(1);
    }


    // ********************* Test Caso d'uso UC5 - Crea il calendario di un Torneo
    @Test
    public void testCreaCalendario() {
        useCaseUC4();

        stagione.creaCalendario(1);

        assertNotNull(stagione.getElencoTornei().get(1).getCalendarioCorrente());
    }

    @Test
    public void testCreaPartita() {
        useCaseUC4();
        stagione.creaCalendario(1);

        stagione.creaPartita("Bari", "Catania");

        assertNotNull(stagione.getElencoTornei().get(1).getCalendarioCorrente().getPartitaCorrente());
    }

    @Test
    public void testSelezionaDataCampo() {
        useCaseUC4();
        stagione.creaCalendario(1);
        stagione.creaPartita("Bari", "Catania");
        Campo campo = new Campo(1, "Calcio");

        stagione.inizializzaPartita(campo, LocalDateTime.now());

        assertEquals(1, stagione.getElencoTornei().get(1).getCalendarioCorrente().getElencoPartite().size());

    }

    @Test
    public void testConfermaCalendario() {
        useCaseUC4();
        stagione.creaCalendario(1);
        stagione.creaPartita("Bari", "Catania");
        Campo campo = new Campo(1, "Calcio");
        stagione.inizializzaPartita(campo, LocalDateTime.now());

        stagione.confermaCalendario();

        assertNotNull(stagione.getElencoTornei().get(1).getCalendario());
    }


    // ********************* Test Caso d'uso UC6 - Visualizza il calendario di un Torneo
    // Metodo di inizializzazione per il caso d'uso UC6
    private void useCaseUC6() {
        useCaseUC4();
        stagione.creaCalendario(1);
        stagione.creaPartita("Bari", "Catania");
        Campo campo = new Campo(1, "Calcio");
        stagione.inizializzaPartita(campo, LocalDateTime.now());
        stagione.confermaCalendario();
    }

    @Test
    public void testVisualizzaCalendario() {
        useCaseUC6();

        System.out.println(stagione.visualizzaCalendario(1));
    }
}
