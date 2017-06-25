package ui.controller;

import dao.OrganizationMapper;
import dao.SectionMapper;
import dao.UserMapper;
import db.DBAccess;
import entity.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.ibatis.session.SqlSession;
import utils.FXHelper;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by FXBL on 6/24/2017.
 */
public class ChangeUserInfoController implements Initializable{



    //修改用户信息
    @FXML
    private TextField textField_name,
            textField_phone,
            textField_email,
            textField_addr;


    //修改密码
    @FXML
    private TextField textField_oldpw,
            textField_newpw,
            textField_newpw_again;

    //个人信息查看
    @FXML
    private Label label_info_type,
            label_info_available,
            label_info_name,
            label_info_balance,
            label_info_organization,
            label_info_section,
            label_info_phone,
            label_info_email,
            label_info_addr;



    /**
     * 刷新用户信息
     */
    @FXML
    private void refreshUserInfo(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        entity.User user = userMapper.selectByPrimaryKey(CurrentUser.userID);
        label_info_type.setText(user.getType());
        label_info_available.setText(user.getAvailable());
        label_info_name.setText(user.getName());
        label_info_balance.setText(""+user.getBalance());
        label_info_organization.setText((user.getOrganization()==null?"无组织":sqlSession.getMapper(OrganizationMapper.class).selectByPrimaryKey(user.getOrganization()).getName()));
        label_info_section.setText(user.getSection()==null?"无班级/科室":sqlSession.getMapper(SectionMapper.class).selectByPrimaryKey(user.getSection()).getName());
        label_info_phone.setText(user.getPhone());
        label_info_email.setText(user.getEmail());
        label_info_addr.setText(user.getAddr());
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 确认修改用户信息
     */
    @FXML
    private void confirmNewUserInfo(){
        if(textField_name.getText().equals("")){
            FXHelper.showWarningDialog("请输入姓名!");
            textField_name.requestFocus();
            return;
        }
        if(textField_phone.getText().equals("")){
            FXHelper.showWarningDialog("请输入电话!");
            textField_phone.requestFocus();
            return;
        }
        if(textField_email.getText().equals("")){
            FXHelper.showWarningDialog("请输入邮箱!");
            textField_email.requestFocus();
            return;
        }
        if(textField_addr.getText().equals("")){
            FXHelper.showWarningDialog("请输入家庭住址!");
            textField_addr.requestFocus();
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        entity.User user = userMapper.selectByPrimaryKey(CurrentUser.userID);
        user.setAddr(textField_addr.getText());
        user.setName(textField_name.getText());
        user.setPhone(textField_phone.getText());
        user.setEmail(textField_email.getText());
        userMapper.updateByPrimaryKey(user);
        sqlSession.commit();
        sqlSession.close();
        FXHelper.showInfoDialog("修改用户信息成功!");
    }

    /**
     * 重新读取用户信息
     */
    @FXML
    private void reloadUserInfo(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        entity.User user = userMapper.selectByPrimaryKey(CurrentUser.userID);
        textField_name.setText(user.getName());
        textField_email.setText(user.getEmail());
        textField_addr.setText(user.getAddr());
        textField_phone.setText(user.getPhone());
        sqlSession.close();
    }


    /**
     * 确认修改新密码
     */
    @FXML
    private void confirmNewPW(){
        String oldpw = textField_oldpw.getText();
        String newpw = textField_newpw.getText();
        String newpw_again = textField_newpw_again.getText();
        if(!newpw.equals(newpw_again)){
            FXHelper.showWarningDialog("两次输入密码不一致,请重新输入!");
            textField_newpw_again.requestFocus();
            return;
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        entity.User user = userMapper.selectByPrimaryKey(CurrentUser.userID);
        if(!oldpw.equals(user.getPassword())){
            FXHelper.showWarningDialog("密码验证失败,请重新输入!");
            textField_oldpw.requestFocus();
            return;
        }
        user.setPassword(newpw);
        CurrentUser.password = newpw;
        userMapper.updateByPrimaryKey(user);
        FXHelper.showInfoDialog("修改密码成功!");
        resetPwField(); //清空输入过的密码.
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 重设修改密码输入框
     */
    @FXML
    private void resetPwField(){
        textField_oldpw.setText("");
        textField_newpw.setText("");
        textField_newpw_again.setText("");
    }


    /**
     * 初始化
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

         reloadUserInfo();
         refreshUserInfo();
    }
}
