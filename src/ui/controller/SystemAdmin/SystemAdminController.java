package ui.controller.SystemAdmin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by FXBL on 6/21/2017.
 */
public class SystemAdminController implements Initializable {

    @FXML
    private ChoiceBox<String> choicebox_search_usertype;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO:ChoiceBox内容的初始化
        choicebox_search_usertype.setItems(FXCollections.observableArrayList("系统管理员", "一卡通管理员", "学生", "教学系统管理员", "图书管理员", "宿舍管理员"));
        choicebox_search_usertype.setValue("学生");
    }
}
