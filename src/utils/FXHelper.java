package utils;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by FXBL on 6/20/2017.
 */
public class FXHelper {
    public static Stage getStage(){
        ObservableList<Stage> stage = FXRobotHelper.getStages();
        return stage.get(0);
    }


    /**
     * 新窗口
     * @param className 类名
     * @param fxmlFile fxml文件位置
     * @param windowTitle 窗口名称
     * @throws IOException
     */
    public static void newStage(Class className,String fxmlFile,String windowTitle) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(className.getResource(fxmlFile)));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(windowTitle);
        stage.show();
    }




    /**
     * 弹出一个信息提示框
     * @param message
     */
    public static void showInfoDialog(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("信息");
        alert.setHeaderText("信息");
        alert.setContentText(message);
        alert.initOwner(FXHelper.getStage());
        alert.show();
    }


    /**
     * 弹出一个警告提示框
     * @param message
     */
    public static void showWarningDialog(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("警告");
        alert.setHeaderText("警告");
        alert.setContentText(message);
        alert.initOwner(FXHelper.getStage());
        alert.show();
    }

}
