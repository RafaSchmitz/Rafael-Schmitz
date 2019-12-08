/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import br.edu.ufpr.db.DatabaseConnection;
import br.edu.utfpr.dao.ClienteDao;
import br.edu.utfpr.dao.QuartoDao;
import br.edu.utfpr.dao.ReservaQuartoClienteDao;
import br.edu.utfpr.model.Cliente;
import br.edu.utfpr.model.CompraProduto;
import br.edu.utfpr.model.CompraServico;
import br.edu.utfpr.model.Quarto;
import br.edu.utfpr.model.ReservaQuartoCliente;
import br.edu.utfpr.report.GenerateReport;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class FXMLReservaCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML
    private ComboBox<Cliente> comboClientes;
    @FXML
    private ComboBox<Quarto> comboQuarto;
    @FXML
    private DatePicker dtReserva;
    @FXML
    private DatePicker dtCheckIn;
    @FXML
    private DatePicker dtCheckOut;
    @FXML
    private TextField txtDiaria;
    @FXML
    private TextField txtHospedes;
    @FXML
    private TextArea taMotivo;
    @FXML
    private VBox boxVendas;

    private ReservaQuartoClienteDao reservaQuartoClienteDao;
    private ClienteDao clienteDao;
    private QuartoDao quartoDao;
    private Stage stage;
    private ReservaQuartoCliente reservaQuartoCliente;
    private List<CompraProduto> compraProdutos;
    private List<CompraServico> compraServicos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.reservaQuartoClienteDao = new ReservaQuartoClienteDao();
        this.clienteDao = new ClienteDao();
        this.quartoDao = new QuartoDao();
        ObservableList<Cliente> clientes
                = FXCollections.observableArrayList(
                        clienteDao.getAll()
                );
        this.comboClientes.setItems(clientes);

        ObservableList<Quarto> quartos
                = FXCollections.observableArrayList(
                        quartoDao.getAll()
                );
        this.comboQuarto.setItems(quartos);
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    public void setDataPane(Node node) {
        boxVendas.getChildren().setAll(node);
    }

    public VBox openVBox(String url) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
                this.getClass()
                        .getResource(url));
        VBox v = (VBox) loader.load();
        FadeTransition ft = new FadeTransition(
                Duration.millis(1000));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        FXMLReservaProdController reservaProdController = loader.getController();

        reservaProdController.setCompraProdutos(compraProdutos);

        ft.play();
        return v;
    }

    public VBox openVBoxServ(String url) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
                this.getClass()
                        .getResource(url));
        VBox v = (VBox) loader.load();
        FadeTransition ft = new FadeTransition(
                Duration.millis(1000));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        FXMLReservaServController reservaServController = loader.getController();

        reservaServController.setCompraServicos(compraServicos);

        ft.play();
        return v;
    }

    @FXML
    public void loadVendaProd(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLReservaProdServ.fxml"
        ));
    }

    @FXML
    public void loadVendaServ(ActionEvent event)
            throws IOException {
        setDataPane(openVBoxServ(
                "/fxml/FXMLReservaServ.fxml"
        ));
    }

    @FXML
    private void setHospedes(ActionEvent event) {

        if (comboQuarto.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um Quarto!");
        }
        Integer hospedes = this.comboQuarto.getSelectionModel().
                getSelectedItem().getQtdPessoas();
        txtHospedes.setText(hospedes.toString());

    }

    public void setReserva(ReservaQuartoCliente reservaQuartoCliente) {
        this.reservaQuartoCliente = reservaQuartoCliente;
        if (reservaQuartoCliente.getId() != null) {
            textId.setText(reservaQuartoCliente.getId().toString());
            comboClientes.setValue(reservaQuartoCliente.getCliente());
            comboQuarto.setValue(reservaQuartoCliente.getQuarto());
            dtReserva.setValue(reservaQuartoCliente.getDtReserva());
            dtCheckIn.setValue(reservaQuartoCliente.getDtIni());
            dtCheckOut.setValue(reservaQuartoCliente.getDtFim());
            txtDiaria.setText(String.valueOf(reservaQuartoCliente.getVlrDiaria()));
            txtHospedes.setText(reservaQuartoCliente.getHospedes().toString());
            taMotivo.setText(reservaQuartoCliente.getMotivo());
            compraProdutos = reservaQuartoCliente.getCompraProdutos();
            compraServicos = reservaQuartoCliente.getCompraServicos();
        }
    }

    @FXML
    private void cancel() {
        this.stage.close();
    }

    @FXML
    private void save() {

        if (reservaQuartoCliente.getId() != null) {
            compraProdutos.forEach(cp -> cp.setReservaQuartoCliente(reservaQuartoCliente));
            compraServicos.forEach(cs -> cs.setReservaQuartoCliente(reservaQuartoCliente));
        }

        reservaQuartoCliente.setCompraProdutos(compraProdutos);

        reservaQuartoCliente.setCompraServicos(compraServicos);

        reservaQuartoCliente.setCliente(comboClientes.getSelectionModel()
                .getSelectedItem());
        reservaQuartoCliente.setQuarto(comboQuarto.getSelectionModel()
                .getSelectedItem());
        reservaQuartoCliente.setDtReserva(dtReserva.getValue());
        reservaQuartoCliente.setDtIni(dtCheckIn.getValue());
        reservaQuartoCliente.setDtFim(dtCheckOut.getValue());
        reservaQuartoCliente.setVlrDiaria(Double.parseDouble(txtDiaria.getText()));
        reservaQuartoCliente.setMotivo(taMotivo.getText());
        reservaQuartoCliente.setHospedes(Integer.parseInt(txtHospedes.getText()));

        this.reservaQuartoClienteDao.save(reservaQuartoCliente);
        this.stage.close();
    }

}
