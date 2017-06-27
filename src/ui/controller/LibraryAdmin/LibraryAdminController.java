package ui.controller.LibraryAdmin;

import dao.BookInfoMapper;
import db.DBAccess;
import entity.BookInfo;
import entity.BookInfoExample;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
 * Created by FXBL on 6/23/2017.
 */
public class LibraryAdminController implements Initializable{

    //图书查询选项卡相关控件
    @FXML private TextField textFieldSearchBookName,textFieldSearchBookAuthor,textFieldSearchBookPublisher;
    @FXML private ChoiceBox<String> choiceBoxSearchBookType;
    @FXML private TableView<BookInfo> tableViewBooks;
    @FXML private TableColumn<BookInfo,String> columnBookID,columnBookName,columnBookType,columnBookAuthor,columnBookPublisher,columnBookPubDate,columnBookPrice,columnBookRack,columnBookAmount,columnBookAvailableNum;


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

        if(bookInfoList.size()==0){
            FXHelper.showInfoDialog("很抱歉,没有匹配到任何结果!\n请修改查询关键词后再试!\n温馨提示:支持模糊查询。");
            return;
        }

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
        //查询界面
        choiceBoxSearchBookType.setItems(FXCollections.observableArrayList("","A马列主义毛邓思想","B哲学","C社会科学总论","D政治、法律","E军事","F经济","G文化、科学、教育、体育","H语言、文字","I文学","J艺术","K历史、地理","N自然科学总论","O数理科学和化学","P天文学、地球科学","Q生物科学","R医药、卫生","S农业科学","T工业技术","U交通运输","V航空、航天","X环境科学、安全科学","Z综合性图书"));
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
    }
}
