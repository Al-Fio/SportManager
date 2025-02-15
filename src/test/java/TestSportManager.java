import Classi.Campo;
import Classi.SportManager;

import Classi.Stagione;
import Eccezioni.WrongPartException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


import java.time.LocalDateTime;
import Enum.Esito;

import static org.junit.Assert.*;

public class TestSportManager {
    static SportManager sportManager;

    @BeforeClass
    public static void initTest() {
        sportManager = SportManager.getInstance();
    }

    @After
    public void clearTest(){
        Stagione stagione = sportManager.getStagione();
        stagione.getElencoTornei().clear();
        stagione.getElencoPartecipanti().clear();
    }


    // ********************* Test Caso d'uso UC1 - Inserisci nuovo Torneo
    @Test
    public void testNuovoTorneo() {
        sportManager.nuovoTorneo("WorldCup", 1, 1, 150);
        assertNotNull(sportManager.getStagione().getTorneoCorrente());
    }

    @Test
    public void testNuovoRegolamento() {
        sportManager.nuovoTorneo("WorldCup", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);

        assertNotNull(sportManager.getStagione().getTorneoCorrente().getRegolamento());
    }

    @Test
    public void testConfermaTorneo() {
        sportManager.nuovoTorneo("WorldCup", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        assertNull(sportManager.getStagione().getTorneoCorrente());
        assertEquals(1, sportManager.getStagione().getElencoTornei().size());
    }


    // ********************* Test Caso d'uso UC2 - Inserisci nuova Squadra/Giocatore Singolo nel Sistema
    @Test
    public void testNuovaSquadra() {
        sportManager.nuovaSquadra("Bari");

        assertNotNull(sportManager.getStagione().getPartecipanteCorrente());
        System.out.println(sportManager.getStagione().getPartecipanteCorrente());
    }

    @Test
    public void testNuovoComponente() {
        sportManager.nuovaSquadra("Bari");
        sportManager.nuovoComponente("Mario", "Rossi", 18, "MRORSS18");

        try {
            assertNotNull(sportManager.getStagione().getPartecipanteCorrente().getElencoComponenti());
            System.out.println(sportManager.getStagione().getPartecipanteCorrente().getElencoComponenti());
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testConfermaPartecipanteSquadra() {
        sportManager.nuovaSquadra("Bari");
        sportManager.nuovoComponente("Mario", "Rossi", 18, "MRORSS18");
        sportManager.confermaPartecipante();

        assertNull(sportManager.getStagione().getPartecipanteCorrente());
        assertEquals(1, sportManager.getStagione().getElencoPartecipanti().size());
    }

    @Test
    public void testNuovoGiocatoreSingolo() {
        sportManager.nuovoGiocatoreSingolo("Mario", "Rossi", 18, "MRORSS18");

        assertNotNull(sportManager.getStagione().getPartecipanteCorrente());
        System.out.println(sportManager.getStagione().getPartecipanteCorrente());
    }

    @Test
    public void testConfermaPartecipanteGiocatoreSingolo() {
        sportManager.nuovoGiocatoreSingolo("Mario", "Rossi", 18, "MRORSS18");
        sportManager.confermaPartecipante();

        assertNull(sportManager.getStagione().getPartecipanteCorrente());
        assertEquals(1, sportManager.getStagione().getElencoPartecipanti().size());
    }


    // ********************* Test Caso d'uso UC3 - Inserisci nuova Squadra/Giocatore Singolo nel Torneo
    // Metodo di inizializzazione per il caso d'uso UC3
    private void useCaseUC3() {
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

    @Test
    public void testIscrizioneSquadra() {
        sportManager.iscrizionePartecipante();

    }

    @Test
    public void testSelezionaSport() {
        useCaseUC3();

        sportManager.selezionaSport(1);
    }

    @Test
    public void testSelezionaTorneo() {
        useCaseUC3();

        sportManager.selezionaTorneo(1);

        assertNotNull(sportManager.getStagione().getTorneoCorrente());
    }

    @Test
    public void testSelezionaSquadra() {
        useCaseUC3();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Bari");

        assertNotNull(sportManager.getStagione().getPartecipanteCorrente());
    }

    @Test
    public void testSelezionaGiocatoreSingolo() {
        useCaseUC3();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaGiocatoreSingolo("MRORSS18");

        assertNotNull(sportManager.getStagione().getPartecipanteCorrente());
    }

    @Test
    public void testConfermaIscrizionePartecipante() {
        useCaseUC3();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Bari");
        sportManager.confermaIscrizionePartecipante();

        assertNull(sportManager.getStagione().getTorneoCorrente());
        assertEquals(1, sportManager.getStagione().getElencoTornei().get(1).getElencoPartecipanti().size());
    }

    @Test
    public void testVisualizzaGiocatoriSingoli() {
        useCaseUC3();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Bari");
        sportManager.visualizzaGiocatoriSingoli();
    }

    @Test
    public void testAccorpaGiocatoreSingolo() {
        useCaseUC3();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaGiocatoreSingolo("MRORSS18");
        sportManager.confermaIscrizionePartecipante();

        sportManager.iscrizionePartecipante();
        sportManager.selezionaSport(1);
        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Bari");
        sportManager.visualizzaGiocatoriSingoli();
        sportManager.accorpaGiocatoreSingolo("MRORSS18");
        sportManager.confermaIscrizionePartecipante();
    }


    // ********************* Test Caso d'uso UC4 - Visualizza Partecipanti
    // Metodo di inizializzazione per il caso d'uso UC4 - UC5
    private void useCaseUC4() {
        useCaseUC3();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaGiocatoreSingolo("MRORSS18");
        sportManager.confermaIscrizionePartecipante();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaGiocatoreSingolo("MRCBRT35");
        sportManager.confermaIscrizionePartecipante();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Bari");
        sportManager.visualizzaGiocatoriSingoli();
        sportManager.confermaIscrizionePartecipante();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Catania");
        sportManager.confermaIscrizionePartecipante();
    }


    // ********************* Test Caso d'uso UC5 - Crea il calendario di un Torneo
    @Test
    public void testCreaCalendario() {
        useCaseUC4();

        sportManager.creaCalendario(1);

        assertNotNull(sportManager.getStagione().getElencoTornei().get(1).getCalendarioCorrente());
    }

    @Test
    public void testCreaPartita() {
        useCaseUC4();
        sportManager.creaCalendario(1);

        sportManager.creaPartita("Bari", "Catania");

        assertNotNull(sportManager.getStagione().getElencoTornei().get(1).getCalendarioCorrente().getPartitaCorrente());
    }

    @Test
    public void testSelezionaDataCampo() {
        useCaseUC4();
        sportManager.creaCalendario(1);
        sportManager.creaPartita("Bari", "Catania");

        sportManager.selezionaDataCampo(1, LocalDateTime.now());

        assertEquals(1, sportManager.getStagione().getElencoTornei().get(1).getCalendarioCorrente().getElencoPartite().size());
    }

    @Test
    public void testConfermaCalendario() {
        useCaseUC4();
        sportManager.creaCalendario(1);
        sportManager.creaPartita("Bari", "Catania");
        sportManager.selezionaDataCampo(1, LocalDateTime.now());

        sportManager.confermaCalendario();

        assertNotNull(sportManager.getStagione().getElencoTornei().get(1).getCalendario());
    }

    @Test
    public void testModificaCalendario() {
        useCaseUC4();
        sportManager.creaCalendario(1);
        sportManager.creaPartita("Bari", "Catania");
        sportManager.selezionaDataCampo(1, LocalDateTime.of(2025, 2, 14, 15, 00));
        sportManager.confermaCalendario();

        sportManager.modificaCalendario(1);

        assertNotNull(sportManager.getStagione().getElencoTornei().get(1).getCalendarioCorrente());
    }

    @Test
    public void testEliminaPartita() {
        useCaseUC4();
        sportManager.creaCalendario(1);
        sportManager.creaPartita("Bari", "Catania");
        sportManager.selezionaDataCampo(1, LocalDateTime.of(2025, 2, 14, 15, 00));
        sportManager.confermaCalendario();
        sportManager.modificaCalendario(1);

        sportManager.eliminaPartita(1, LocalDateTime.of(2025, 2, 14, 15, 00));

        sportManager.selezionaPartita(1, LocalDateTime.of(2025, 2, 14, 15, 00));
        assertNull(sportManager.getStagione().getElencoTornei().get(1).getCalendarioCorrente().getPartitaCorrente());
    }


    // ********************* Test Caso d'uso UC8 - Inserisci i risultati di una Partita
    // Metodo di inizializzazione per il caso d'uso UC8
    private void useCaseUC8() {
        useCaseUC4();

        sportManager.selezionaTorneo(2);
        sportManager.selezionaSquadra("Bari");
        sportManager.confermaIscrizionePartecipante();

        sportManager.selezionaTorneo(2);
        sportManager.selezionaSquadra("Catania");
        sportManager.confermaIscrizionePartecipante();

        sportManager.creaCalendario(1);
        sportManager.creaPartita("Bari", "Catania");
        sportManager.selezionaDataCampo(1, LocalDateTime.of(2025, 2, 14, 15, 00));
        sportManager.confermaCalendario();

        sportManager.creaCalendario(2);
        sportManager.creaPartita("Catania", "Bari");
        sportManager.selezionaDataCampo(1, LocalDateTime.of(2025, 2, 15, 15, 00));
        sportManager.confermaCalendario();
    }

    @Test
    public void testSelezionaPartita() {
        useCaseUC8();
        sportManager.selezionaTorneo(1);

        sportManager.selezionaPartita(1, LocalDateTime.of(2025, 2, 14, 15, 00));

        assertNotNull(sportManager.getStagione().getElencoTornei().get(1).getCalendario().getPartitaCorrente());
    }

    @Test
    public void testInserisciRisultato() {
        useCaseUC8();
        sportManager.selezionaTorneo(1);
        sportManager.selezionaPartita(1, LocalDateTime.of(2025, 2, 14, 15, 00));

        sportManager.inserisciRisultato(3, 1, Esito.VITTORIA, Esito.SCONFITTA);

        sportManager.visualizzaCalendario(1);
    }


    // ********************* Test Caso d'uso UC9 - Inserisci Statistiche di un Giocatore
    @Test
    public void testSelezionaGiocatorePartita() {
        useCaseUC8();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaPartita(1, LocalDateTime.of(2025, 2, 14, 15, 00));
        sportManager.inserisciRisultato(3, 1, Esito.VITTORIA, Esito.SCONFITTA);

        sportManager.selezionaGiocatorePartita("PPRPP18");
        assertNotNull(sportManager.getStagione().getElencoTornei().get(1).getPartecipanteCorrente());
    }

    @Test
    public void testInserisciStatisticheGiocatore() {
        useCaseUC8();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaPartita(1, LocalDateTime.of(2025, 2, 14, 15, 00));
        sportManager.inserisciRisultato(3, 1, Esito.VITTORIA, Esito.SCONFITTA);

        sportManager.selezionaGiocatorePartita("MRORSS18");

        sportManager.inserisciStatisticheGiocatore(10);

        sportManager.visualizzaClassificaTorneo(1);
    }

    @Test
    public void testConfermaInserimentoStatistichePartita() {
        useCaseUC8();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaPartita(1, LocalDateTime.of(2025, 2, 14, 15, 00));
        sportManager.inserisciRisultato(3, 1, Esito.VITTORIA, Esito.SCONFITTA);
        sportManager.selezionaGiocatorePartita("MRORSS18");
        sportManager.inserisciStatisticheGiocatore(10);

        sportManager.confermaInserimentoStatistichePartita();

        sportManager.visualizzaClassificaTorneo(1);
    }

}
