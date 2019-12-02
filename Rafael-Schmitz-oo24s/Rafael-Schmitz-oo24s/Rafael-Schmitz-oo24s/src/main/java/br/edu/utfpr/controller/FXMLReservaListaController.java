/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.dao.ReservaQuartoClienteDao;
import br.edu.utfpr.model.ReservaQuartoCliente;
import br.edu.utfpr.model.Servico;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
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
public class FXMLReservaListaController implements Initializable {

    @FXML
    private TableView<ReservaQuartoCliente> tableData;
    @FXML
    private TableColumn<ReservaQuartoCliente, Long> columnId;
    @FXML
    private TableColumn<ReservaQuartoCliente, String> columnCliente;
    @FXML
    private TableColumn<ReservaQuartoCliente, String> columnQuarto;
    @FXML
    private TableColumn<ReservaQuartoCliente, LocalDate> columnCheckIn;
    @FXML
    private TableColumn<ReservaQuartoCliente, LocalDate> columnCheckOut;
    @FXML
    private TableColumn<ReservaQuartoCliente, Double> columnDiaria;
      
    private ReservaQuartoClienteDao reservaQuartoClienteDao;
    private ObservableList<ReservaQuartoCliente> list = 
            FXCollections.observableArrayList();
    
    
    public void initialize(URL url, ResourceBundle rb) {
        this.reservaQuartoClienteDao = new ReservaQuartoClienteDao();
        setColumnProperties();
        loadData();
    } 
    
     private void setColumnProperties() {
        columnId.setCellValueFactory(
          new PropertyValueFactory<>("id")
        );
        columnCliente.setCellValueFactory(
          new PropertyValueFactory<>("cliente")
        );
        columnQuarto.setCellValueFactory(
          new PropertyValueFactory<>("quarto")
        );
        columnCheckIn.setCellValueFactory(
          new PropertyValueFactory<>("dtIni")
        );
        columnCheckOut.setCellValueFactory(
          new PropertyValueFactory<>("dtFim")
        );
        columnDiaria.setCellValueFactory(
          new PropertyValueFactory<>("vlrDiaria")
        );
        
    }

    private void loadData() {
        list.clear();
        list.addAll(reservaQuartoClienteDao.getAll());
        
        tableData.setItems(list);
    }
    
    private void openForm(
            ReservaQuartoCliente reservaQuartoCliente, 
            ActionEvent event) {
        try {
            // Carregar o arquivo fxml e cria um
            //novo stage para a janela Modal
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                this.getClass()
                    .getResource("/fxml/FXMLReservaCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            
            //Criando o stage para o modal
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Reservas");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) event.getSource())
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            FXMLReservaCadastroController controller = 
                    loader.getController();
            controller.setReserva(reservaQuartoCliente);
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
        ReservaQuartoCliente reservaQuartoCliente = 
                tableData.getSelectionModel()
                    .getSelectedItem();
        this.openForm(reservaQuartoCliente, event);
    }
    
    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new ReservaQuartoCliente(), event);
    }
    
    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel()
                .getSelectedIndex() >=0) {
            try {
                ReservaQuartoCliente reservaQuartoCliente =  tableData
                        .getSelectionModel().getSelectedItem();
                reservaQuartoClienteDao.delete(reservaQuartoCliente.getId());
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
