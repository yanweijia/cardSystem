package ui.controller.SystemAdmin;

import com.sun.javafx.css.CssError;
import dao.OrganizationMapper;
import dao.SectionMapper;
import dao.UserMapper;
import db.DBAccess;
import entity.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import utils.CodeUtils;
import utils.FXHelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by FXBL on 6/21/2017.
 */
public class SystemAdminController implements Initializable {
    final Logger logger = LogManager.getLogger(SystemAdminController.class);





    //查询选项卡的控件
    @FXML private ChoiceBox<String> choiceBoxSearchUsertype,choiceBoxSearchOrganization,choiceBoxSearchUserSection;
    @FXML private TextField textFieldSearchUserID,textFieldSearchUserName;
    @FXML private TableView<User> tableViewSearch;
    @FXML private TableColumn<User,String> columnSearchID,columnSearchType,columnSearchName,columnSearchBalance,columnSearchPhone;


    //新建用户选项卡的控件
    @FXML private ChoiceBox<String> choiceBoxNewUserType,choiceBoxNewUserAvailable,choiceBoxNewUserOrganization,choiceBoxNewUserSection;
    @FXML private TextField textFieldNewUserID,textFieldNewUserPW,textFieldNewUserPhone,textFieldNewUserName;

    //维护用户信息选项卡的控件
    @FXML private ChoiceBox<String> choiceBoxUpdateUserType,choiceBoxUpdateUserAvailable,choiceBoxUpdateUserOrganization,choiceBoxUpdateUserSection;
    @FXML private TextField textFieldUpdateUserName,textFieldUpdateUserID;

    //重置用户密码选项卡的控件
    @FXML private TextField textFieldResetPwUserID;


    //组织机构维护选项卡相关控件
    @FXML private TextField textFieldOrganizationID,textFieldOrganizationName;
    @FXML private TableView<Organization> tableViewOrganization;
    @FXML private TableColumn<Organization,String> columnOrganizatonID,columnOrganizationName;
    @FXML private TextField textFieldOrganizationNewName;


    //科室/班级维护选项卡相关控件
    @FXML private TextField textFieldSectionID,textFieldSectionName,textFieldSectionNewSectionName;
    @FXML private ChoiceBox<String> choiceBoxSectionOrganization,choiceBoxSectionNewSectionOrganization;
    @FXML private TableView<Section> tableViewSection;
    @FXML private TableColumn<Section,String> columnSectionOrganizationID,columnSectionID,columnSectionName;



