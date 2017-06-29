package ui.controller.User;

import dao.*;
import db.DBAccess;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.ibatis.session.SqlSession;
import utils.FXHelper;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

/**
 * Created by FXBL on 6/29/2017.
 */
public class UserController implements Initializable{
    //账户信息选项卡
    @FXML private TextField textFieldBalance;

    //消费查询相关选项卡
    @FXML private TableView<Consume> tableViewConsume;
    @FXML private TableColumn<Consume,String> columnConsumeUserID,columnConsumeTime,columeConsumeMoney,columnConsumeGoodsName;
    @FXML private CheckBox checkBoxConsumeToday;

    //最近充值情况选项卡相关
    @FXML private TextField textFieldTopupDate,textFieldTopupMoney,textFieldTopupCard;

    //消费选项卡相关
    @FXML private TextField textFieldConsumeGoodsName,textFieldConsumeMoney;

    //网上充值一卡通选项卡相关
    @FXML private TextField textFieldTopup_card,textFieldTopup_money;

    //图书查询
    @FXML private TextField textFieldSearchBookName,textFieldSearchBookAuthor,textFieldSearchBookPublisher;
    @FXML private ChoiceBox<String> choiceBoxSearchBookType;
    @FXML private TableView<BookInfo> tableViewBooks;
    @FXML private TableColumn<BookInfo,String> columnBookID,columnBookName,columnBookType,columnBookAuthor,columnBookPublisher,columnBookPubDate,columnBookPrice,columnBookRack,columnBookAmount,columnBookAvailableNum;


    //图书借阅查询
    @FXML private TableView<BookBorrow> tableViewBookBorrow;
    @FXML private TableColumn<BookBorrow,String> columnBorrowedBookID,columnBorrowedTime,columnBorrowedSBacktime;

    //课程查询选项卡相关
    @FXML private TableView<Course> tableViewCourse;
    @FXML private TableColumn<Course,String> columnCourseID,columnCourseName,columnCourseStartDate,columnCourseEndDate,colmnCourseOrganization;

    //退课选项卡相关
    @FXML private TableView<Course> tableViewReturnCourse;
    @FXML private TableColumn<Course,String> columnReturnCourseID,columnReturnCourseName,columnReturnCourseStartDate,columnReturnCourseEndDate,colmnReturnCourseOrganization;

    //成绩选项卡相关
    @FXML private TableView<CourseChoice> tableViewCourseChoice;
    @FXML private TableColumn<CourseChoice,String> columnUserID,columnUserGrade;


