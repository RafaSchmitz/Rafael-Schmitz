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
    private CompraProduto compraProduto;
    private ObservableList<CompraProduto> list
            = FXCollections.observableArrayList();

    private CompraProdutoDao compraProdutoDao;
    private ProdutoDao produtoDao;
    private ReservaQuartoClienteDao reservaQuartoClienteDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // this.compraProdutos = new ArrayList<>();
        this.compraProdutoDao = new CompraProdutoDao();
        this.produtoDao = new ProdutoDao();
        ObservableList<Produto> produtos
                = FXCollections.observableArrayList(
                        produtoDao.getAll()
                );
        this.comboProduto.setItems(produtos);
        compraProduto = new CompraProduto();
        setColumnProperties();
        loadData();
    }

    @FXML
    private void setValorProduto(ActionEvent event) {

        if (comboProduto.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um produto!");
        }
        Double valorTotal = this.comboProduto.getSelectionModel().
                getSelectedItem().getValor() * Double.parseDouble(textQuantidade.getText());
        textValor.setText(valorTotal.toString());

    }

    public void setCompraProdutos(List<CompraProduto> compraProdutos) {
        if (compraProdutos == null) {
            this.compraProdutos = new ArrayList<>();
        } else {
            this.compraProdutos = compraProdutos;
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

    @FXML
    private void edit() {
        compraProduto
                = tableData.getSelectionModel()
                        .getSelectedItem();
        setCompraProd();
    }

    private void setCompraProd() {
        if (compraProduto.getId() != null) {
            textId.setText(compraProduto.getId().toString());
        }
        dtCompra.setValue(compraProduto.getDate());
        comboProduto.setValue(compraProduto.getProduto());
        textQuantidade.setText(String.valueOf(compraProduto.getQuantidade()));
        textValor.setText(String.valueOf(compraProduto.getValor()));

    }

    private void loadData() {
        if (this.compraProdutos != null) {
            list.clear();
            list.addAll(compraProdutos);

            tableData.setItems(list);
        }
    }

    @FXML
    private void save() {

        compraProduto.setDate(dtCompra.getValue());
        compraProduto.setProduto(comboProduto.getSelectionModel().getSelectedItem());
        compraProduto.setQuantidade(Integer.parseInt(textQuantidade.getText()));
        compraProduto.setValor(Double.parseDouble(textValor.getText()));


        if (compraProduto.getId() == null) {
            this.compraProdutos.add(compraProduto);
        }
        compraProduto = new CompraProduto();
        loadData();

    }

}
