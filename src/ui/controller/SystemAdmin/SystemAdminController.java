package ui.controller.SystemAdmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        choicebox_search_usertype.setItems(FXCollections.observableArrayList("用户类型1","用户类型2"));
        choicebox_search_usertype.setValue("用户类型1");
    }
}
