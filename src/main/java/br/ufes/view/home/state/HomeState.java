/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.view.home.state;

import br.ufes.view.home.HomePresenter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author gabriel
 */
public abstract class HomeState {
    
    protected HomePresenter controlador;

    public HomeState(HomePresenter controlador) {
        this.controlador = controlador;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.controlador.getView().getLblDataLogin().setText("A data do qual o login foi efetuado é : "+LocalDate.now().format(formatter));
    }
    
    
}
