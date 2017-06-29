package ui.controller.CardAdmin;

import dao.*;
import db.DBAccess;
import entity.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.ibatis.session.SqlSession;
import utils.CodeUtils;
import utils.FXHelper;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by FXBL on 6/28/2017.
 */
public class CardAdminController implements Initializable{

    //新建用户页面
    @FXML private TextField textFieldNewUserID,textFieldNewUserPW,textFieldNewUserName;
    @FXML private ChoiceBox<String> choiceBoxNewUserOrganization,choiceBoxNewUserSection;

    //销卡用户页面
    @FXML private TextField textFieldUserDeleteID;

    //查询统计用户信息
    @FXML private TextField textFieldSearchInfoName;
    @FXML private ChoiceBox<String> choiceBoxSearchInfoType,choiceBoxSearchInfoOrganization;
    @FXML private TableView<User> tableViewSearchInfo;
    @FXML private TableColumn<User,String> columnUserID,columnUserType,columnUserAvailable,columnUserName,columnUserBalance,columnUserOrganization,columnUserPhone;

    //查询统计用户消费信息
    @FXML private TextField textFieldSearchConsumeUserID;
    @FXML private TableView<Consume> tableViewSearchConsume;
    @FXML private TableColumn<Consume,String> columnConsumeUserID,columnConsumeTime,columnConsumeMoney,columnConsumeGoodsName;

    //查看待审核列表
    @FXML private TableView<RequestModify> tableViewRequestModify;
    @FXML private TableColumn<RequestModify,String> columnModifyID,columnModifyUserID,columnModifyPhone,columnModifyEmail,columnModifyAddr;


