/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaops.paginacao.controller;

import br.com.joaops.paginacao.Main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;

/**
 * 
 * @author João Paulo
 */
@Controller
public class HomeLayoutController implements Initializable {
    
    private static final Logger LOGGER = LogManager.getLogger(HomeLayoutController.class);
    
    private Main main;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setMain(Main main) {
        this.main = main;
    }
    
}