package in.saeakgec.supra.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;



public class AddFlagController implements Initializable {

    private String colorMain;

    @FXML
    HBox blueFlag;

    @FXML
    HBox redYellowFlag;

    @FXML
    HBox redFlag;

    @FXML
    HBox greenFlag;


    @FXML
    HBox yellowFlag;

    private Stage stage = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        blueFlag.setOnMouseClicked(event -> {
            colorMain = "blue";
            closeStage();
        });

        redYellowFlag.setOnMouseClicked(event -> {
            colorMain = "red-yellow";
            closeStage();
        });

        redFlag.setOnMouseClicked(event -> {
            colorMain = "red";
            closeStage();
        });

        greenFlag.setOnMouseClicked(event -> {
            colorMain = "green";
            closeStage();
        });

        yellowFlag.setOnMouseClicked(event -> {
            colorMain = "yellow";
            closeStage();
        });

    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void closeStage() {
        if(stage!=null) {
            stage.close();
        }
    }

    public String getColorMain() {
        return colorMain;
    }
}
