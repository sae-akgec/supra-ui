package in.saeakgec.supra.controller;

import in.saeakgec.supra.model.User;
import in.saeakgec.supra.service.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSession;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {

    @FXML
    TextField authUsername;

    @FXML
    PasswordField authPassword;

    @FXML
    Button authSubmit;

    @FXML
    Label alertLabel;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    AuthService authService;

    private StompSession session;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        authService = new AuthService();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        alertLabel.getStyleClass().add("alert");
        alertLabel.getStyleClass().add("alert-warning");
        alertLabel.setText("Please wait contacting to server....");
        alertLabel.setVisible(true);
        String username = authUsername.getText();
        String password = authPassword.getText();
        if (!username.equals("") &&  !password.equals("")){
            if(authenticate(username, password)){
//            session.send("/server/flags", new GreenFlag("HEllo"));
                openDashboard(event);
            }else{
                alertLabel.getStyleClass().clear();
                alertLabel.getStyleClass().add("alert");
                alertLabel.getStyleClass().add("alert-danger");
                alertLabel.setText("Wrong Credentials....");
            }
        }else {
            alertLabel.getStyleClass().clear();
            alertLabel.getStyleClass().add("alert");
            alertLabel.getStyleClass().add("alert-danger");
            alertLabel.setText("Please enter your Credentials....");
        }

    }


    private void openDashboard(ActionEvent event) throws IOException {
        Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new Pane()));
        Parent root = FXMLLoader.load(getClass().getResource("/in/saeakgec/supra/view/Dashboard.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("bootstrapfx.css");
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }



    private boolean authenticate(String username, String password){
        User user = new User(username, password);
        return authService.signin(user);
    }
}
