package in.saeakgec.supra.controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import in.saeakgec.supra.model.Race;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRaceController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    @FXML
    TextField name;

    @FXML
    TextField toText;

    @FXML
    TextField fromText;

    @FXML
    Button addRace;

    @FXML
    Button clearButton;

    @FXML
    Button directionButton;



    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    protected DirectionsRenderer directionsRenderer = null;

    @FXML
    GoogleMapView mapView;

    private Stage stage = null;

    private Race race;

    @Override
        public void initialize(URL location, ResourceBundle resources) {

        race = new Race();
        mapView.addMapInializedListener(this);
        to.bindBidirectional(toText.textProperty());
        from.bindBidirectional(fromText.textProperty());

        addRace.setOnAction(e ->{
            race = new Race();
            System.out.println("hello");
            race.setName(name.getText());
            race.setAddressTo(toText.getText());
            race.setAddressFrom(fromText.toString());
            closeStage();

        });

        directionButton.setOnAction(e ->{
            DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.WALKING);
            directionsRenderer = new DirectionsRenderer(true, mapView.getMap(), directionsPane);
            directionsService.getRoute(request, this, directionsRenderer);
        });

        clearButton.setOnAction(e ->{
            directionsRenderer.clearDirections();
        });
    }


    private void clearDirections(ActionEvent event) {
        directionsRenderer.clearDirections();
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        options.center(new LatLong(47.606189, -122.335842))
                .zoomControl(true)
                .zoom(15)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {

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
