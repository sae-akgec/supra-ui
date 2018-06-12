package in.saeakgec.supra.controller;

import in.saeakgec.supra.App;
import in.saeakgec.supra.model.Race;
import in.saeakgec.supra.service.DashboardService;
import in.saeakgec.supra.util.HeaderRequestInterceptor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.io.IOException;
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

    protected App main;

    public void setMainApp(App main) {
        this.main = main;
    }

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

        pendingListView.setOnMouseClicked(arg0 -> {

            ObservableList<Race> clickedRaces = pendingListView.getSelectionModel().getSelectedItems();
            Race race = clickedRaces.get(0);
            System.out.println(race.getName());
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

    private Race showPopupWindow() {
        Race race;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Popup.fxml"));
        // initializing the controller
        AddRaceController popupController = new AddRaceController();
        loader.setController(popupController);
        Parent layout;
        try {
            layout = loader.load();
            Scene scene = new Scene(layout);
            // this is the popup stage
            Stage popupStage = new Stage();
            // Giving the popup controller access to the popup stage (to allow the controller to close the stage)
            popupController.setStage(popupStage);
            if(this.main!=null) {
                popupStage.initOwner(main.getPrimaryStage());
            }
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.setScene(scene);
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return popupController.getResult();
    }
}
