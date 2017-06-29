package ui.controller.User;

import dao.BookAccountMapper;
import dao.ConsumeMapper;
import dao.SectionMapper;
import dao.UserMapper;
import db.DBAccess;
import entity.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.ibatis.session.SqlSession;
import utils.FXHelper;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
     * 查询图书并显示
     */
    @FXML
    private void SearchBooksAndShow(){

    }
    /**
     * 借阅选中图书
     */
    @FXML
    private void borrowSelectedBook(){

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

    }
}
