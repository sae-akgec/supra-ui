package in.saeakgec.supra.controller;

import in.saeakgec.supra.App;
import in.saeakgec.supra.model.Race;
import in.saeakgec.supra.service.DashboardService;
import in.saeakgec.supra.util.HeaderRequestInterceptor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class DashboardController implements Initializable {
    private DashboardService dashboardService;

    @FXML
    private ListView pendingListView;
//    @FXML
//    private ListView completedListView;

    ObservableList<Race> pendingObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dashboardService = new DashboardService();
        List<Race> races = dashboardService.getAdminRace();
        for(Race race:races){
            System.out.println(race.getName());
        }
        setPendingListView(races);
    }

    public void setPendingListView(List<Race> races){
        pendingObservableList.setAll(races);
        pendingListView.setItems(pendingObservableList);
        pendingListView.setCellFactory(new Callback<ListView<Race>, ListCell<Race>>(){

            @Override
            public ListCell<Race> call(ListView<Race> p) {

                ListCell<Race> cell = new ListCell<Race>(){

                    @Override
                    protected void updateItem(Race item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null){
                            System.out.println(item.getId());
                            DashboardData data = new DashboardData();
                            data.setInfo(item.getName(), item.getTime().toString());
                            data.setIndicaton( "btn-warning", "btn-xs");
                            setGraphic(data.getBox());
                        }else {
                            textProperty().unbind();
                            textProperty().set("");
                        }
                    }

                };

                return cell;
            }
        });


    }
}
