/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.dao.ServicoDao;
import br.edu.utfpr.model.Categoria;
import br.edu.utfpr.model.Produto;
import br.edu.utfpr.model.Servico;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class FXMLServicoListaController implements Initializable {

    @FXML
    private TableView<Servico> tableData;
    @FXML
    private TableColumn<Servico, Long> columnId;
    @FXML
    private TableColumn<Servico, String> columnNome;
    
    @FXML
    private TableColumn<Servico, String> columnDesc;
    
    @FXML
    private TableColumn<Servico, Categoria> columnCat;
    
    private ServicoDao servicoDao;
    private ObservableList<Servico> list = 
            FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.servicoDao = new ServicoDao();
        setColumnProperties();
        loadData();
    }    

    private void setColumnProperties() {
        columnId.setCellValueFactory(
          new PropertyValueFactory<>("id")
        );
        columnNome.setCellValueFactory(
          new PropertyValueFactory<>("nome")
        );
        columnDesc.setCellValueFactory(
          new PropertyValueFactory<>("descricao")
        );
        columnCat.setCellValueFactory(
          new PropertyValueFactory<>("categoria")
        );
        
    }

    private void loadData() {
        list.clear();
        list.addAll(servicoDao.getAll());
        
        tableData.setItems(list);
    }
    
    private void openForm(
            Servico servico, 
            ActionEvent event) {
        try {
            // Carregar o arquivo fxml e cria um
            //novo stage para a janela Modal
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                this.getClass()
                    .getResource("/fxml/FXMLProdutoCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            
            //Criando o stage para o modal
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Serviços");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) event.getSource())
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            FXMLServicoCadastroController controller = 
                    loader.getController();
            controller.setProduto(servico);
            controller.setDialogStage(dialogStage);
            // Exibe a janela Modal e espera até o usuário
            //fechar
            dialogStage.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro ao abrir "
                    + "a janela de cadastro!");
            alert.setContentText("Por favor, tente realizar "
                    + "a operação novamente!");
            alert.showAndWait();
        }
        loadData();
    }
    
    @FXML
    private void edit(ActionEvent event) {
        Servico servico = 
                tableData.getSelectionModel()
                    .getSelectedItem();
        this.openForm(servico, event);
    }
    
    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Servico(), event);
    }
    
    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel()
                .getSelectedIndex() >=0) {
            try {
                Servico servico =  tableData
                        .getSelectionModel().getSelectedItem();
                servicoDao.delete(servico.getId());
                tableData.getItems().remove(
                        tableData.getSelectionModel()
                                    .getSelectedIndex());
                        
                       
                
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro "
                    + " ao remover o registro!");
            alert.setContentText("Por favor, tente realizar "
                    + "a operação novamente!");
            alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro "
                    + "selecionado");
            alert.setContentText("Por favor, "
                    + "selecione um registro "
                    + "na tabela!");
            alert.showAndWait();
        }
    }
    
}
