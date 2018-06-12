package in.saeakgec.supra.controller;

import in.saeakgec.supra.model.Race;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class AddRaceController implements Initializable {

    @FXML
    TextField name;

    @FXML
    Button addRace;

    private Stage stage = null;

    private Race race;

    @Override
        public void initialize(URL location, ResourceBundle resources) {

        race = new Race();

        addRace.setOnAction(e ->{
            race.setName(name.getText());
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

    public Race getResult() {
        return this.race;
    }
}
