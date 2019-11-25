/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.dao.CidadeDao;
import br.edu.utfpr.dao.ClienteDao;
import br.edu.utfpr.model.Cidade;
import br.edu.utfpr.model.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class FXMLClienteCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML 
    private TextField textNome;
    @FXML
    private TextField textCpf;
    @FXML
    private TextField textRg;
    @FXML
    private TextField textNumPass;
    @FXML
    private TextField textEndereco;
    @FXML 
    private TextField textCep;
    @FXML
    private TextField textBairro;
    @FXML
    private ComboBox<Cidade> comboCidade;
    @FXML
    private TextField textTelefone;
    @FXML
    private TextField textEmail;
        
    private ClienteDao clienteDao;
    private CidadeDao cidadeDao;
    private Stage stage;
    private Cliente cliente;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        this.clienteDao = new  ClienteDao();
        this.cidadeDao = new CidadeDao();
        ObservableList<Cidade> cidade = 
                FXCollections.observableArrayList(
                            cidadeDao.getAll()
                );
        this.comboCidade.setItems(cidade);
    
    }   
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
     public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente.getId() != null) {
            textId.setText(cliente.getId().toString());
            textNome.setText(cliente.getNome());
            textCpf.setText(cliente.getCpf().toString());
            textRg.setText(cliente.getRg());
            textNumPass.setText(cliente.getNumPassaporte());
            textEndereco.setText(cliente.getEndereco());
            textCep.setText(cliente.getCep());
            textBairro.setText(cliente.getBairro());
            comboCidade.setValue(cliente.getCidade());
            textTelefone.setText(cliente.getTelefone());
            textEmail.setText(cliente.getEmail());

        }
    }
    @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        cliente.setNome(textNome.getText());
        cliente.setCpf(String.valueOf(textCpf.getText()));
        cliente.setRg(String.valueOf(textRg.getText()));
        cliente.setNumPassaporte(String.valueOf(textNumPass.getText()));
        cliente.setEndereco(textEndereco.getText());
        cliente.setCep(textCep.getText());
        cliente.setBairro(textBairro.getText());
        cliente.setCidade(comboCidade.getSelectionModel()
                        .getSelectedItem());
        cliente.setTelefone(textTelefone.getText());
        cliente.setEmail(textEmail.getText());

        this.clienteDao.save(cliente);
        this.stage.close();
    }
    
}
