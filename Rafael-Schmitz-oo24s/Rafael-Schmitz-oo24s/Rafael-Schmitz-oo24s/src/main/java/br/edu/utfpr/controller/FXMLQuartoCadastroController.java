/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.dao.QuartoDao;
import br.edu.utfpr.model.Quarto;
import br.edu.utfpr.model.TipoQuarto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class FXMLQuartoCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML
    private TextField textNumero;
     @FXML
    private ComboBox<TipoQuarto> comboTipoQuarto;
    @FXML
    private TextField textCamas;
    @FXML 
    private TextField textPessoas;
    
    
    private QuartoDao quartoDao;
    private Stage stage;
    private Quarto quarto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.quartoDao = new QuartoDao();
        comboTipoQuarto.setItems(FXCollections.observableArrayList( TipoQuarto.values()));
    }  
    
     public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
     public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
        if (quarto.getId() != null) {
            textId.setText(quarto.getId().toString());
            textNumero.setText(String.valueOf(quarto.getNumQuarto()));
            comboTipoQuarto.setValue(quarto.getTipoQuarto());
            textCamas.setText(String.valueOf(quarto.getQtdCamas()));
            textPessoas.setText(String.valueOf(quarto.getQtdPessoas()));
        }
    }
    @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        quarto.setNumQuarto(Integer.parseInt(textNumero.getText()));
        quarto.setTipoQuarto(comboTipoQuarto.getSelectionModel()
                        .getSelectedItem());
        quarto.setQtdCamas(Integer.parseInt(textCamas.getText()));
        quarto.setQtdPessoas(Integer.parseInt(textPessoas.getText()));

        this.quartoDao.save(quarto);
        this.stage.close();
    }
    
}

