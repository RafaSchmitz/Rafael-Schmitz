/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.dao.CompraProdutoDao;
import br.edu.utfpr.dao.ProdutoDao;
import br.edu.utfpr.dao.ReservaQuartoClienteDao;
import br.edu.utfpr.model.CompraProduto;
import br.edu.utfpr.model.Produto;
import br.edu.utfpr.model.ReservaQuartoCliente;
import java.net.URL;
import java.time.LocalDate;
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

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class FXMLReservaProdController implements Initializable {
    
    @FXML
    private TableView<CompraProduto> tableData;
    @FXML
    private TableColumn<CompraProduto, Long> columnId;
    @FXML
    private TableColumn<CompraProduto, LocalDate> columnDtCompra;
    @FXML
    private TableColumn<CompraProduto, Produto> columnProduto;
    @FXML
    private TableColumn<CompraProduto, LocalDate> columnQuantidade;
    @FXML
    private TableColumn<CompraProduto, LocalDate> columnValor;

    @FXML
    private TextField textId;
    @FXML
    private DatePicker dtCompra;
    @FXML
    private ComboBox<Produto> comboProduto;
    @FXML
    private TextField textQuantidade;
    @FXML
    private TextField textValor;

    private List<CompraProduto> compraProdutos;
    private ObservableList<CompraProduto> list
            = FXCollections.observableArrayList();

    private CompraProdutoDao compraProdutoDao;
    private ProdutoDao produtoDao;
    private ReservaQuartoClienteDao reservaQuartoClienteDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.compraProdutoDao = new CompraProdutoDao();

        this.produtoDao = new ProdutoDao();
        ObservableList<Produto> produtos
                = FXCollections.observableArrayList(
                        produtoDao.getAll()
                );
        this.comboProduto.setItems(produtos);
        
        setColumnProperties();
        loadData();
    }
    
    public void setCompraProdutos(List<CompraProduto> compraProdutos){
        this.compraProdutos = compraProdutos;
    }

    private void setColumnProperties() {
        columnId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        columnDtCompra.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        columnProduto.setCellValueFactory(
                new PropertyValueFactory<>("produto")
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
        list.addAll(compraProdutoDao.getAll());

        tableData.setItems(list);
    }

        @FXML
    private void edit(ActionEvent event) {
        CompraProduto compraProduto = 
                tableData.getSelectionModel()
                    .getSelectedItem();
    }
    
    @FXML
    private void save() {
        CompraProduto compraProduto = new CompraProduto(); 
        compraProduto.setDate(dtCompra.getValue());
        compraProduto.setProduto(comboProduto.getSelectionModel().getSelectedItem());
        compraProduto.setQuantidade(Integer.parseInt(textQuantidade.getText()));
        compraProduto.setValor(Double.parseDouble(textValor.getText()));
        this.compraProdutos.add(compraProduto);   
    }
    


}
