package in.saeakgec.supra.controller;

import in.saeakgec.supra.App;
import in.saeakgec.supra.model.Race;
import in.saeakgec.supra.service.DashboardService;
import in.saeakgec.supra.util.HeaderRequestInterceptor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private ListView completedListView;

    ObservableList<Race> pendingObservableList = FXCollections.observableArrayList();
    ObservableList<Race> completedObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dashboardService = new DashboardService();
        List<Race> races = dashboardService.getAdminRace();
        List<Race> pendingRaces = getPendingView(races);
        List<Race> completedRaces = getCompletedView(races);
        setPendingListView(pendingRaces);
        setCompletedListView(races);
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

        pendingListView.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {

                ObservableList<Race> clickedRaces = pendingListView.getSelectionModel().getSelectedItems();
                Race race = clickedRaces.get(0);
                System.out.println(race.getName());
            }

        });


    }

    public void setCompletedListView(List<Race> races){
        completedObservableList.setAll(races);
        completedListView.setItems(completedObservableList);
        completedListView.setCellFactory(new Callback<ListView<Race>, ListCell<Race>>(){

            @Override
            public ListCell<Race> call(ListView<Race> p) {

                ListCell<Race> ncell = new ListCell<Race>(){

                    @Override
                    protected void updateItem(Race item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null){
                            DashboardData data = new DashboardData();
                            data.setInfo(item.getName(), item.getTime().toString());
                            data.setIndicaton( "btn-info", "btn-xs");
                            setGraphic(data.getBox());
                        }else {
                            textProperty().unbind();
                            textProperty().set("");
                        }
                    }

                };

                return ncell;
            }
        });


    }
    
    public List<Race> getPendingView(List<Race> races){
        ArrayList<Race> pendingRaces = new ArrayList<>();
        for (Race race : races){
            if((race.getStatus()).equals("pending")){
                pendingRaces.add(race);
            }
        }
        
        return pendingRaces;
    }

    public List<Race> getCompletedView(List<Race> races){
        ArrayList<Race> completedRaces = new ArrayList<>();
        for (Race race : races){
            if((race.getStatus()).equals("completed")){
                completedRaces.add(race);
            }
        }

        return completedRaces;
    }
}
