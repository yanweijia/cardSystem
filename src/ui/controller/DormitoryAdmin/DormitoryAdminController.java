package ui.controller.DormitoryAdmin;

import dao.DormitoryApplyMapper;
import dao.DormitoryMapper;
import dao.DormitoryUserMapper;
import db.DBAccess;
import entity.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.ibatis.session.SqlSession;
import utils.FXHelper;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by FXBL on 6/29/2017.
 */
public class DormitoryAdminController implements Initializable{

    //审批入住选项卡相关
    @FXML private TableView<DormitoryApply> tableViewApply;
    @FXML private TableColumn<DormitoryApply,String> columnApplyID,columnApplyUserID,columnApplyBuildingNo,columnApplyDormitoryNo,columnApplyTime;

    //退宿
    @FXML private TextField textFieldReturnDormitoryUserID;

    //查看入住信息
    @FXML private TableView<DormitoryUser> tableViewDormitoryUser;
    @FXML private TableColumn<DormitoryUser,String> columnDormitoryBuildingNo,columnDormitoryNo,columnDormitoryBedNo,columnDormitoryUserID;

    /**
     * 查询宿舍入住情况
     */
    @FXML void btnQueryDormitoryUser(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        List<DormitoryUser> dormitoryUserList = sqlSession.getMapper(DormitoryUserMapper.class).selectByExample(new DormitoryUserExample());
        if(dormitoryUserList.size()==0){
            FXHelper.showInfoDialog("抱歉,没有查到任何结果");
        }
        tableViewDormitoryUser.setItems(FXCollections.observableList(dormitoryUserList));

        sqlSession.close();
    }




    /**
     * 确认退宿
     */
    @FXML
    private void btnReturnDormitory(){
        String userID = textFieldReturnDormitoryUserID.getText();
        if("".equals(userID)){
            FXHelper.showWarningDialog("请输入用户编号!");
            textFieldReturnDormitoryUserID.requestFocus();
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        DormitoryUserMapper dormitoryUserMapper = sqlSession.getMapper(DormitoryUserMapper.class);
        DormitoryMapper dormitoryMapper = sqlSession.getMapper(DormitoryMapper.class);
        DormitoryUserExample dormitoryUserExample = new DormitoryUserExample();
        dormitoryUserExample.or().andUser_idEqualTo(userID);
        List<DormitoryUser> dormitoryUserList = dormitoryUserMapper.selectByExample(dormitoryUserExample);
        if(dormitoryUserList.size()==0){
            FXHelper.showInfoDialog("抱歉,该用户没有住宿,无法退宿!");
        }else{
            dormitoryUserList.get(0).getBuilding_no();
            dormitoryUserList.get(0).getDormitory_no();
            dormitoryUserMapper.deleteByExample(dormitoryUserExample);
            Dormitory dormitory = dormitoryMapper.selectByPrimaryKey(new DormitoryKey(dormitoryUserList.get(0).getBuilding_no(),dormitoryUserList.get(0).getDormitory_no()));
            dormitory.setNowNum((short)(dormitory.getNowNum()-1));
            dormitoryMapper.updateByPrimaryKey(dormitory);
            FXHelper.showInfoDialog("退宿成功!");
        }
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 驳回入住申请
     */
    @FXML
    private void btnRejectApply(){
        DormitoryApply dormitoryApply = tableViewApply.getSelectionModel().getSelectedItem();
        if(dormitoryApply==null){
            FXHelper.showWarningDialog("抱歉,您没有选中任何项目,无法操作!");
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        DormitoryApplyMapper dormitoryApplyMapper = sqlSession.getMapper(DormitoryApplyMapper.class);
        dormitoryApply.setResult(true);
        dormitoryApplyMapper.updateByPrimaryKeySelective(dormitoryApply);
        FXHelper.showInfoDialog("驳回成功!");
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 入住申请选中项通过审批
     */
    @FXML
    private void btnPassApply(){
        DormitoryApply dormitoryApply = tableViewApply.getSelectionModel().getSelectedItem();
        if(dormitoryApply==null){
            FXHelper.showWarningDialog("抱歉,您没有选中任何项目,无法操作!");
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        DormitoryApplyMapper dormitoryApplyMapper = sqlSession.getMapper(DormitoryApplyMapper.class);
        dormitoryApply.setResult(true);
        DormitoryMapper dormitoryMapper = sqlSession.getMapper(DormitoryMapper.class);
        Dormitory dormitory = dormitoryMapper.selectByPrimaryKey(new DormitoryKey(dormitoryApply.getBuildingNo(),dormitoryApply.getDormitoryNo()));
        if(dormitory.getMaxNum()>dormitory.getNowNum()){
            dormitory.setNowNum((short)(dormitory.getNowNum()+1));
            dormitoryMapper.updateByPrimaryKey(dormitory);

            DormitoryUserMapper dormitoryUserMapper = sqlSession.getMapper(DormitoryUserMapper.class);
            DormitoryUser dormitoryUser = new DormitoryUser();
            dormitoryUser.setBuilding_no(dormitoryApply.getBuildingNo());
            dormitoryUser.setDormitory_no(dormitoryApply.getDormitoryNo());
            dormitoryUser.setBuilding_no("随便哪个床");
            dormitoryUser.setUser_id(dormitoryApply.getUserId());
            dormitoryUserMapper.insert(dormitoryUser);

            FXHelper.showInfoDialog("审批成功,该用户已成功入住!");
        }else{
            FXHelper.showWarningDialog("抱歉该宿舍已经住满了人,没法继续住人了,审批失败!已删除审批单!");
            return;
        }
        dormitoryApplyMapper.updateByPrimaryKeySelective(dormitoryApply);

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 查看入住申请
     */
    @FXML
    private void btnViewApply(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        DormitoryApplyMapper dormitoryApplyMapper = sqlSession.getMapper(DormitoryApplyMapper.class);
        DormitoryApplyExample dormitoryApplyExample = new DormitoryApplyExample();
        dormitoryApplyExample.or().andResultEqualTo(false);
        List<DormitoryApply> dormitoryApplyList = dormitoryApplyMapper.selectByExample(dormitoryApplyExample);
        if(dormitoryApplyList.size()==0){
            FXHelper.showInfoDialog("当前没有待审批项目!");
        }
        tableViewApply.setItems(FXCollections.observableList(dormitoryApplyList));
        sqlSession.close();
    }



    /**
     * 初始化
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //审批入住选项卡相关
        columnApplyID.setCellValueFactory(new PropertyValueFactory<>("applyId"));
        columnApplyUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        columnApplyBuildingNo.setCellValueFactory(new PropertyValueFactory<>("buildingNo"));
        columnApplyDormitoryNo.setCellValueFactory(new PropertyValueFactory<>("dormitoryNo"));
        columnApplyTime.setCellValueFactory(new PropertyValueFactory<>("applyTime"));


        //查看入住信息选项卡相关
        columnDormitoryBuildingNo.setCellValueFactory(new PropertyValueFactory<>("building_no"));
        columnDormitoryNo.setCellValueFactory(new PropertyValueFactory<>("dormitory_no"));
        columnDormitoryBedNo.setCellValueFactory(new PropertyValueFactory<>("bed_no"));
        columnDormitoryUserID.setCellValueFactory(new PropertyValueFactory<>("user_id"));
    }
}
