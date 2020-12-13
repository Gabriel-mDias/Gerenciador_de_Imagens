package br.ufes.observable;

import java.util.ArrayList;
import java.util.List;

public abstract class Observado {

    private List<Observador> observadores;

    public Observado() {
        this.observadores = new ArrayList<>();
    }

    public void addObservador(Observador o) {
        this.observadores.add(o);
    }

    public void removerObservador(Observador o) {
        this.observadores.remove(o);
    }

    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.update();
        }
    }

}
