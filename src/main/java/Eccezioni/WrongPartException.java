package Eccezioni;

public class WrongPartException extends Exception {
    public WrongPartException() {
        super("Metodo non supportato");
    }
}
