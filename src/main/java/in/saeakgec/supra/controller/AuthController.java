package in.saeakgec.supra.controller;

import in.saeakgec.supra.model.GreenFlag;
import in.saeakgec.supra.model.User;
import in.saeakgec.supra.service.AuthService;
import in.saeakgec.supra.websocket.MyStompSessionHandler;
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
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

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
//        try {
//            StompSession session = connectToWebsocketServer("http://localhost:8080/socket","JavaFx-client");
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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

    private StompSession connectToWebsocketServer(String url, String sender) throws ExecutionException, InterruptedException, IOException {
        WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
        List<Transport> transports = new ArrayList(1);
        transports.add(new WebSocketTransport(simpleWebSocketClient));

        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        StompSession session = stompClient.connect(url, sessionHandler).get();
        return session;
    }

    private boolean authenticate(String username, String password){
        User user = new User(username, password);
        return authService.signin(user);
    }
}
