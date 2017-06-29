package ui.controller.CourseAdmin;

import dao.CourseChoiceMapper;
import dao.CourseMapper;
import dao.OrganizationMapper;
import db.DBAccess;
import entity.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.ibatis.session.SqlSession;
import utils.FXHelper;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by FXBL on 6/29/2017.
 */
public class CourseAdminController implements Initializable{

    //课程查询选项卡相关
    @FXML private TextField textFieldCourseName;
    @FXML private TableView<Course> tableViewCourse;
    @FXML private TableColumn<Course,String> columnCourseID,columnCourseName,columnCourseStartDate,columnCourseEndDate,colmnCourseOrganization;

    //开设课程选项卡相关
    @FXML private TextField textFieldNewCourseID,textFieldNewCourseName;
    @FXML private DatePicker datePickerStartDate,datePickerEndDate;
    @FXML private ChoiceBox<String> choiceBoxNewCourseOrganization;

    //上课名单查询选项卡相关
    @FXML private TextField textFieldGradeSearchID;
    @FXML private TableView<CourseChoice> tableViewCourseChoice;
    @FXML private TableColumn<CourseChoice,String> columnUserID,columnUserGrade;


    /**
     * 确定录入成绩
     */
    @FXML
    private void confirmChangeGrade(){
        FXHelper.showInfoDialog("要不先手动在数据库修改一下?");
        return;
    }


    /**
     * 查询上课名单,修改成绩等
     */
    @FXML
    private void gradeSearchAndChange(){
        String strcourseID = textFieldGradeSearchID.getText();
        if("".equals(strcourseID)){
            FXHelper.showInfoDialog("抱歉,请输入课程编号!");
            return;
        }
        Integer courseID = Integer.parseInt(strcourseID);
        SqlSession sqlSession = DBAccess.getSqlSession();
        CourseChoiceMapper courseChoiceMapper = sqlSession.getMapper(CourseChoiceMapper.class);
        CourseChoiceExample courseChoiceExample = new CourseChoiceExample();
        courseChoiceExample.or().andCourseIdEqualTo(courseID);
        List<CourseChoice> courseChoiceList = courseChoiceMapper.selectByExample(courseChoiceExample);
        if(courseChoiceList.size()==0){
            FXHelper.showInfoDialog("抱歉,没有匹配的结果!");
        }else{
            tableViewCourseChoice.setItems(FXCollections.observableList(courseChoiceList));
        }



        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 重设开设课程相关控件
     */
    @FXML
    private void resetNewCourse(){
        textFieldNewCourseName.setText("");
        textFieldNewCourseID.setText("");
        datePickerEndDate.getEditor().setText("");
        datePickerStartDate.getEditor().setText("");
        choiceBoxNewCourseOrganization.getSelectionModel().selectFirst();
    }



    /**
     * 确定开设课程
     */
    @FXML
    private void btnConfirmCourse() throws ParseException {
        String courseID = textFieldNewCourseID.getText();
        String courseName = textFieldNewCourseName.getText();

        String courseOrganization = choiceBoxNewCourseOrganization.getValue();
        if("".equals(courseID)){
            FXHelper.showWarningDialog("请输入课程编号!");
            textFieldNewCourseID.requestFocus();
            return;
        }
        if("".equals(courseName)){
            FXHelper.showWarningDialog("请输入课程名称!");
            textFieldNewCourseName.requestFocus();
            return;
        }
        if("".equals(datePickerStartDate.getEditor().getText())){
            FXHelper.showWarningDialog("请输入开始时间!");
            datePickerStartDate.requestFocus();
            return;
        }
        if("".equals(datePickerStartDate.getEditor().getText())){
            FXHelper.showWarningDialog("请输入结束时间!");
            datePickerEndDate.requestFocus();
            return;
        }

        Date courseStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(((LocalDate)(datePickerStartDate.getValue())).toString());
        Date courseEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(((LocalDate)(datePickerEndDate.getValue())).toString());


        SqlSession sqlSession = DBAccess.getSqlSession();
        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        OrganizationExample organizationExample = new OrganizationExample();
        organizationExample.or().andNameEqualTo(courseOrganization);
        Course course = new Course(Integer.parseInt(courseID),courseName,courseStartDate,courseEndDate,organizationMapper.selectByExample(organizationExample).get(0).getOrganization_id());
        if(courseMapper.insertSelective(course)!=0){
            FXHelper.showInfoDialog("插入成功!");
        }else{
            FXHelper.showWarningDialog("插入失败!");
        }
        resetNewCourse();
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 模糊查询课程名称
     */
    @FXML
    private void searchCourse(){
        String courseName = textFieldCourseName.getText();
        SqlSession sqlSession = DBAccess.getSqlSession();
        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
        CourseExample courseExample = new CourseExample();
        courseExample.or().andCourseNameLike("%"+courseName+"%");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        if(courseList.size()==0){
            FXHelper.showInfoDialog("没有匹配的信息!");
        }
        tableViewCourse.setItems(FXCollections.observableList(courseList));

        sqlSession.close();
    }


    /**
     * 初始化
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //课程模糊查询相关
        columnCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        columnCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        columnCourseStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        columnCourseEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colmnCourseOrganization.setCellValueFactory(new PropertyValueFactory<>("organizationId"));

        //新建课程相关
        //用户管理选项卡相关
        SqlSession sqlSession = DBAccess.getSqlSession();
        OrganizationMapper organizationMapper = sqlSession.getMapper(OrganizationMapper.class);
        List<Organization> organizationList = organizationMapper.selectByExample(new OrganizationExample());
        List<String> newOrganizationList = new ArrayList<>();
        for(Organization org : organizationList){
            newOrganizationList.add(org.getName());
        }
        choiceBoxNewCourseOrganization.setItems(FXCollections.observableList(newOrganizationList));
        if(newOrganizationList.size()>1)
            choiceBoxNewCourseOrganization.setValue(newOrganizationList.get(0));
        sqlSession.close();

        //上课名单查询,成绩录入选项卡相关

        columnUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        columnUserGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

    }
}