    /**
     * 删除选中Section
     */
    @FXML
    private void deleteSelectedSection(){
        Section section = tableViewSection.getSelectionModel().getSelectedItem();
        if(section==null){
            FXHelper.showWarningDialog("未选中任何数据,无法删除!");
            return;
        }
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION,"确认删除?",ButtonType.YES, ButtonType.CANCEL).showAndWait();
        if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            SqlSession sqlSession = DBAccess.getSqlSession();
            SectionMapper sectionMapper = sqlSession.getMapper(SectionMapper.class);
            sectionMapper.deleteByPrimaryKey(section.getSectionId());
            FXHelper.showInfoDialog("删除成功!");
            sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 删除选中Organization
     */
    @FXML
    private void deleteSelectedOrganization(){
        Organization organization = tableViewOrganization.getSelectionModel().getSelectedItem();
        if(organization==null){
            FXHelper.showWarningDialog("未选中任何数据,无法删除!");
            return;
        }
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION,"确认删除?",ButtonType.YES, ButtonType.CANCEL).showAndWait();
        if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            SqlSession sqlSession = DBAccess.getSqlSession();
            OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
            organizationMapper.deleteByPrimaryKey(organization.getOrganization_id());
            FXHelper.showInfoDialog("删除成功!");
            sqlSession.commit();
            sqlSession.close();
        }
    }


    /**
     * 删除选中用户
      */
    @FXML
    private void deleteSelectedUser(){
        User user = tableViewSearch.getSelectionModel().getSelectedItem();
        if(user==null){
            FXHelper.showWarningDialog("未选中任何数据,无法删除!");
            return;
        }
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION,"确认删除?",ButtonType.YES, ButtonType.CANCEL).showAndWait();
        if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            SqlSession sqlSession = DBAccess.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteByPrimaryKey(user.getId());
            FXHelper.showInfoDialog("删除成功!");
            sqlSession.commit();
            sqlSession.close();
        }
    }


    /**
     * 新增科室/班级
     */
    @FXML
    private void addNewSection(){
        String sectionName = textFieldSectionNewSectionName.getText();
        if("".equals(sectionName)){
            FXHelper.showWarningDialog("请输入科室/班级名称!");
            textFieldSectionNewSectionName.requestFocus();
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();

        SectionMapper sectionMapper = sqlSession.getMapper(SectionMapper.class);
        Section section = new Section();
        section.setName(sectionName);
        OrganizationExample organizationExample = new OrganizationExample();
        organizationExample.or().andNameEqualTo(choiceBoxSectionNewSectionOrganization.getValue());
        Integer organizationID = sqlSession.getMapper(OrganizationMapper.class).selectByExample(organizationExample).get(0).getOrganization_id();
        section.setOrganizationId(organizationID);

        sectionMapper.insertSelective(section);
        FXHelper.showInfoDialog("新增科室/班级成功!");
        textFieldSectionNewSectionName.setText("");
        sqlSession.commit();
        sqlSession.close();
        initialize(null,null);
    }


    /**
     * 查询科室/班级相关信息
     */
    @FXML
    private void querySectionBy(){
        Integer sectionID = 0;
        String sectionName = textFieldSectionName.getText();
        if(!"".equals(textFieldSectionID.getText())) {
            try {
                sectionID = Integer.parseInt(textFieldSectionID.getText());
            } catch (Exception e) {
                logger.error("班级编号解析失败!", e);
                FXHelper.showWarningDialog("班级编号解析失败!");
                textFieldSectionID.requestFocus();
                return;
            }
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        SectionExample sectionExample = new SectionExample();
        SectionMapper sectionMapper = sqlSession.getMapper(SectionMapper.class);
        SectionExample.Criteria criteria = sectionExample.or().andNameLike("%"+sectionName+"%");
        if(!"".equals(textFieldSectionID.getText())){
            criteria.andSectionIdEqualTo(sectionID);
        }
        if(!"".equals(choiceBoxSectionOrganization.getValue())){
            String organizationName = choiceBoxSectionOrganization.getValue();
            OrganizationExample organizationExample = new OrganizationExample();
            organizationExample.or().andNameEqualTo(organizationName);
            Integer organizationID = sqlSession.getMapper(OrganizationMapper.class).selectByExample(organizationExample).get(0).getOrganization_id();
            criteria.andOrganizationIdEqualTo(organizationID);
        }

        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        tableViewSection.setItems(FXCollections.observableList(sectionList));
        sqlSession.close();
    }




    /**
     * 新增组织机构
     */
    @FXML
    private void addNewOrganization(){
        String organizationName = textFieldOrganizationNewName.getText();
        if("".equals(organizationName)){
            FXHelper.showWarningDialog("请输入组织机构名称!");
            textFieldOrganizationNewName.requestFocus();
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();

        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        Organization organization = new Organization();
        organization.setName(organizationName);
        organizationMapper.insertSelective(organization);
        FXHelper.showInfoDialog("新增组织机构成功!");
        textFieldOrganizationNewName.setText("");
        sqlSession.commit();
        sqlSession.close();
        initialize(null,null);
    }


    /**
     * 查询组织机构相关信息
     */
    @FXML
    private void queryOrganizationBy(){
        Integer organizationID = 0;
        String organizationName = textFieldOrganizationName.getText();
        if(!"".equals(organizationName)) {
            try {
                organizationID = Integer.parseInt(textFieldOrganizationID.getText());
            } catch (Exception e) {
                logger.error("组织编号解析失败!", e);
                FXHelper.showWarningDialog("组织编号解析失败!");
                textFieldOrganizationID.requestFocus();
                return;
            }
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        OrganizationExample organizationExample = new OrganizationExample();
        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        OrganizationExample.Criteria criteria = organizationExample.or().andNameLike("%"+organizationName+"%");
        if(!"".equals(textFieldOrganizationID.getText())){
            criteria.andOrganization_idEqualTo(organizationID);
        }
        List<Organization> organizationList = organizationMapper.selectByExample(organizationExample);
        tableViewOrganization.setItems(FXCollections.observableList(organizationList));
        sqlSession.close();
    }





    /**
     * 重置用户密码
     */
    @FXML
    private void resetUserPassword(){
        String userID = textFieldResetPwUserID.getText();
        if("".equals(userID)){
            FXHelper.showWarningDialog("请输入用户编号");
            textFieldResetPwUserID.requestFocus();
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(userID);
        if(user==null){
            FXHelper.showWarningDialog("编号为0"+userID+"的用户不存在!");
            textFieldResetPwUserID.requestFocus();
            return;
        }
        user.setPassword(CodeUtils.getMD5("123456"));
        FXHelper.showInfoDialog("编号为0"+userID+"的用户密码已被重置为:\n123456\n请通知该用户及时登录系统修改密码!");
        userMapper.updateByPrimaryKey(user);
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 确认更新用户信息
     */
    @FXML
    private void confirmUpdateUserInfo(){
        String userID = textFieldUpdateUserID.getText();
        String userName = textFieldUpdateUserName.getText();
        String organizationName = choiceBoxUpdateUserOrganization.getValue();
        String sectionName = choiceBoxUpdateUserSection.getValue();
        if("".equals(userID)){
            FXHelper.showWarningDialog("请输入用户编号!");
            textFieldUpdateUserID.requestFocus();
            return;
        }
        if(!"".equals(userName)){
            FXHelper.showWarningDialog("请输入用户姓名!");
            textFieldUpdateUserName.requestFocus();
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        User user = sqlSession.getMapper(UserMapper.class).selectByPrimaryKey(userID);
        user.setId(userID);
        user.setName(userName);
        user.setAvailable(choiceBoxUpdateUserAvailable.getValue());
        user.setType(choiceBoxUpdateUserType.getValue());

        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        SectionMapper sectionMapper = sqlSession.getMapper(SectionMapper.class);
        OrganizationExample organizationExample = new OrganizationExample();
        SectionExample sectionExample = new SectionExample();

        if(!"".equals(organizationName)){
            organizationExample.or().andNameEqualTo(organizationName);
            Integer organizaionID = organizationMapper.selectByExample(organizationExample).get(0).getOrganization_id();
            user.setOrganization(organizaionID);
        }else {
            user.setOrganization(null);
        }

        if(!"".equals(sectionName)){
            sectionExample.or().andNameEqualTo(sectionName);
            Integer sectionID = sectionMapper.selectByExample(sectionExample).get(0).getSectionId();
            user.setSection(sectionID);
        }else{
            user.setSection(null);
        }

        sqlSession.getMapper(UserMapper.class).updateByPrimaryKey(user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 重设"更新用户信息"相关控件
     */
    @FXML
    private void resetUpdateUserInfo(){
        queryOriginalUserInfo();
    }

    /**
     * 查询用户原始信息
     */
    @FXML
    private void queryOriginalUserInfo(){
        String userID = textFieldUpdateUserID.getText();
        if("".equals(userID)){
            FXHelper.showWarningDialog("请输入用户编号!");
            textFieldUpdateUserID.requestFocus();
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(userID);
        if(user==null){
            FXHelper.showWarningDialog("编号为"+userID+"的用户不存在,请重新输入!");
            textFieldUpdateUserID.requestFocus();
            return;
        }


        textFieldUpdateUserName.setText(user.getName());
        choiceBoxUpdateUserType.setValue(user.getType());
        choiceBoxUpdateUserAvailable.setValue(user.getAvailable());
        choiceBoxUpdateUserOrganization.setValue(sqlSession.getMapper(OrganizationMapper.class).selectByPrimaryKey(user.getOrganization()).getName());
        choiceBoxUpdateUserSection.setValue(sqlSession.getMapper(SectionMapper.class).selectByPrimaryKey(user.getSection()).getName());

        sqlSession.close();
    }





    /**
     * 新建用户
     */
    @FXML
    private void newUser(){
        String userID = textFieldNewUserID.getText();
        String userPW = CodeUtils.getMD5(textFieldNewUserPW.getText());
        String userPhone = textFieldNewUserPhone.getText();
        String userName = textFieldNewUserName.getText();
        String userType = choiceBoxNewUserType.getValue();
        String userAvailable = choiceBoxNewUserAvailable.getValue();

        String userOrganization = choiceBoxNewUserOrganization.getValue();
        String userSection = choiceBoxNewUserSection.getValue();

        SqlSession sqlSession = DBAccess.getSqlSession();
        SectionMapper sectionMapper = sqlSession.getMapper(SectionMapper.class);
        SectionExample sectionExample = new SectionExample();


        if(!"".equals(userOrganization))
            sectionExample.or().andNameEqualTo(userSection);
        Integer organizationID = sectionMapper.selectByExample(sectionExample).get(0).getOrganizationId();
        Integer sectionID = sectionMapper.selectByExample(sectionExample).get(0).getSectionId();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(userID);
        user.setPassword(userPW);
        user.setPhone(userPhone);
        user.setName(userName);
        user.setType(userType);
        user.setAvailable(userAvailable);
        user.setOrganization(organizationID);
        user.setSection(sectionID);
        userMapper.insertSelective(user);
        FXHelper.showInfoDialog("新建用户成功!");
        resetNewUser();
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 重设新建用户编辑框
     */
    @FXML
    private void resetNewUser(){
        textFieldNewUserID.setText("");
        textFieldNewUserPW.setText("");
        textFieldNewUserPhone.setText("");
        textFieldNewUserName.setText("");
        choiceBoxNewUserType.setValue("学生");
        choiceBoxNewUserAvailable.setValue("正常");


    }


    /**
     * 初始化
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ChoiceBox内容的初始化
        choiceBoxSearchUsertype.setItems(FXCollections.observableArrayList("","系统管理员", "一卡通管理员", "学生", "教学系统管理员", "图书管理员", "宿舍管理员"));
        choiceBoxSearchUsertype.setValue("");
        SqlSession sqlSession = DBAccess.getSqlSession();
        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        List<Organization> organizationList = organizationMapper.selectByExample(new OrganizationExample());
        List<String> newOrganizationList = new ArrayList<>();
        newOrganizationList.add("");
        for(Organization org : organizationList){
            newOrganizationList.add(org.getName());
        }
        choiceBoxSearchOrganization.setItems(FXCollections.observableList(newOrganizationList));
        if(newOrganizationList.size()>1)
            choiceBoxSearchOrganization.setValue(newOrganizationList.get(0));
        choiceBoxSearchOrganization.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,Number old_val,Number new_val) -> {
                    //组织机构选择框被选择后修改对应科室/班级选择框
                    chagneSectionListWhenOrganizationChanged(new_val,choiceBoxSearchOrganization,choiceBoxSearchUserSection);
                }
        );
        chagneSectionListWhenOrganizationChanged(0,choiceBoxSearchOrganization,choiceBoxSearchUserSection); //默认根据第一个组织机构更新Section数据
        sqlSession.close();
        columnSearchID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnSearchType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnSearchName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSearchBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        columnSearchPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));



        //新建用户界面组建
        choiceBoxNewUserType.setItems(FXCollections.observableArrayList("系统管理员", "一卡通管理员", "学生", "教学系统管理员", "图书管理员", "宿舍管理员"));
        choiceBoxNewUserType.setValue("学生");
        choiceBoxNewUserAvailable.setItems(FXCollections.observableArrayList("正常", "正常销卡", "退学销卡", "休学销卡"));
        choiceBoxNewUserAvailable.setValue("正常");
        choiceBoxNewUserOrganization.setItems(FXCollections.observableList(newOrganizationList.subList(1,newOrganizationList.size())));
        if(newOrganizationList.size()>1)
            choiceBoxNewUserOrganization.setValue(newOrganizationList.get(1));
        chagneSectionListWhenOrganizationChanged(0,choiceBoxNewUserOrganization,choiceBoxNewUserSection); //默认根据第一个组织机构更新Section数据
        choiceBoxNewUserOrganization.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,Number old_val,Number new_val) -> {
                    //组织机构选择框被选择后修改对应科室/班级选择框
                    chagneSectionListWhenOrganizationChanged(new_val,choiceBoxNewUserOrganization,choiceBoxNewUserSection);
                }
        );

        //修改用户信息页面
        choiceBoxUpdateUserType.setItems(FXCollections.observableArrayList("系统管理员", "一卡通管理员", "学生", "教学系统管理员", "图书管理员", "宿舍管理员"));
        choiceBoxUpdateUserType.setValue("学生");
        choiceBoxUpdateUserAvailable.setItems(FXCollections.observableArrayList("正常", "正常销卡", "退学销卡", "休学销卡"));
        choiceBoxUpdateUserAvailable.setValue("正常");
        choiceBoxUpdateUserOrganization.setItems(FXCollections.observableList(newOrganizationList.subList(1,newOrganizationList.size())));
        if(newOrganizationList.size()>1)
            choiceBoxUpdateUserOrganization.setValue(newOrganizationList.get(1));
        chagneSectionListWhenOrganizationChanged(0,choiceBoxUpdateUserOrganization,choiceBoxUpdateUserSection); //默认根据第一个组织机构更新Section数据
        choiceBoxUpdateUserOrganization.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,Number old_val,Number new_val) -> {
                    //组织机构选择框被选择后修改对应科室/班级选择框
                    chagneSectionListWhenOrganizationChanged(new_val,choiceBoxUpdateUserOrganization,choiceBoxUpdateUserSection);
                }
        );

        //组织结构维护页面
        columnOrganizatonID.setCellValueFactory(new PropertyValueFactory<>("organization_id"));
        columnOrganizationName.setCellValueFactory(new PropertyValueFactory<>("name"));

        //科室,班级维护页面
        choiceBoxSectionOrganization.setItems(FXCollections.observableList(newOrganizationList));
        if(newOrganizationList.size()>1)
            choiceBoxSectionOrganization.setValue(newOrganizationList.get(0));
        choiceBoxSectionNewSectionOrganization.setItems(FXCollections.observableList(newOrganizationList.subList(1,newOrganizationList.size())));
        if(newOrganizationList.size()>1)
            choiceBoxSectionNewSectionOrganization.setValue(newOrganizationList.get(1));

        columnSectionOrganizationID.setCellValueFactory(new PropertyValueFactory<>("organizationId"));
        columnSectionID.setCellValueFactory(new PropertyValueFactory<>("sectionId"));
        columnSectionName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }


    /**
     * 当组织机构被选择后修改科室/班级选择框内容
     */
    private void chagneSectionListWhenOrganizationChanged(Number new_val,ChoiceBox<String> choiceBoxOrganization,ChoiceBox<String> choiceBoxSection){
        if(new_val.intValue()<0)
            new_val=0;
        SqlSession sqlSession = DBAccess.getSqlSession();
        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        SectionMapper sectionMapper = sqlSession.getMapper(SectionMapper.class);
        SectionExample sectionExample = new SectionExample();

        OrganizationExample organizationExample = new OrganizationExample();
        String organizationName = choiceBoxOrganization.getItems().get(new_val.intValue());
        if(!"".equals(organizationName)){       //如果组织机构名称不为空,即要选中了组织机构
            organizationExample.or().andNameEqualTo(organizationName);
        }
        Organization organization = organizationMapper.selectByExample(organizationExample).get(0);
        sectionExample.or().andOrganizationIdEqualTo(organization.getOrganization_id());
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        List<String> newSectionList = new ArrayList<>();
        newSectionList.add("");
        for(Section section:sectionList){
            newSectionList.add(section.getName());
        }
        choiceBoxSection.setItems(FXCollections.observableList(newSectionList));
        if(newSectionList.size()!=0)
            choiceBoxSection.setValue(newSectionList.get(0));
        sqlSession.close();
    }




    /**
     * 查询用户并显示在表格上
     */
    @FXML
    private void queryUsersAndShow(){
        String userID = textFieldSearchUserID.getText();
        String userType = choiceBoxSearchUsertype.getValue();
        String userName = textFieldSearchUserName.getText();
        String userOrganization = choiceBoxSearchOrganization.getValue();
        String userSection = choiceBoxSearchUserSection.getValue();

        SqlSession sqlSession = DBAccess.getSqlSession();
        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        SectionMapper sectionMapper = sqlSession.getMapper(SectionMapper.class);
        OrganizationExample organizationExample = new OrganizationExample();
        SectionExample sectionExample = new SectionExample();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria =  userExample.or();
        criteria.andIdLike((userID!=null)?"%"+userID+"%":"%")
                        .andNameLike("%"+userName+"%");
        if(!"".equals(userType)){
            criteria.andTypeEqualTo(userType);
        }
        if(!"".equals(userOrganization)) {
            organizationExample.or().andNameEqualTo(userOrganization);
            Integer organizationID = organizationMapper.selectByExample(organizationExample).get(0).getOrganization_id();
            criteria.andOrganizationEqualTo(organizationID);
        }
        if(!"".equals(userSection)) {
            sectionExample.or().andNameEqualTo(userSection);
            Integer sectionID = sectionMapper.selectByExample(sectionExample).get(0).getSectionId();
            criteria.andSectionEqualTo(sectionID);
        }
        List<User> userList = userMapper.selectByExample(userExample);

        tableViewSearch.setItems(FXCollections.observableList(userList));

        sqlSession.close();
    }
}
