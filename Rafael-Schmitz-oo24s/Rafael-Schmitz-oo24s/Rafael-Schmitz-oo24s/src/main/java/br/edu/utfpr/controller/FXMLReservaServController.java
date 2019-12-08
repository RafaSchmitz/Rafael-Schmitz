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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

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
    private CompraServico compraServico;
    private ServicoDao servicoDao;
    private ReservaQuartoClienteDao reservaQuartoClienteDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //        this.compraServicos = new ArrayList<>();
        this.compraServicoDao = new CompraServicoDao();
        this.servicoDao = new ServicoDao();
        ObservableList<Servico> servico
                = FXCollections.observableArrayList(
                        servicoDao.getAll()
                );
        this.comboServico.setItems(servico);
        compraServico = new CompraServico();
        setColumnProperties();
        loadData();
    }

    @FXML
    private void setValorServico(ActionEvent event) {

        if (comboServico.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Servico!");
        }
        Double valorTotal = this.comboServico.getSelectionModel().
                getSelectedItem().getValor() * Double.parseDouble(textQuantidade.getText());
        textValor.setText(valorTotal.toString());

    }
    
    public void setCompraServicos(List<CompraServico> compraServicos) {
        if (compraServicos == null) {
            this.compraServicos = new ArrayList<>();
        } else {
            this.compraServicos = compraServicos;
        }
        loadData();
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

    @FXML
    private void edit() {
        compraServico
                = tableData.getSelectionModel()
                        .getSelectedItem();
        setCompraServ();
    }

    private void setCompraServ() {
        if (compraServico.getId() != null) {
            textId.setText(compraServico.getId().toString());
        }
        dtCompra.setValue(compraServico.getDate());
        comboServico.setValue(compraServico.getServico());
        textQuantidade.setText(String.valueOf(compraServico.getQuantidade()));
        textValor.setText(String.valueOf(compraServico.getValor()));

    }

    private void loadData() {
        if (this.compraServicos != null) {
            list.clear();
            list.addAll(compraServicos);

            tableData.setItems(list);
        }
    }

    @FXML
    private void save() {

        compraServico.setDate(dtCompra.getValue());
        compraServico.setServico(comboServico.getSelectionModel().getSelectedItem());
        compraServico.setQuantidade(Integer.parseInt(textQuantidade.getText()));
        compraServico.setValor(Double.parseDouble(textValor.getText()));
        
        if(compraServico.getId() == null){
             this.compraServicos.add(compraServico);
        }
        compraServico = new CompraServico();
 
        loadData();
    }

}
