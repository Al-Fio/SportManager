import Classi.Calendario;
import Classi.Campo;
import Classi.SportManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDateTime;

import static org.junit.Assert.assertNull;

public class TestCalendario {
    private static Calendario calendario;

    @Before
    public void initTest() {
        SportManager sportManager = SportManager.getInstance();

        sportManager.nuovoTorneo("WorldCup2025", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        sportManager.nuovaSquadra("Bari");
        sportManager.nuovoComponente("Paperino", "Paperinopoli", 18, "PPRPP18");
        sportManager.confermaPartecipante();

        sportManager.nuovaSquadra("Catania");
        sportManager.nuovoComponente("Pippo", "Paperinopoli", 18, "PPNPL18");
        sportManager.confermaPartecipante();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Bari");
        sportManager.confermaIscrizionePartecipante();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Catania");
        sportManager.confermaIscrizionePartecipante();

        sportManager.creaCalendario(1);
        sportManager.creaPartita("Bari", "Catania");
        sportManager.selezionaDataCampo(1, LocalDateTime.of(2025, 2, 14, 15, 00));
        sportManager.confermaCalendario();

        calendario = SportManager.getInstance().getStagione().getElencoTornei().get(1).getCalendario();
    }

    @After
    public void closeTest() {
        calendario = null;
    }


    // ********************* Test Caso d'uso UC5 - Crea il calendario di un Torneo
    @Test
    public void testEliminaPartita() {
        Campo campo = new Campo(1, "campo_calcio");

        calendario.eliminaPartita(campo, LocalDateTime.of(2025, 2, 14, 15, 00));
        calendario.selezionaPartita(campo, LocalDateTime.of(2025, 2, 14, 15, 00));
        assertNull(calendario.getPartitaCorrente());
    }
}