    /**
     * 查询成绩
     */
    @FXML
    private void gradeSearch(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        CourseChoiceMapper courseChoiceMapper = sqlSession.getMapper(CourseChoiceMapper.class);
        CourseChoiceExample courseChoiceExample = new CourseChoiceExample();
        courseChoiceExample.or().andUserIdEqualTo(CurrentUser.userID);
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
     * 选中课程退课
     */
    @FXML void returnCourse(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        Course course = tableViewCourse.getSelectionModel().getSelectedItem();
        if(course==null){
            FXHelper.showInfoDialog("还没选中任何课程,无法退课!");
            return;
        }
        CourseChoiceMapper courseChoiceMapper = sqlSession.getMapper(CourseChoiceMapper.class);
        CourseChoiceExample courseChoiceExample = new CourseChoiceExample();
        courseChoiceExample.or().andUserIdEqualTo(CurrentUser.userID)
                .andCourseIdEqualTo(course.getCourseId());
        if(courseChoiceMapper.deleteByExample(courseChoiceExample)>0){
            FXHelper.showInfoDialog("退课成功!");
        }else{
            FXHelper.showInfoDialog("退课失败!");
        }

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 查询选修的课程
     */
    @FXML void searchSelectedCourse(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
        CourseChoiceMapper courseChoiceMapper = sqlSession.getMapper(CourseChoiceMapper.class);
        CourseChoiceExample courseChoiceExample = new CourseChoiceExample();
        courseChoiceExample.or().andUserIdEqualTo(CurrentUser.userID);
        List<CourseChoice> courseChoiceList = courseChoiceMapper.selectByExample(courseChoiceExample);
        List<Integer> courseIdList = new ArrayList<>();
        for(CourseChoice courseChoice:courseChoiceList){
            courseIdList.add(courseChoice.getCourseId());
        }
        CourseExample courseExample = new CourseExample();
        courseExample.or().andCourseIdIn(courseIdList);
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        if(courseList.size()==0){
            FXHelper.showInfoDialog("没有匹配的信息!");
        }
        tableViewReturnCourse.setItems(FXCollections.observableList(courseList));

        sqlSession.close();
    }



    /**
     * 选修选中课程
     */
    @FXML void selectCourse(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        Course course = tableViewCourse.getSelectionModel().getSelectedItem();
        if(course==null){
            FXHelper.showInfoDialog("还没选中任何课程,无法选课!");
            return;
        }
        CourseChoiceMapper courseChoiceMapper = sqlSession.getMapper(CourseChoiceMapper.class);
        CourseChoice courseChoice = new CourseChoice(CurrentUser.userID,course.getCourseId(),null);
        if(courseChoiceMapper.insertSelective(courseChoice)>0){
            FXHelper.showInfoDialog("选修成功!");
        }else{
            FXHelper.showInfoDialog("选修失败!");
        }

        sqlSession.commit();
        sqlSession.close();
    }





    /**
     * 查询所有课程
     */
    @FXML void searchCourse(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
        CourseExample courseExample = new CourseExample();
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        if(courseList.size()==0){
            FXHelper.showInfoDialog("没有匹配的信息!");
        }
        tableViewCourse.setItems(FXCollections.observableList(courseList));

        sqlSession.close();
    }

    /**
     * 归还已借阅图书
     */
    @FXML
    private void returnBorrowedBook(){
        BookBorrow bookBorrow = tableViewBookBorrow.getSelectionModel().getSelectedItem();
        if(bookBorrow==null){
            FXHelper.showWarningDialog("没有选中任何数据我该归还什么呢?");
            return;
        }
        if(bookBorrow.getIfback()){
            FXHelper.showInfoDialog("这本书都还了唉,我该怎么为你再还一次?");
            return;
        }
        bookBorrow.setIfback(true);
        bookBorrow.setBacktime(new Date());

        SqlSession sqlSession = DBAccess.getSqlSession();
        BookAccountMapper bookAccountMapper = sqlSession.getMapper(BookAccountMapper.class);
        BookBorrowMapper bookBorrowMapper = sqlSession.getMapper(BookBorrowMapper.class);
        BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);

        bookBorrowMapper.updateByPrimaryKey(bookBorrow);

        BookAccount bookAccount = bookAccountMapper.selectByPrimaryKey(bookBorrow.getUserId());
        bookAccount.setBorrowedNum((short)(bookAccount.getBorrowedNum() - 1));
        bookAccountMapper.updateByPrimaryKey(bookAccount);

        BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(bookBorrow.getBookId());
        bookInfo.setRemainNum((short)(bookInfo.getRemainNum()+1));
        bookInfoMapper.updateByPrimaryKey(bookInfo);

        FXHelper.showInfoDialog("归还成功!");
        refreshBorrowBooks();

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 刷新已借阅图书
     */
    @FXML
    private void refreshBorrowBooks(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        BookBorrowMapper bookBorrowMapper = sqlSession.getMapper(BookBorrowMapper.class);
        BookBorrowExample bookBorrowExample = new BookBorrowExample();
        bookBorrowExample.or().andUserIdEqualTo(CurrentUser.userID)
                .andIfbackEqualTo(false);
        List<BookBorrow> bookBorrowList = bookBorrowMapper.selectByExample(bookBorrowExample);
        if (bookBorrowList.size()==0){
            FXHelper.showInfoDialog("抱歉,未查询到任何信息!");
        }
        tableViewBookBorrow.setItems(FXCollections.observableList(bookBorrowList));
        sqlSession.close();
    }

    /**
     * 借阅选中图书
     */
    @FXML
    private void borrowSelectedBook(){
        BookInfo bookInfo = tableViewBooks.getSelectionModel().getSelectedItem();
        if(bookInfo==null){
            FXHelper.showWarningDialog("抱歉,您没有选中任何选项,无法借阅!");
            return;
        }
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION,"确认借阅?",ButtonType.YES, ButtonType.CANCEL).showAndWait();
        if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            SqlSession sqlSession = DBAccess.getSqlSession();
            BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
            BookAccountMapper bookAccountMapper = sqlSession.getMapper(BookAccountMapper.class);
            if(bookInfo.getRemainNum()==0){
                FXHelper.showInfoDialog("书籍已经被借阅完,无法借阅");
                return;
            }
            BookAccount bookAccount = bookAccountMapper.selectByPrimaryKey(CurrentUser.userID);
            if(bookAccount.getBorrowedNum()==bookAccount.getMaxBorrowNum()){
                FXHelper.showInfoDialog("抱歉,您的可借阅书籍数量为0,无法借阅");
                return;
            }
            bookAccount.setBorrowedNum((short)(bookAccount.getBorrowedNum()+1));
            bookAccountMapper.updateByPrimaryKey(bookAccount);
            bookInfo.setRemainNum((short)(bookInfo.getRemainNum()+1));
            bookInfoMapper.updateByPrimaryKey(bookInfo);

            //图书借阅表
            BookBorrowMapper bookBorrowMapper = sqlSession.getMapper(BookBorrowMapper.class);
            BookBorrow bookBorrow = new BookBorrow(null,CurrentUser.userID,bookInfo.getBookId(),new Date(),new Date(System.currentTimeMillis()+(30L*24L*60L*60L*1000L)),false,null);
            bookBorrowMapper.insertSelective(bookBorrow);



            bookInfoMapper.deleteByPrimaryKey(bookInfo.getBookId());





            FXHelper.showInfoDialog("借阅成功!");
            sqlSession.commit();
            sqlSession.close();
        }
    }




