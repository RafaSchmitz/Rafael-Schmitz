/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import br.edu.ufpr.db.DatabaseConnection;
import br.edu.utfpr.model.Usuario;
import br.edu.utfpr.report.GenerateReport;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Rafa
 */
public class FXMLTLPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     * 
     */
    
        @FXML
    private VBox boxPrincipal;

    private Usuario usuarioAutenticado;

    public void setUsuarioAutenticado(Usuario usuario) {
        this.usuarioAutenticado = usuario;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                // TODO
                setDataPane(openVBox("/fxml/FXMLLogo.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(FXMLTLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }    
    
    public void setDataPane(Node node) {
        /* Atualiza o VBox (boxPrincipal) com um 
            novo form (FXML) dependendo do 
            item de menu ou botão acionado
         */
        boxPrincipal.getChildren().setAll(node);
    }
    
    
    public VBox openVBox(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(
                this.getClass().getResource(url));
        FadeTransition ft = new FadeTransition(
                Duration.millis(1000));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }
    
    @FXML
    public void loadPrincipal(ActionEvent event)
            throws IOException {
        setDataPane(openVBox("/fxml/FXMLLogo.fxml"));
    }
    
    @FXML
    public void loadProduto(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLProdutoLista.fxml"
        ));
    }
    
    
    @FXML
    public void loadServico(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLServicoLista.fxml"
        ));
    }
    
    @FXML
    public void loadUsuario(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLUsuarioLista.fxml"
        ));
    }
    
    @FXML
    public void loadCliente(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLClienteLista.fxml"
        ));
    }
    @FXML
    public void loadQuarto(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLQuartoLista.fxml"
        ));
    }
    
    @FXML
    public void loadReserva(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLReservaLista.fxml"
        ));
    }
    

    @FXML
    private void showReportClientes(ActionEvent event) {
        GenerateReport generateReport = new GenerateReport();
        InputStream file = this.getClass().getResourceAsStream("/report/Clientes.Jasper");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("TITULO", "Relatório de Clientes");

        DatabaseConnection conn = DatabaseConnection.getInstance();
        try {
            JasperViewer viewer = generateReport.getReport(
                    conn.getConnection(), parameters, file);
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao exibir relatório!");
            alert.setContentText("Falha ao exibir relatório!");
            alert.showAndWait();
        }
    }
}
