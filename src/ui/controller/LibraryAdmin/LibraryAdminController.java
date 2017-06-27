package ui.controller.LibraryAdmin;

import dao.BookAccountMapper;
import dao.BookBorrowMapper;
import dao.BookInfoMapper;
import db.DBAccess;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.ibatis.session.SqlSession;
import utils.DateUtils;
import utils.FXHelper;


import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by FXBL on 6/23/2017.
 */
public class LibraryAdminController implements Initializable{

    //图书查询选项卡相关控件
    @FXML private TextField textFieldSearchBookName,textFieldSearchBookAuthor,textFieldSearchBookPublisher;
    @FXML private ChoiceBox<String> choiceBoxSearchBookType;
    @FXML private TableView<BookInfo> tableViewBooks;
    @FXML private TableColumn<BookInfo,String> columnBookID,columnBookName,columnBookType,columnBookAuthor,columnBookPublisher,columnBookPubDate,columnBookPrice,columnBookRack,columnBookAmount,columnBookAvailableNum;

    //新增图书选项卡相关控件
    @FXML private TextField textFieldNewBookName,textFieldNewBookAuthor,textFieldNewBookPublisher,textFieldNewBookPubDate,textFieldNewBookPrice,textFieldNewBookISBN,textFieldNewBookRack,textFieldNewBookAmount, textFieldNewBookRemain;
    @FXML private ChoiceBox<String> choiceBoxNewBookType;

    //借阅查询/手动还书相关控件
    @FXML private TextField textFieldBorrowID,textFieldBorrowerID;
    @FXML private ChoiceBox<String> choiceBoxBorrowIfBack;
    @FXML private TableView<BookBorrow> tableViewBookBorrow;
    @FXML private TableColumn<BookBorrow,String> columnBorrowID,columnBorrowerID,columnBorrowBookID,columnBorrowTime,columnBorrowSBackTime,columnBorrowIfBack,columnBorrowBackTime;


