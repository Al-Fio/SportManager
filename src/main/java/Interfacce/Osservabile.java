package Interfacce;

public interface Osservabile {
    void attach(Osservatore osservatore);
    void detach(Osservatore osservatore);
    void notify(int operazione);
}
