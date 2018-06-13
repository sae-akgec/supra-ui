package in.saeakgec.supra.scene;

import in.saeakgec.supra.model.Race;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RaceScene extends Scene {
    private Race race;

    public RaceScene(Parent root) {
        super(root);
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
