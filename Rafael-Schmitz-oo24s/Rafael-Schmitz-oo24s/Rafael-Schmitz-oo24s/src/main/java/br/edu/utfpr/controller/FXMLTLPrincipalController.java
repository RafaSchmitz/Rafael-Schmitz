/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class FXMLTLPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private VBox boxPrincipal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setDataPane(Node node) {
        /* Atualiza o VBox (boxPrincipal) com um 
            novo form (FXML) dependendo do 
            item de menu ou botão acionado
         */
        boxPrincipal.getChildren().setAll(node);
    }
    
    
    public VBox openVBox(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(
                this.getClass().getResource(url));
        FadeTransition ft = new FadeTransition(
                Duration.millis(1000));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }
    
    @FXML
    public void loadProduto(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLProdutoLista.fxml"
        ));
    }
    
    @FXML
    public void loadServico(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLServicoLista.fxml"
        ));
    }
    @FXML
    public void loadCliente(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLClienteLista.fxml"
        ));
    }
    
}