    /**
     * 手动归还选中借阅情况
     */
    @FXML
    private void returnBorrowedBook(){
        BookBorrow bookBorrow = tableViewBookBorrow.getSelectionModel().getSelectedItem();
        if(bookBorrow==null){
            FXHelper.showWarningDialog("没有选中任何数据我该删除什么呢?");
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
        queryBookBorrow();

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 查询书籍借阅情况
     */
    @FXML
    private void queryBookBorrow(){
        String id = textFieldBorrowID.getText();
        String borrowerID = textFieldBorrowerID.getText();
        String ifBack = choiceBoxBorrowIfBack.getValue();

        SqlSession sqlSession = DBAccess.getSqlSession();
        BookBorrowMapper bookBorrowMapper = sqlSession.getMapper(BookBorrowMapper.class);
        BookBorrowExample bookBorrowExample = new BookBorrowExample();
        BookBorrowExample.Criteria criteria = bookBorrowExample.or().andUserIdLike("%"+borrowerID+"%");
        if(!"".equals(id)){
            criteria.andIdEqualTo(Integer.parseInt(id));
        }
        if("是".equals(ifBack)){
            criteria.andIfbackEqualTo(true);
        }else{
            criteria.andIfbackEqualTo(false);
        }
        List<BookBorrow> bookBorrowList = bookBorrowMapper.selectByExample(bookBorrowExample);
        if(bookBorrowList.size()==0)
            FXHelper.showInfoDialog("很抱歉,没有匹配到任何结果!\n请修改查询关键词后再试!\n温馨提示:支持模糊查询。");
        tableViewBookBorrow.setItems(FXCollections.observableList(bookBorrowList));
        sqlSession.close();
    }



    /**
     * 重设新增图书控件
     */
    @FXML
    private void resetBookInfo(){
        textFieldNewBookName.setText("");
        choiceBoxNewBookType.getSelectionModel().selectLast();
        textFieldNewBookAuthor.setText("");
        textFieldNewBookPublisher.setText("");
        textFieldNewBookPubDate.setText("");
        textFieldNewBookPrice.setText("");
        textFieldNewBookISBN.setText("");
        textFieldNewBookRack.setText("");
        textFieldNewBookAmount.setText("");
        textFieldNewBookRemain.setText("");
    }


    /**
     * 添加新书籍
     */
    @FXML
    private void addNewBookInfo(){
        String bookName = textFieldNewBookName.getText();
        String bookType = choiceBoxNewBookType.getValue();
        String bookAuthor = textFieldNewBookAuthor.getText();
        String bookPublisher = textFieldNewBookPublisher.getText();
        String bookPubDate = textFieldNewBookPubDate.getText();
        String bookPrice = textFieldNewBookPrice.getText();
        String bookISBN = textFieldNewBookISBN.getText();
        String bookRack = textFieldNewBookRack.getText();
        Short bookAmount = Short.parseShort(textFieldNewBookAmount.getText());
        Short bookRemain = Short.parseShort(textFieldNewBookRemain.getText());
        BookInfo bookInfo = new BookInfo(null,bookName,bookType,bookAuthor,bookPublisher,DateUtils.parseDate(bookPubDate), new Date(),new BigDecimal(bookPrice),bookISBN,bookRack,bookAmount,bookRemain);

        SqlSession sqlSession = DBAccess.getSqlSession();
        BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
        if(bookInfoMapper.insertSelective(bookInfo)!=0) {
            FXHelper.showInfoDialog("新增成功!");
            resetBookInfo();
        }else{
            FXHelper.showWarningDialog("新增失败!");
        }

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 删除选中图书
     */
    @FXML
    private void deleteSelectedBook(){
        BookInfo bookInfo = tableViewBooks.getSelectionModel().getSelectedItem();
        if(bookInfo==null){
            FXHelper.showWarningDialog("抱歉,您没有选中任何选项,无法删除!");
            return;
        }
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION,"确认删除?",ButtonType.YES, ButtonType.CANCEL).showAndWait();
        if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            SqlSession sqlSession = DBAccess.getSqlSession();
            BookInfoMapper bookInfoMapper = sqlSession.getMapper(BookInfoMapper.class);
            bookInfoMapper.deleteByPrimaryKey(bookInfo.getBookId());
            FXHelper.showInfoDialog("删除成功!");
            sqlSession.commit();
            sqlSession.close();
        }
    }





    /**
     * 查询图书
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
     * 界面初始化
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> observableList = FXCollections.observableArrayList("","A马列主义毛邓思想","B哲学","C社会科学总论","D政治、法律","E军事","F经济","G文化、科学、教育、体育","H语言、文字","I文学","J艺术","K历史、地理","N自然科学总论","O数理科学和化学","P天文学、地球科学","Q生物科学","R医药、卫生","S农业科学","T工业技术","U交通运输","V航空、航天","X环境科学、安全科学","Z综合性图书");

        //查询界面
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

        //新增图书相关控件
        choiceBoxNewBookType.setItems(FXCollections.observableList(observableList.subList(1,observableList.size())));
        choiceBoxNewBookType.getSelectionModel().selectLast();

        //借阅查询/手动还书相关控件
        choiceBoxBorrowIfBack.setItems(FXCollections.observableArrayList("全部","是","否"));
        choiceBoxBorrowIfBack.getSelectionModel().selectFirst();
        columnBorrowID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnBorrowerID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        columnBorrowBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        columnBorrowTime.setCellValueFactory(new PropertyValueFactory<>("borrowtime"));
        columnBorrowSBackTime.setCellValueFactory(new PropertyValueFactory<>("sBacktime"));
        columnBorrowIfBack.setCellValueFactory(new PropertyValueFactory<>("ifback"));
        columnBorrowBackTime.setCellValueFactory(new PropertyValueFactory<>("backtime"));
    }
}
