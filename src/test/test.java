package test;

/**
 * Created by FXBL on 6/18/2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FXHelper;


/**
 * 这是本软件的主入口,要运行本软件请直接运行本类就可以了,不用传入任何参数
 */
public class test extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXHelper.newStage(getClass(),"/resources/fxml/User.fxml","学生一卡通用户测试");
    }

    public static void main(String[] args) {
            launch(args);

    }

}
