package br.edu.utfpr.controller;

import br.edu.utfpr.dao.UsuarioDao;
import br.edu.utfpr.model.Usuario;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {

    @FXML
    private TextField textUsuario;
    @FXML
    private TextField textSenha;
    @FXML
    private Button buttonEntrar;

    private UsuarioDao usuarioDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.usuarioDao = new UsuarioDao();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textUsuario.requestFocus();
            }
        });
        buttonEntrar.setDefaultButton(true);
    }

    private String hashSenha(TextField senha) {

        String senhaHex = "";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte messaDigest[] = digest.digest(senha.getText().getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();

            for (byte b : messaDigest) {
                sb.append(String.format("%02X", 0xFF & b));

            }

            senhaHex = sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FXMLUsuarioCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FXMLUsuarioCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return senhaHex;
    }

    @FXML
    private void login() {
        try {
            Usuario usuario = this.usuarioDao.findByEmailAndSenhaNamedQuery(
                    textUsuario.getText(), hashSenha(textSenha));
            if (usuario != null) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(
                        this.getClass()
                                .getResource("/fxml/FXMLTLPrincipal.fxml"));
                VBox root = (VBox) loader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/bootstrap.css");

                Stage stage = new Stage();
                stage.setTitle("Controle de Reservas");
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.setResizable(true);

                FXMLTLPrincipalController controller
                        = loader.getController();
                controller.setUsuarioAutenticado(usuario);
                
                stage.show();

                Stage stageLogin = (Stage) buttonEntrar.getScene().getWindow();
                stageLogin.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Usuário e/ou senha inválidos!");
            alert.setContentText("Por favor, tente novamente!");
            alert.showAndWait();
        }
    }

}
