/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.dao.CompraServicoDao;
import br.edu.utfpr.dao.ReservaQuartoClienteDao;
import br.edu.utfpr.dao.ServicoDao;
import br.edu.utfpr.model.CompraServico;
import br.edu.utfpr.model.Servico;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class FXMLReservaServController implements Initializable {

        @FXML
    private TableView<CompraServico> tableData;
    @FXML
    private TableColumn<CompraServico, Long> columnId;
    @FXML
    private TableColumn<CompraServico, LocalDate> columnDtCompra;
    @FXML
    private TableColumn<CompraServico, Servico> columnServico;
    @FXML
    private TableColumn<CompraServico, LocalDate> columnQuantidade;
    @FXML
    private TableColumn<CompraServico, LocalDate> columnValor;

    @FXML
    private TextField textId;
    @FXML
    private DatePicker dtCompra;
    @FXML
    private ComboBox<Servico> comboServico;
    @FXML
    private TextField textQuantidade;
    @FXML
    private TextField textValor;

    private List<CompraServico> compraServicos;
    private ObservableList<CompraServico> list
            = FXCollections.observableArrayList();

    private CompraServicoDao compraServicoDao;
    private ServicoDao servicoDao;
    private ReservaQuartoClienteDao reservaQuartoClienteDao;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.compraServicoDao = new CompraServicoDao();

        this.servicoDao = new ServicoDao();
        ObservableList<Servico> produtos
                = FXCollections.observableArrayList(
                        servicoDao.getAll()
                );
        this.comboServico.setItems(produtos);
        
        setColumnProperties();
        loadData();
    }   
    
      public void setCompraServicos(List<CompraServico> compraServicos){
        this.compraServicos = compraServicos;
    }
    
     private void setColumnProperties() {
        columnId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        columnDtCompra.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        columnServico.setCellValueFactory(
                new PropertyValueFactory<>("servico")
        );
        columnQuantidade.setCellValueFactory(
                new PropertyValueFactory<>("quantidade")
        );
        columnValor.setCellValueFactory(
                new PropertyValueFactory<>("valor")
        );

    }

    private void loadData() {
        list.clear();
        list.addAll(compraServicoDao.getAll());

        tableData.setItems(list);
    }

    @FXML
    private void save() {
        CompraServico compraServico = new CompraServico(); 
        compraServico.setDate(dtCompra.getValue());
        compraServico.setServico(comboServico.getSelectionModel().getSelectedItem());
        compraServico.setQuantidade(Integer.parseInt(textQuantidade.getText()));
        compraServico.setValor(Double.parseDouble(textValor.getText()));
        this.compraServicos.add(compraServico);   
    }
    
}
