/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.memento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class ZeladorImagem {
    
    private List<MementoImagem> historico;

    public ZeladorImagem() {
        historico = new ArrayList<>();
    }
    
    public MementoImagem getUltimo(){
        if(historico.isEmpty()){
            return null;
        }
        
        int last = historico.size()-1;
        return historico.remove(last);
    }
    
    public void addMemento(MementoImagem memento){
        this.historico.add(memento);
    }
    
    public boolean historicoDesfazer(){
        return !this.historico.isEmpty();
    }
}
