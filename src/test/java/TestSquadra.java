import Classi.*;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class TestSquadra {
    static Squadra squadra;

    @BeforeClass
    public static void initTest() {
        squadra = new Squadra("Italia");
    }

    @Test
    public void testAggiungiComponente() {
        squadra.aggiungiComponente("MRORSS18", new GiocatoreSingolo("Mario", "Rossi", 18, "MRORSS18"));
        assertNotNull(squadra.getElencoComponenti());

        for(String key : squadra.getElencoComponenti().keySet()) {
            System.out.println(squadra.getElencoComponenti().get(key).toString());
        }
    }

}
