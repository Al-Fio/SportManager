package org.example;


import Classi.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Enum.Esito;

import static java.lang.System.exit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static SportManager sportManager = SportManager.getInstance();

    private static int menuScelta() {
        int scelta;

        do {
            System.out.println("\n".repeat(2));
            System.out.println("\t\t\t\t****** Sistema di gestione SportManager ******");
            System.out.println("\nSelezionare chi sta eseguendo le operazion1: ");
            System.out.println("1) Amministratore");
            System.out.println("2) Operatore");
            System.out.println("0) Esci");
            System.out.print("\nInserisci la scelta: ");

            scelta = scanner.nextInt();
        } while (scelta < 0 || scelta > 10);

        return scelta;
    }

    private static int menuSceltaOperatore() {
        int scelta;

        do {
            System.out.println("\n".repeat(2));
            System.out.println("\t\t\t\t****** Sistema di gestione SportManager ******");
            System.out.println("\nSeleziona l'operazione da eseguire: ");
            System.out.println("1) Inserisci nuova squadra/giocatore singolo nel sistema");
            System.out.println("2) Inserisci nuova squadra/giocatore singolo nel torneo");
            System.out.println("3) Visualizza squadre/giocatori singoli");
            System.out.println("4) Visualizza il calendario di un torneo");
            System.out.println("5) Visualizza la programmazione complessiva delle partite");
            System.out.println("6) Visualizza le statistiche di un torneo");
            System.out.println("0) Tornare alla scelta iniziale");
            System.out.print("\nInserisci la scelta: ");

            scelta = scanner.nextInt();
        } while (scelta < 0 || scelta > 10);

        return scelta;
    }

    private static void SceltaOperatore() {
        int scelta;

        while (true) {
            scelta = menuSceltaOperatore();

            switch (scelta) {
                case 1:         //Inserisci nuova squadra/giocatore singolo nel sistema
                    InserisciNuovoPartecipante();
                    break;

                case 2:         //Inserisci nuova squadra/giocatore singolo nel torneo
                    IscrizionePartecipante();
                    break;

                case 3:         //Visualizza squadre/giocatori singoli
                    VisualizzaPartecipanti();
                    break;

                case 4:         //Visualizza il calendario di un torneo
                    VisualizzaCalendario();
                    break;

                case 5:         //Visualizza la programmazione complessiva delle partite
                    VisualizzaProgrammazioneStagione();
                    break;

                case 6:         //Visualizza le statistiche di un torneo
                    VisualizzaStatisticheTorneo();
                    break;

                default:
                    return ;
            }
        }
    }

    private static int menuSceltaAmministratore() {
        int scelta;
        do {
            System.out.println("\n".repeat(2));
            System.out.println("\t\t\t\t****** Sistema di gestione SportManager ******");
            System.out.println("\nSeleziona l'operazione da eseguire: ");
            System.out.println("1) Inserisci nuovo Torneo");
            System.out.println("2) Crea il calendario di un torneo");
            System.out.println("3) Inserisci il risultato di una partita");
            System.out.println("4) Elimina Stagione");
            System.out.println("0) Tornare alla scelta iniziale");
            System.out.print("\nInserisci la scelta: ");
            scelta = scanner.nextInt();

        } while (scelta < 0 || scelta > 4);

        return scelta;
    }

    private static void SceltaAmministratore() {
        int scelta;

        while (true) {
            scelta = menuSceltaAmministratore();

            switch(scelta) {
                case 1:         //Inserisci nuovo Torneo
                    InserisciNuovoTorneo();
                    break;

                case 2:         //Crea il calendario di un torneo
                    CreaCalendario();
                    break;

                case 3:         //Inserisci il risultato di una partita
                    InserisciRisultatoPartita();
                    break;

                case 4:         //Elimina Stagione
                    EliminaStagione();
                    break;

                default: return;
            }
        }
    }

    private static void InserisciNuovoTorneo() {
        String nomeTorneo;
        float quotaIscrizione;

        int numeroSquadre, numeroMinimoGiocatoriPerSquadra, punteggioVittoria,
        punteggioPareggio, punteggioSconfitta, codiceSport, codiceModalita;

        Torneo torneo;

        System.out.println("\n\t\t*** Inserimento nuovo Torneo ***");
        System.out.print("Inserisci il nome del Torneo: ");
        nomeTorneo = scanner.next();
        System.out.print("Inserisci il codice dello sport: ");
        codiceSport = scanner.nextInt();
        System.out.print("Inserisci il codice della modalità: ");
        codiceModalita = scanner.nextInt();
        System.out.print("Inserisci la quota iscrizione: ");
        quotaIscrizione = scanner.nextFloat();

        sportManager.nuovoTorneo(nomeTorneo, codiceSport, codiceModalita, quotaIscrizione);

        System.out.println("\n");
        System.out.println("\t\t*** Inserimento nuovo Torneo ***");
        System.out.println("\t** Inserimento regolamento Torneo **");
        System.out.print("Inserisci il numero di squadre: ");
        numeroSquadre = scanner.nextInt();
        System.out.print("Inserisci il numero minimo di giocatori per squadra: ");
        numeroMinimoGiocatoriPerSquadra = scanner.nextInt();
        System.out.print("Inserisci il punteggio vittoria: ");
        punteggioVittoria = scanner.nextInt();
        System.out.print("Inserisci il punteggio pareggio: ");
        punteggioPareggio = scanner.nextInt();
        System.out.print("Inserisci il punteggio sconfitta: ");
        punteggioSconfitta = scanner.nextInt();

        torneo = sportManager.nuovoRegolamento(numeroSquadre, numeroMinimoGiocatoriPerSquadra, punteggioVittoria, punteggioPareggio, punteggioSconfitta);

        System.out.println("\n");
        System.out.println("\t\t*** Inserimento nuovo Torneo ***");
        System.out.println("\tRiepilogo dati inseriti");
        System.out.println(torneo);
        System.out.print("Conferma inserimento Torneo [y/n]: ");

        if(scanner.next().equals("y")) {
            sportManager.confermaTorneo();
            System.out.println("Torneo inserito con successo!");
        }
    }

    private static void InserisciNuovoPartecipante() {
        String nomeComponente, cognomeComponente, cf;
        int eta;

        System.out.println("\n\t\t*** Inserimento nuova Squadra/Giocatore Singolo ***");
        System.out.println("Seleziona attività: ");
        System.out.println("1) Inserimento nuova Squadra");
        System.out.println("2) Inserimento nuovo Giocatore singolo");
        System.out.print("Inserisci la scelta: ");

        switch (scanner.nextInt()) {
            case 1:
                System.out.println("\n");
                System.out.println("\t\t*** Inserimento nuova Squadra ***");
                System.out.print("Inserisci il nome della Squadra: ");
                String nomeSquadra = scanner.next();
                if(!sportManager.nuovaSquadra(nomeSquadra)) {
                    System.out.println("Squadra già presente nel sistema");
                    return;
                }

                int i = 1;
                do {
                    System.out.println("\n");
                    if (i > 1) {
                        System.out.print("Inserisci nuovo componente della Squadra [y/n]: ");
                        if (scanner.next().equals("n")) break;
                    } else System.out.println("Inserisci il primo componente della Squadra: ");

                    System.out.print("Inserisci il nome del componente: ");
                    nomeComponente = scanner.next();
                    System.out.print("Inserisci il cognome del componente: ");
                    cognomeComponente = scanner.next();
                    System.out.print("Inserisci il codice fiscale del componente: ");
                    cf = scanner.next();
                    System.out.print("Inserisci l'eta' del componente: ");
                    eta = scanner.nextInt();

                    System.out.println(sportManager.nuovoComponente(nomeComponente, cognomeComponente, eta, cf));

                    i++;
                } while (true);

                System.out.println("\n");
                System.out.println("\t\t*** Inserimento nuova Squadra ***");
                System.out.print("Conferma inserimento Squadra [y/n]: ");
                break;

            case 2:
                System.out.println("\n");
                System.out.println("\t\t*** Inserimento nuovo Giocatore singolo ***");
                System.out.print("Inserisci il nome del componente: ");
                nomeComponente = scanner.next();
                System.out.print("Inserisci il cognome del componente: ");
                cognomeComponente = scanner.next();
                System.out.print("Inserisci il codice fiscale del componente: ");
                cf = scanner.next();
                System.out.print("Inserisci l'eta' del componente: ");
                eta = scanner.nextInt();

                if(!sportManager.nuovoGiocatoreSingolo(nomeComponente, cognomeComponente, eta, cf)) {
                    System.out.println("Giocatore già presente nel sistema");
                    return;
                }

                System.out.println("\n");
                System.out.println("\t\t*** Inserimento nuovo Giocatore singolo ***");
                System.out.print("Conferma inserimento Giocatore singolo [y/n]: ");
                break;

            default:
                System.out.println("Scelta non valida!");
                return;
        }

        if(scanner.next().equals("y")) {
            sportManager.confermaPartecipante();
            System.out.println("Partecipante inserito con successo!");
        }
    }

    private static void IscrizionePartecipante() {
        int scelta;
        Torneo torneo;
        float quota;

        System.out.println("\n\t\t*** Iscrizione squadra/giocatore singolo ad un torneo ***");
        System.out.println("Seleziona attività: ");
        System.out.println("1) Iscrizione squadra");
        System.out.println("2) Iscrizione giocatore singolo");
        System.out.print("Inserisci la scelta: ");
        scelta = scanner.nextInt();

        System.out.println("\n");
        sportManager.iscrizionePartecipante();

        System.out.print("\nScegli lo sport relativo al torneo: ");
        List<Torneo> tornei = sportManager.selezionaSport(scanner.nextInt());
        if(tornei.size() == 0) {
            System.out.println("Non ci sono tornei relativi a quello sport!");
            return;
        }

        System.out.print("\nScegli il torneo: ");
        torneo = sportManager.selezionaTorneo(scanner.nextInt());

        if(torneo == null) {
            System.out.println("Torneo non trovato!");
            return;
        }

        switch (scelta) {
            case 1:
                System.out.println("\n");
                System.out.println("\t\t*** Iscrizione squadra ad un torneo ***");
                System.out.print("\nSeleziona la squadra da iscrivere: ");

                quota = sportManager.selezionaSquadra(scanner.next());
                if(quota == -1) {
                    System.out.println("Squadra non trovata!");
                    return;
                } else
                    System.out.println("Quota iscrizione: " + quota);

                if(!sportManager.squadraCompleta()) {
                    System.out.println("La squadra non ha il numero minimo di giocatori!");

                    while(!sportManager.squadraCompleta()) {
                        if(!sportManager.visualizzaGiocatoriSingoli()) {
                            System.out.println("Non è possibile inserire la squadra");
                            return;
                        }

                        System.out.println("\n");
                        System.out.print("Inserisci il codice fiscale del giocatore da iscrivere: ");
                        if(sportManager.accorpaGiocatoreSingolo(scanner.next()))
                            System.out.println("Giocatore iscritto con successo!");
                        else
                            System.out.println("Errore iscrizione giocatore singolo!");
                    }
                }

                System.out.println("\n");
                System.out.print("Conferma inserimento squadra [y/n]: ");
                break;

            case 2:
                System.out.println("\n");
                System.out.println("\t\t*** Iscrizione giocatore singolo ad un torneo ***");
                System.out.print("\nSeleziona il giocatore singolo da iscrivere: ");

                quota = sportManager.selezionaGiocatoreSingolo(scanner.next());
                if(quota == -1) {
                    System.out.println("Giocatore singolo non trovato!");
                    return;
                } else
                    System.out.println("Quota iscrizione: " + quota);

                System.out.println("\n");
                System.out.print("Conferma inserimento Giocatore singolo [y/n]: ");
                break;

            default:
                System.out.println("Scelta non valida!");
                return;
        }

        if(scanner.next().equals("y")) {
            sportManager.confermaIscrizionePartecipante();
            System.out.println("Partecipante inserito con successo!");
        }
    }

    private static void VisualizzaPartecipanti() {
        System.out.println("\n\t\t*** Visualizzazione squadre/giocatori singoli ***");
        System.out.print("Inserisci il codice del Torneo: ");

        Map<String, Partecipante> partecipanti = sportManager.visualizzaPartecipanti(scanner.nextInt());
        if(partecipanti.isEmpty())
            System.out.println("Non ci sono partecipanti iscritti al torneo");
        else if (partecipanti == null)
            System.out.println("Non è stato trovato il torneo");
        else
            System.out.println(partecipanti);
    }

    private static boolean CreaPartita() {
        String nomePartecipante1;
        String nomePartecipante2;
        int codiceCampo;
        LocalDate dataPartita;

        LocalDateTime dataOra = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.print("Inserisci il primo partecipante della partita: ");
        nomePartecipante1 = scanner.next();
        System.out.print("Inserisci il secondo partecipante della partita: ");
        nomePartecipante2 = scanner.next();

        if(!sportManager.creaPartita(nomePartecipante1, nomePartecipante2)) {
            System.out.println("Uno dei partecipanti non è iscritto al torneo");
            return false;
        }

        System.out.print("Inserisci il codice del campo: ");
        codiceCampo = scanner.nextInt();

        scanner.nextLine();
        while (dataOra == null) {
            System.out.print("Inserisci la date e l'ora della partita (formato gg/MM/yyyy HH:mm): ");
            String input = scanner.nextLine();
            try {
                dataOra = LocalDateTime.parse(input, formatter); // Converte la stringa in LocalDateTime
            } catch (DateTimeParseException e) {
                System.out.println("Formato non valido. Riprova.");
            }
        }

        if(!sportManager.selezionaDataCampo(codiceCampo, dataOra)) {
            System.out.println("Trovata sovrapposizione nelle partite");
            return false;
        }

        return true;
    }

    private static void CreaCalendario() {
        System.out.println("\n\t\t*** Creazione del calendario di un Torneo ***");

        System.out.println("Seleziona attività: ");
        System.out.println("1) Creazione calendario");
        System.out.println("2) Modifica calendario");
        System.out.print("Inserisci la scelta: ");

        switch (scanner.nextInt()) {
            case 1:
                System.out.println("\n");
                System.out.println("\t\t*** Creazione calendario ***");
                System.out.print("Inserisci il codice del Torneo: ");
                sportManager.creaCalendario(scanner.nextInt());

                do {
                    System.out.println("\n");

                    System.out.print("Inserisci nuova partita [y/n]: ");
                    if (scanner.next().equals("n")) break;
                    if(!CreaPartita()) return;
                } while (true);

                System.out.println("\n");
                System.out.println("\t\t*** Creazione calendario ***");
                System.out.print("Conferma inserimento calendario [y/n]: ");
                break;

            case 2:
                System.out.println("\n");
                System.out.println("\t\t*** Modifica calendario ***");
                System.out.print("Inserisci il codice del Torneo: ");
                sportManager.modificaCalendario(scanner.nextInt());

                System.out.println("\n");
                System.out.println("\t\t*** Modifica calendario ***");
                System.out.println("Seleziona attività: ");
                System.out.println("1) Creazione partita");
                System.out.println("2) Eliminazione partita");
                System.out.print("Inserisci la scelta: ");

                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("\n");
                        System.out.println("\t\t*** Creazione partita ***");
                        if(!CreaPartita()) return;
                        break;

                    case 2:
                        int codiceCampo;
                        LocalDate dataPartita;

                        LocalDateTime dataOra = null;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                        System.out.println("\n");
                        System.out.println("\t\t*** Eliminazione partita ***");
                        System.out.print("Inserisci il codice del campo: ");
                        codiceCampo = scanner.nextInt();

                        while (dataOra == null) {
                            System.out.print("Inserisci la date e l'ora della partita (formato gg/MM/yyyy HH:mm): ");
                            String input = scanner.nextLine();

                            try {
                                dataOra = LocalDateTime.parse(input, formatter); // Converte la stringa in LocalDateTime
                            } catch (DateTimeParseException e) {
                                System.out.println("Formato non valido. Riprova.");
                            }
                        }

                        sportManager.eliminaPartita(codiceCampo, dataOra);
                        break;

                    default:
                        System.out.println("Scelta non valida!");
                        return;
                }

                System.out.println("\n");
                System.out.println("\t\t*** Creazione partita ***");
                System.out.print("Conferma modifica calendario [y/n]: ");
                break;

            default:
                System.out.println("Scelta non valida!");
                return;
        }

        if(scanner.next().equals("y")) {
            sportManager.confermaCalendario();
            System.out.println("Calendario inserito con successo!");
        }
    }

    private static void VisualizzaCalendario() {
        System.out.println("\n\t\t*** Visualizzazione del calendario di un Torneo ***");
        System.out.print("Inserisci il codice del Torneo: ");

        Calendario calendario = sportManager.visualizzaCalendario(scanner.nextInt());
        if(calendario == null)
            System.out.println("Calendario non trovato!");
        else if(calendario.getElencoPartite().isEmpty())
            System.out.println("Calendario vuoto");
        else
            System.out.println(calendario);
    }

    private static void VisualizzaProgrammazioneStagione() {
        System.out.println("\n\t\t*** Visualizzazione programmazione complessiva della stagione ***");
        List<Partita> partite = sportManager.visualizzaProgrammazioneStagione();

        if (partite.isEmpty())
            System.out.println("Programmazione vuota");
        else if(partite == null)
            System.out.println("Non ci sono tornei nella stagione");
        else
            System.out.println(partite);
    }

    private static void InserisciRisultatoPartita() {
        int codiceCampo;
        LocalDate dataPartita;

        LocalDateTime dataOra = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        int punteggioPrimoPartecipante, punteggioSecondoPartecipante;
        Esito esitoPrimoPartecipante, esitoSecondoPartecipante;

        System.out.println("\n\t\t*** Inserisci il risultato di una partita ***");
        sportManager.inserisciRisultatoPartita();


        System.out.print("Inserisci il codice del torneo: ");
        sportManager.selezionaTorneo(scanner.nextInt());

        System.out.println("Seleziona la partita");

        System.out.print("Inserisci il codice del campo: ");
        codiceCampo = scanner.nextInt();

        scanner.nextLine();
        while (dataOra == null) {
            System.out.print("Inserisci la date e l'ora della partita (formato gg/MM/yyyy HH:mm): ");
            String input = scanner.nextLine();

            try {
                dataOra = LocalDateTime.parse(input, formatter); // Converte la stringa in LocalDateTime
            } catch (DateTimeParseException e) {
                System.out.println("Formato non valido. Riprova.");
            }
        }

        if(!sportManager.selezionaPartita(codiceCampo, dataOra)) {
            System.out.println("Partita inserita non trovata!");
            return;
        }

        System.out.println("Inserisci il risultato della partita");
        System.out.print("Inserisci il punteggio del primo partecipante: ");
        punteggioPrimoPartecipante = scanner.nextInt();
        System.out.print("Inserisci il punteggio del secondo partecipante: ");
        punteggioSecondoPartecipante = scanner.nextInt();
        System.out.println("Inserimento esito partecipanti");
        System.out.println("1) Vittoria");
        System.out.println("2) Sconfitta");
        System.out.println("3) Pareggio");
        System.out.println("4) Vittoria a tavolino");
        System.out.println("5) Sconfitta a tavolino");
        System.out.print("Inserisci l'esito del primo partecipante: ");
        switch (scanner.nextInt()) {
            case 1:
                esitoPrimoPartecipante = Esito.VITTORIA;
                break;
            case 2:
                esitoPrimoPartecipante = Esito.SCONFITTA;
                break;
            case 3:
                esitoPrimoPartecipante = Esito.PAREGGIO;
                break;
            case 4:
                esitoPrimoPartecipante = Esito.VITTORIA_A_TAVOLINO;
                break;
            default:
                esitoPrimoPartecipante = Esito.SCONFITTA_A_TAVOLINO;
                break;
        }
        System.out.print("Inserisci l'esito del secondo partecipante: ");
        switch (scanner.nextInt()) {
            case 1:
                esitoSecondoPartecipante = Esito.VITTORIA;
                break;
            case 2:
                esitoSecondoPartecipante = Esito.SCONFITTA;
                break;
            case 3:
                esitoSecondoPartecipante = Esito.PAREGGIO;
                break;
            case 4:
                esitoSecondoPartecipante = Esito.VITTORIA_A_TAVOLINO;
                break;
            default:
                esitoSecondoPartecipante = Esito.SCONFITTA_A_TAVOLINO;
                break;
        }
        sportManager.inserisciRisultato(punteggioPrimoPartecipante, punteggioSecondoPartecipante, esitoPrimoPartecipante, esitoSecondoPartecipante);

        if(sportManager.torneoASquadre()) {
            do {
                System.out.println("\n");
                System.out.println("\n\t\t*** Inserisci il risultato di una partita ***");
                System.out.print("Inserire le statistiche dei giocatori della squadra [y/n]: ");
                if(!scanner.next().equals("y"))
                    break;

                System.out.println("Inserisci il codice fiscale del giocatore: ");
                if(sportManager.selezionaGiocatorePartita(scanner.next())) {
                    System.out.print("Inserisci le statistiche del giocatore: ");
                    sportManager.inserisciStatisticheGiocatore(scanner.nextInt());
                } else
                    System.out.println("Giocatore non trovato");

            } while (true);
        }

        System.out.print("Conferma inserimento risultato [y/n]: ");
        if(scanner.next().equals("y")) {
            sportManager.confermaInserimentoStatistichePartita();
            System.out.println("Risultato inserito con successo!");
        }
    }

    private static void VisualizzaStatisticheTorneo() {
        System.out.println("\n\t\t*** Visualizzazione statistiche di un Torneo ***");
        Map<Integer, Torneo> tornei = sportManager.visualizzaStatisticheTorneo();

        if(tornei.isEmpty()) {
            System.out.println("Non ci sono tornei nella stagione");
            return;
        }

        System.out.print("Inserisci il codice del Torneo: ");
        sportManager.visualizzaClassificaTorneo(scanner.nextInt());
    }

    private static void EliminaStagione() {
        System.out.println("\n\t\t*** Eliminazione Stagione ***");
        System.out.print("Sei sicuro di voler eliminare la stagione? [y/n]: ");
        if(scanner.next().equals("y"))
            sportManager.eliminaStagione();
        else
            System.out.println("Eliminazione annullata!");
    }


    public static void main(String[] args) {
        int scelta;

        while(true) {
            scelta = menuScelta();

            switch(scelta) {
                case 1:         //Amministratore
                    SceltaAmministratore();
                    break;

                case 2:         //operatore
                    SceltaOperatore();
                    break;

                default:
                    exit(0);
            }
        }
    }
}