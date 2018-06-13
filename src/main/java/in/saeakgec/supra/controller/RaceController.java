package in.saeakgec.supra.controller;

import com.fasterxml.jackson.databind.type.MapType;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.util.MarkerImageFactory;
import in.saeakgec.supra.App;
import in.saeakgec.supra.model.Race;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class RaceController implements Initializable,MapComponentInitializedListener, DirectionsServiceCallback {

    @FXML
    private GoogleMapView googleMapView;
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    protected DirectionsRenderer directionsRenderer = null;

    @FXML
    AnchorPane mapPane;

    private GoogleMap map;
    private Race race;

    protected App main;


    public void setMainApp(App main) {
        this.main = main;
    }

    private DecimalFormat formatter = new DecimalFormat("###.00000");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        googleMapView.addMapInializedListener(this);
        mapPane.setOnMouseClicked(event -> {
            String color = showPopupWindow();
            String code = address(color);
            showMarker(event, code);
        });
    }


    public void showMarker(MouseEvent event, String code) {
        System.out.println(code);
        System.out.println(event.getX() + " --- "+ event.getY());
        ImageView imageView = new ImageView();
        imageView.setX(event.getX());
        imageView.setY(event.getY());
        File file = new File("src/main/resources/in/saeakgec/supra/img/flags/" + code);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);

        mapPane.getChildren().add(imageView);
    }

    public String address(String color){
        String code="";
        if(color.equals("blue")){
            code = "flag-blue.png";
        }
        if(color.equals("red-yellow")){
            code = "flag-red-yellow.png";
        }
        if(color.equals("red")){
            code = "flag-red.png";
        }
        if(color.equals("green")){
            code = "flag-green.png";
        }
        if(color.equals("yellow")){
            code = "flag-yellow.png";
        }

        return code;
    }

    private String baseDir() {
        String baseDir = "";
        try {
            baseDir = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseDir = baseDir.replace('\\','/');
        baseDir = "file://" + baseDir + "/src/" + "main/resources/in/saeakgec/supra/img/";
        return baseDir;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        options.center(new LatLong(28.3505, 77.5351))
                .zoomControl(false)
                .zoom(15)
                .overviewMapControl(false).mapMaker(true)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = googleMapView.createMap(options);

        directionsService = new DirectionsService();
        directionsPane = googleMapView.getDirec();
        DirectionsRequest request = new DirectionsRequest(race.getAddressFrom(), race.getAddressTo(), TravelModes.WALKING);
        directionsRenderer= new DirectionsRenderer(false, googleMapView.getMap(), directionsPane);
        directionsService.getRoute(request, this, directionsRenderer);



    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
    }

    private String showPopupWindow() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/in/saeakgec/supra/view/AddFlag.fxml"));
        // initializing the controller
        AddFlagController popupController = new AddFlagController();
        loader.setController(popupController);
        Parent layout;
        try {
            layout = loader.load();
            Scene scene = new Scene(layout);
            scene.getStylesheets().add("bootstrapfx.css");

            Stage popupStage = new Stage();

            popupController.setStage(popupStage);
            if(this.main!=null) {
                popupStage.initOwner(main.getPrimaryStage());
            }
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.setScene(scene);
            popupStage.showAndWait();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return popupController.getColorMain();
    }
}
