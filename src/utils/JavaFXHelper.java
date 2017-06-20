package utils;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * Created by FXBL on 6/20/2017.
 */
public class JavaFXHelper {
    public static Stage getStage(){
        ObservableList<Stage> stage = FXRobotHelper.getStages();
        return stage.get(0);
    }
}