    /**
     * 查询图书并显示
     */
    @FXML
    private void SearchBooksAndShow(){
        String bookName = textFieldSearchBookName.getText();
        String bookAuthor = textFieldSearchBookAuthor.getText();
        String bookPublisher = textFieldSearchBookPublisher.getText();
        String bookType = choiceBoxSearchBookType.getValue();
        SqlSession sqlSession = DBAccess.getSqlSession();
        BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
        BookInfoExample bookInfoExample = new BookInfoExample();
        bookInfoExample.or().andNameLike("%"+bookName+"%")
                .andAuthorLike("%"+bookAuthor+"%")
                .andPublisherLike("%"+bookPublisher+"%")
                .andTypeLike("%"+bookType+"%");
        List<BookInfo> bookInfoList = bookInfoMapper.selectByExample(bookInfoExample);

        if(bookInfoList.size()==0)
            FXHelper.showInfoDialog("很抱歉,没有匹配到任何结果!\n请修改查询关键词后再试!\n温馨提示:支持模糊查询。");


        tableViewBooks.setItems(FXCollections.observableList(bookInfoList));

        sqlSession.close();
    }

    /**
     * 注销图书馆账号
     */
    @FXML
    private void destoryBookAccount(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        BookAccountMapper bookAccountMapper = sqlSession.getMapper(BookAccountMapper.class);
        BookAccount bookAccount = bookAccountMapper.selectByPrimaryKey(CurrentUser.userID);

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION,"没有图书账户,是否创建?",ButtonType.YES, ButtonType.CANCEL).showAndWait();
        if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.NO)){
            return;
        }


        if(bookAccount==null){
            FXHelper.showWarningDialog("抱歉,该用户未注册,无法注销!");
            return;
        }
        if(bookAccount.getBorrowedNum()!=0){
            FXHelper.showWarningDialog("抱歉,该用户还有未归还图书,无法注销!");
            return;
        }
        bookAccountMapper.deleteByPrimaryKey(bookAccount.getId());
        FXHelper.showInfoDialog("注销(删除)成功!");



        sqlSession.commit();
        sqlSession.close();
    }




    /**
     * 申请图书馆账号/查看账号信息
     */
    @FXML
    private void viewLibraryAccount(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        BookAccountMapper bookAccountMapper = sqlSession.getMapper(BookAccountMapper.class);
        BookAccountExample bookAccountExample = new BookAccountExample();
        bookAccountExample.or().andIdEqualTo(CurrentUser.userID);
        List<BookAccount> bookAccountList = bookAccountMapper.selectByExample(bookAccountExample);
        if(bookAccountList.size()==0){
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION,"没有图书账户,是否创建?",ButtonType.YES, ButtonType.CANCEL).showAndWait();
            if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
                BookAccount bookAccount = new BookAccount();
                bookAccount.setBorrowedNum((short)0);
                bookAccount.setId(CurrentUser.userID);
                bookAccount.setMaxBorrowNum((short)7);
                bookAccount.setRegisterDate(new Date());
                bookAccountMapper.insertSelective(bookAccount);
                FXHelper.showInfoDialog("新建用户成功!");
            }else{
                return;
            }
            BookAccount bookAccount = bookAccountMapper.selectByPrimaryKey(CurrentUser.userID);
            if(bookAccount!=null){
                FXHelper.showInfoDialog("最大借阅数:"+bookAccount.getMaxBorrowNum()+"\n已借阅数:"+bookAccount.getBorrowedNum()+"\n注册日期:"+bookAccount.getRegisterDate());
            }
        }
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 充值一卡通
     */
    @FXML
    private void topup(){
        String card = textFieldTopup_card.getText();
        String money = textFieldTopup_money.getText();
        if("".equals(card)|| "".equals(money)){
            FXHelper.showWarningDialog("请输入用户编号");
        }
        SqlSession sqlSession = DBAccess.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        ConsumeMapper consumeMapper = sqlSession.getMapper(ConsumeMapper.class);
        User user = userMapper.selectByPrimaryKey(CurrentUser.userID);
        user.setBalance(new BigDecimal(user.getBalance().doubleValue()+new BigDecimal(money).doubleValue()));
        userMapper.updateByPrimaryKeySelective(user);

        Consume consume = new Consume();
        consume.setTime(new Date());
        consume.setId(CurrentUser.userID);
        consume.setMoney(new BigDecimal(money).multiply(new BigDecimal("-1")));
        consume.setGoodsName("充值卡号:"+card);
        consumeMapper.insertSelective(consume);

        FXHelper.showInfoDialog("充值成功!");

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 进行消费
     */
    @FXML
    private void consume(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        String goodsName = textFieldConsumeGoodsName.getText();
        String consumeMoney = textFieldConsumeMoney.getText();
        if("".equals(goodsName)||"".equals(consumeMoney)){
            FXHelper.showWarningDialog("请先把消费名称和金额填写完整!");
            textFieldConsumeGoodsName.requestFocus();
            return;
        }
        ConsumeMapper consumeMapper = sqlSession.getMapper(ConsumeMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(CurrentUser.userID);
        if(user.getBalance().doubleValue()<new BigDecimal(consumeMoney).doubleValue()){
            FXHelper.showWarningDialog("抱歉,用户余额不足,无法进行消费!");
            return;
        }
        Consume consume = new Consume();
        consume.setId(user.getId());
        consume.setGoodsName(goodsName);
        consume.setMoney(new BigDecimal(consumeMoney));
        consume.setTime(new Date());
        consumeMapper.insertSelective(consume);
        user.setBalance(new BigDecimal(user.getBalance().doubleValue()-new BigDecimal(consumeMoney).doubleValue()));
        userMapper.updateByPrimaryKeySelective(user);
        FXHelper.showInfoDialog("更新成功!");

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 查询最近充值情况
     */
    @FXML
    private void viewTopup(){
        String time,money,cardNo;   //充值时间,金额,银行卡号

        SqlSession sqlSession = DBAccess.getSqlSession();
        ConsumeMapper consumeMapper = sqlSession.getMapper(ConsumeMapper.class);
        ConsumeExample consumeExample = new ConsumeExample();
        consumeExample.or().andMoneyLessThan(new BigDecimal("0"));
        List<Consume> consumeList = consumeMapper.selectByExample(consumeExample);
        if(consumeList.size()==0){
            time="没有最近充值信息";
            money="没有最近充值信息";
            cardNo="没有最近充值信息";
        }else{
            time=consumeList.get(0).getTime().toString();
            money=consumeList.get(0).getMoney().toString();
            cardNo = consumeList.get(0).getGoodsName();
        }
        textFieldTopupCard.setText(cardNo);
        textFieldTopupDate.setText(time);
        textFieldTopupMoney.setText(money);
        sqlSession.close();
    }

    /**
     * 查询消费
     */
    @FXML
    private void viewConsume(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        ConsumeMapper consumeMapper = sqlSession.getMapper(ConsumeMapper.class);
        ConsumeExample consumeExample = new ConsumeExample();
        if(checkBoxConsumeToday.isSelected()){
            consumeExample.or().andTimeEqualTo(new Date());
        }
        List<Consume> consumeList = consumeMapper.selectByExample(consumeExample);
        if(consumeList.size()==0){
            FXHelper.showInfoDialog("抱歉没有查询到任何信息!");
        }
        tableViewConsume.setItems(FXCollections.observableList(consumeList));

        sqlSession.close();
    }

    /**
     * 查询余额
     */
    @FXML
    private void viewBalance(){
        SqlSession sqlSession = DBAccess.getSqlSession();
        String balance = sqlSession.getMapper(UserMapper.class).selectByPrimaryKey(CurrentUser.userID).getBalance().toString();
        textFieldBalance.setText(balance);
        sqlSession.close();
    }






    /**
     * 初始化
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //消费查询相关
        columnConsumeUserID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnConsumeTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columeConsumeMoney.setCellValueFactory(new PropertyValueFactory<>("money"));
        columnConsumeGoodsName.setCellValueFactory(new PropertyValueFactory<>("goodsName"));

        //图书查询
        ObservableList<String> observableList = FXCollections.observableArrayList("","A马列主义毛邓思想","B哲学","C社会科学总论","D政治、法律","E军事","F经济","G文化、科学、教育、体育","H语言、文字","I文学","J艺术","K历史、地理","N自然科学总论","O数理科学和化学","P天文学、地球科学","Q生物科学","R医药、卫生","S农业科学","T工业技术","U交通运输","V航空、航天","X环境科学、安全科学","Z综合性图书");
        choiceBoxSearchBookType.setItems(observableList);
        choiceBoxSearchBookType.getSelectionModel().selectFirst();
        columnBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        columnBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnBookType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        columnBookPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        columnBookPubDate.setCellValueFactory(new PropertyValueFactory<>("pubDate"));
        columnBookPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnBookRack.setCellValueFactory(new PropertyValueFactory<>("bookrack"));
        columnBookAmount.setCellValueFactory(new PropertyValueFactory<>("totalNum"));
        columnBookAvailableNum.setCellValueFactory(new PropertyValueFactory<>("remainNum"));

        //已借阅的图书
        columnBorrowedBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        columnBorrowedTime.setCellValueFactory(new PropertyValueFactory<>("borrowtime"));
        columnBorrowedSBacktime.setCellValueFactory(new PropertyValueFactory<>("sBacktime"));

        //课程查询相关
        columnCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        columnCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        columnCourseStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        columnCourseEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colmnCourseOrganization.setCellValueFactory(new PropertyValueFactory<>("organizationId"));

        //退课相关
        columnReturnCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        columnReturnCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        columnReturnCourseStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        columnReturnCourseEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colmnReturnCourseOrganization.setCellValueFactory(new PropertyValueFactory<>("organizationId"));

        //成绩查询相关
        columnUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        columnUserGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
    }
}
