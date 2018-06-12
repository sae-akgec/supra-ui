package in.saeakgec.supra.controller;

import com.fasterxml.jackson.databind.type.MapType;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.util.MarkerImageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class RaceController implements Initializable {

    @FXML
    private GoogleMapView googleMapView;

    private GoogleMap map;

    private DecimalFormat formatter = new DecimalFormat("###.00000");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        googleMapView.addMapInializedListener(() -> configureMap());
    }

    protected void configureMap() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.6097, -122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(9);
        map = googleMapView.createMap(mapOptions, false);

        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
            LatLong latLong = event.getLatLong();
            String path = MarkerImageFactory.createMarkerImage(baseDir() + "flags/flag-yellow.png", "png");
            System.out.println(path);
            path = path.replace("(", "");
            path = path.replace(")", "");
            System.out.println(path);
            showMarker(latLong, path);
        });

    }



    public void showMarker(LatLong latLong, String iconPath) {
        MarkerOptions options = new MarkerOptions();
        options
                .icon(iconPath)
                .position(latLong);
        Marker marker = new Marker(options);
        map.addMarker(marker);
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

}
