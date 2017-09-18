/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaops.paginacao.controller;

import br.com.joaops.paginacao.Main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author João Paulo
 */
@Controller
public class RootLayoutController implements Initializable {
    
    private static final Logger LOGGER = LogManager.getLogger(RootLayoutController.class);
    
    private Main main;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setMain(Main main) {
        this.main = main;
    }
    
    @FXML
    private void sair() {
        System.exit(0);
    }
    
    @FXML
    private void showHomeLayout() {
        main.showHomeLayout();
    }
    
    @FXML
    private void showPessoaLayout() {
        main.showPessoaLayout();
    }
    
    @FXML
    private void sobre() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre");
        alert.setHeaderText("Aplicação de Exemplo de Paginação");
        alert.setContentText("Versão 1.0-SNAPSHOT");
        alert.showAndWait();
    }
    
}