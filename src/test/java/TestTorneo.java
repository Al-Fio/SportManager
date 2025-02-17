import Classi.*;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TestTorneo {
    private static Torneo torneo;
    private static SportManager sportManager;

    @Before
    public void initTest() {
        sportManager = SportManager.getInstance();

        // Creazione tornei
        sportManager.nuovoTorneo("WorldCup2025", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        sportManager.nuovoTorneo("WorldCup2026", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        sportManager.nuovoTorneo("WorldCup", 2, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        // Creazione partecipanti su Stagione
        sportManager.nuovoGiocatoreSingolo("Mario", "Rossi", 18, "MRORSS18");
        sportManager.confermaPartecipante();

        sportManager.nuovoGiocatoreSingolo("Marco", "Berti", 35, "MRCBRT35");
        sportManager.confermaPartecipante();

        sportManager.nuovoGiocatoreSingolo("Marcello", "Storti", 45, "MRCSTR45");
        sportManager.confermaPartecipante();

        sportManager.nuovaSquadra("Bari");
        sportManager.nuovoComponente("Paperino", "Paperinopoli", 18, "PPRPP18");
        sportManager.confermaPartecipante();

        sportManager.nuovaSquadra("Catania");
        sportManager.nuovoComponente("Pippo", "Paperinopoli", 18, "PPNPL18");
        sportManager.confermaPartecipante();
    }

    @After
    public void clearTest() {
        torneo = null;

        Stagione stagione = sportManager.getStagione();
        stagione.getElencoTornei().clear();
        stagione.getElencoPartecipanti().clear();
    }


    // ********************* Caso d'uso UC3 - Inserisci nuova Squadra/Giocatore Singolo nel Torneo
    @Test
    public void testVerificaUnicitaPartecipanteTorneo() {
        torneo = sportManager.getStagione().getElencoTornei().get(1);
        sportManager.selezionaTorneo(1);
        sportManager.selezionaGiocatoreSingolo("MRORSS18");
        sportManager.confermaIscrizionePartecipante();

        assertEquals(false, torneo.verificaUnicitaPartecipanteTorneo("PPRPP18"));
        assertEquals(true, torneo.verificaUnicitaPartecipanteTorneo("MRORSS18"));
    }

    @Test
    public void testVisualizzaGiocatoriSingoli() {
        torneo = sportManager.getStagione().getElencoTornei().get(1);

        sportManager.selezionaTorneo(1);
        sportManager.selezionaGiocatoreSingolo("MRORSS18");
        sportManager.confermaIscrizionePartecipante();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaGiocatoreSingolo("MRCBRT35");
        sportManager.confermaIscrizionePartecipante();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Bari");

        System.out.println(torneo.visualizzaGiocatoriSingoli());
    }

    // ********************* Test Caso d'uso UC5 - Crea il calendario di un Torneo
}
