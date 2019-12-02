/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.dao.CategoriaDao;
import br.edu.utfpr.dao.ServicoDao;
import br.edu.utfpr.model.Categoria;
import br.edu.utfpr.model.Servico;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class FXMLServicoCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML 
    private TextField textNome;
    @FXML
    private TextField textValor;
    @FXML
    private ComboBox<Categoria> comboCategoria;
    @FXML
    private TextArea textDescricao;
    
    private ServicoDao servicoDao;
    private CategoriaDao categoriaDao;
    private Stage stage;
    private Servico servico;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.servicoDao = new ServicoDao();
        this.categoriaDao = new CategoriaDao();
        ObservableList<Categoria> categorias = 
                FXCollections.observableArrayList(
                        categoriaDao.getAll()
                );
        this.comboCategoria.setItems(categorias);
    }    
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setServico(Servico servico) {
        this.servico = servico;
        if (servico.getId() != null) {
            textId.setText(servico.getId().toString());
            textNome.setText(servico.getNome());
            textValor.setText(servico.getValor().toString());
            comboCategoria.setValue(servico.getCategoria());
            textDescricao.setText(servico.getDescricao());
        }
    }
    @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        servico.setNome(textNome.getText());
        servico.setValor(Double.parseDouble(
                textValor.getText()));
        servico.setDescricao(textDescricao.getText());
        servico.setCategoria(
                comboCategoria.getSelectionModel()
                        .getSelectedItem());
        this.servicoDao.save(servico);
        this.stage.close();
    }  
    
}