    /**
     * 驳回修改信息请求
     */
    @FXML
    private void rejectSelectedRequestModify(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        RequestModifyMapper requestModifyMapper = sqlSession.getMapper(RequestModifyMapper.class);
        RequestModify requestModify = tableViewRequestModify.getSelectionModel().getSelectedItem();
        if(requestModify==null){
            FXHelper.showWarningDialog("抱歉,您还没有选中任何信息,无法进行修改的审批或驳回!");
            return;
        }
        requestModify.setValidate(true);
        if(requestModifyMapper.deleteByPrimaryKey(requestModify.getId())>0){
            FXHelper.showInfoDialog("驳回成功!");
        }else{
            FXHelper.showWarningDialog("驳回失败!");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 通过修改信息请求
     */
    @FXML
    private void passTheSelectedRequestModify(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        RequestModifyMapper requestModifyMapper = sqlSession.getMapper(RequestModifyMapper.class);
        UserMapper userMapper = sqlSession.getMapper((UserMapper.class));
        RequestModify requestModify = tableViewRequestModify.getSelectionModel().getSelectedItem();
        if(requestModify==null){
            FXHelper.showWarningDialog("抱歉,您还没有选中任何信息,无法进行修改的审批或驳回!");
            return;
        }
        requestModify.setValidate(true);
        requestModifyMapper.deleteByPrimaryKey(requestModify.getId());
        User user = userMapper.selectByPrimaryKey(requestModify.getUserId());
        user.setPhone(requestModify.getPhone());
        user.setEmail(requestModify.getEmail());
        user.setAddr(requestModify.getAddr());
        if(userMapper.updateByPrimaryKey(user)>0){
            FXHelper.showInfoDialog("审批成功!");
        }else{
            FXHelper.showWarningDialog("修改失败,请联系管理员!");
        }
        sqlSession.commit();
        sqlSession.close();
    }




    /**
     * 查询待审核列表
     */
    @FXML
    private void refreshRequestList(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        RequestModifyMapper requestModifyMapper = sqlSession.getMapper(RequestModifyMapper.class);
        RequestModifyExample requestModifyExample = new RequestModifyExample();
        requestModifyExample.or().andValidateEqualTo(false);
        List<RequestModify> requestModifyList =  requestModifyMapper.selectByExample(requestModifyExample);
        if(requestModifyList.size()==0){
            FXHelper.showInfoDialog("没有新的待审核修改请求!");
        }

        tableViewRequestModify.setItems(FXCollections.observableList(requestModifyList));
        sqlSession.close();
    }



    /**
     * 查询统计用户消费信息
     */
    @FXML
    private void searchUserConsume(){
        String userID = textFieldSearchConsumeUserID.getText();
        SqlSession sqlSession = DBAccess.getSqlSession();
        ConsumeMapper consumeMapper = sqlSession.getMapper(ConsumeMapper.class);
        ConsumeExample consumeExample = new ConsumeExample();
        consumeExample.or().andIdLike(userID);
        List<Consume> consumeList = consumeMapper.selectByExample(consumeExample);
        if(consumeList.size()==0){
            FXHelper.showInfoDialog("没有任何匹配的信息!");
        }
        tableViewSearchConsume.setItems(FXCollections.observableList(consumeList));
        sqlSession.close();
    }


    /**
     * 查询统计用户信息
     */
    @FXML
    private void searchUserInfoAndShow(){
        String userName = textFieldSearchInfoName.getText();
        String userOrganization = choiceBoxSearchInfoOrganization.getValue();
        String userType = choiceBoxSearchInfoType.getValue();
        SqlSession sqlSession = DBAccess.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.or();
        criteria.andNameLike("%"+userName+"%")
                .andTypeLike("%"+userType+"%");
        if(!"".equals(userOrganization)){
            OrganizationExample organizationExample = new OrganizationExample();
            organizationExample.or().andNameEqualTo(userOrganization);
            criteria.andOrganizationEqualTo(organizationMapper.selectByExample(organizationExample).get(0).getOrganization_id());
        }
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.size()==0){
            FXHelper.showInfoDialog("没有查询到任何匹配到的信息,查询失败!");
        }
        tableViewSearchInfo.setItems(FXCollections.observableList(userList));

        sqlSession.close();
    }

    /**
     * 销卡功能
     */
    @FXML
    private void deleteUser(){
        String userID = textFieldUserDeleteID.getText();
        if("".equals(userID)){
            FXHelper.showWarningDialog("请输入需要删除的用户编号!");
            textFieldUserDeleteID.requestFocus();
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        BookAccountMapper bookAccountMapper = sqlSession.getMapper(BookAccountMapper.class);
        DormitoryUserMapper dormitoryUserMapper = sqlSession.getMapper(DormitoryUserMapper.class);
        //判断用户是否还有未归还图书,是否还有余额,是否未退宿舍
        User user = userMapper.selectByPrimaryKey(userID);
        if(user==null || !user.getAvailable().equals("正常")){
            FXHelper.showWarningDialog("不存在该用户或已经销户,无法销户!");
        }


        if(user!=null){
            if(user.getBalance().floatValue()!=0.00){
                FXHelper.showWarningDialog("抱歉,还有未消费余额,请先将该余额消费完成或退回才可销卡!");
                return;
            }
        }
        BookAccount bookAccount = bookAccountMapper.selectByPrimaryKey(userID);
        if (bookAccount != null) {
            if(bookAccount.getBorrowedNum()!=0){
                FXHelper.showWarningDialog("该用户还有未归还图书,无法销卡!");
                return;
            }
        }
        DormitoryUserExample dormitoryUserExample = new DormitoryUserExample();
        dormitoryUserExample.or().andUser_idEqualTo(userID);
        List<DormitoryUser> dormitoryUserList = dormitoryUserMapper.selectByExample(dormitoryUserExample);
        if(dormitoryUserList.size()!=0){
            FXHelper.showWarningDialog("抱歉,该用户还有未退还的宿舍,无法销卡!");
            return;
        }

        user.setAvailable("正常销卡");
        if(userMapper.updateByPrimaryKey(user)>0){
            FXHelper.showInfoDialog("销卡(标记)成功!");
            return;
        }

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 重设开卡页相关控件
     */
    @FXML
    private void resetNewUser(){
        textFieldNewUserName.setText("");
        textFieldNewUserPW.setText("");
        textFieldNewUserID.setText("");
        choiceBoxNewUserOrganization.getSelectionModel().selectFirst();
        choiceBoxNewUserSection.getSelectionModel().selectFirst();
    }


    /**
     * 确定开卡(新增用户)
     */
    @FXML
    private void addNewUser(){
        String userID= textFieldNewUserID.getText();
        String userPW = textFieldNewUserPW.getText();
        String userName = textFieldNewUserName.getText();
        String userOrganization = choiceBoxNewUserOrganization.getValue();
        String userSection = choiceBoxNewUserSection.getValue();
        if("".equals(userID)){
            FXHelper.showWarningDialog("请输入用户卡号(编号)");
            textFieldNewUserID.requestFocus();
            return;
        }
        if("".equals(userPW)){
            FXHelper.showWarningDialog("请输入用户密码");
            textFieldNewUserPW.requestFocus();
            return;
        }
        userPW = CodeUtils.getMD5(userPW);
        if("".equals(userName)){
            FXHelper.showWarningDialog("请输入用户姓名!");
            textFieldNewUserName.requestFocus();
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        SectionMapper sectionMapper = sqlSession.getMapper(SectionMapper.class);
        Integer organizationID = null,sectionID = null;
        if(!"".equals(userOrganization)){
            OrganizationExample organizationExample = new OrganizationExample();
            organizationExample.or().andNameEqualTo(userOrganization);
            organizationID = organizationMapper.selectByExample(organizationExample).get(0).getOrganization_id();
        }
        if(!"".equals(userSection)){
            SectionExample sectionExample = new SectionExample();
            sectionExample.or().andNameEqualTo(userSection);
            sectionID = sectionMapper.selectByExample(sectionExample).get(0).getSectionId();
        }




        User user = new User(userID,userPW,"学生","正常",userName,new BigDecimal("0.00"),organizationID,sectionID,null,null,null);
        if(userMapper.insert(user)>0){
            FXHelper.showInfoDialog("添加信息成功!");
            resetNewUser();
        }
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 初始化
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //用户管理选项卡相关
        SqlSession sqlSession = DBAccess.getSqlSession();
        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        List<Organization> organizationList = organizationMapper.selectByExample(new OrganizationExample());
        List<String> newOrganizationList = new ArrayList<>();
        newOrganizationList.add("");
        for(Organization org : organizationList){
            newOrganizationList.add(org.getName());
        }
        choiceBoxNewUserOrganization.setItems(FXCollections.observableList(newOrganizationList));
        if(newOrganizationList.size()>1)
            choiceBoxNewUserOrganization.setValue(newOrganizationList.get(0));
        choiceBoxNewUserOrganization.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    //组织机构选择框被选择后修改对应科室/班级选择框
                    chagneSectionListWhenOrganizationChanged(new_val,choiceBoxNewUserOrganization,choiceBoxNewUserSection);
                }
        );
        chagneSectionListWhenOrganizationChanged(0,choiceBoxNewUserOrganization,choiceBoxNewUserSection); //默认根据第一个组织机构更新Section数据
        sqlSession.close();


        //查询用户信息选项卡
        choiceBoxSearchInfoType.setItems(FXCollections.observableArrayList("","学生","教学系统管理员","图书管理员","宿舍管理员"));
        choiceBoxSearchInfoType.getSelectionModel().selectFirst();
        choiceBoxSearchInfoOrganization.setItems(FXCollections.observableList(newOrganizationList));
        choiceBoxSearchInfoOrganization.getSelectionModel().selectFirst();

        columnUserID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnUserType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnUserAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));
        columnUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnUserBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        columnUserOrganization.setCellValueFactory(new PropertyValueFactory<>("organization"));
        columnUserPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        //查询用户消费信息选项卡
        columnConsumeUserID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnConsumeTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnConsumeMoney.setCellValueFactory(new PropertyValueFactory<>("money"));
        columnConsumeGoodsName.setCellValueFactory(new PropertyValueFactory<>("goodsName"));

        //审批用户修改信息请求选项卡相关
        columnModifyID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnModifyUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        columnModifyPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnModifyEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnModifyAddr.setCellValueFactory(new PropertyValueFactory<>("addr"));

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
}
