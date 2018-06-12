package in.saeakgec.supra.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class DashboardData {

    @FXML
    private FlowPane fPane;

    @FXML
    private Button indicator;

    @FXML
    private Label name;

    @FXML
    private Label date;

    @FXML
    private Label to;

    @FXML
    private Label from;

    public DashboardData() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/in/saeakgec/supra/view/DashboardItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(String nameStr, String dateStr, String toStr, String fromStr)
    {
        name.setText(nameStr);
        date.setText(dateStr);
        to.setText(toStr);
        from.setText(fromStr);
    }

    public void setIndicaton(String... classes){
        indicator.getStyleClass().clear();
        indicator.getStyleClass().addAll(classes);
    }

    public FlowPane getBox()
    {
        return fPane;
    }
}
