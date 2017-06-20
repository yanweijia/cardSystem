package db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by weijia on 2017-05-04.
 * Mybatis访问数据库
 */
public class DBAccess {
    private static Reader reader = null;
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final Logger logger = LogManager.getLogger(DBAccess.class);

    static{
        try {
            // 通过配置文件获取数据库连接信息
            reader = Resources.getResourceAsReader("config/mybatis/mybatisConfig.xml");
        } catch (IOException e) {
            logger.error("获取Mybatis配置文件出错",e);
        }
        // 通过配置信息构建一个SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * 获取一个SqlSession
     * @return
     */
    public static SqlSession getSqlSession(){
        // 通过sqlSessionFactory打开一个数据库会话
        return sqlSessionFactory.openSession();
    }

    /**
     * 插入数据
     * @param s map名字
     * @param o 参数封装
     * @return
     */
    public static int insert(String s,Object o){
        int effectedRows = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = DBAccess.getSqlSession();
            effectedRows = insert(sqlSession,s,o);
            sqlSession.commit();
        }catch(Exception e){
            logger.error("sql插入出错,详细信息:" ,e);
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
        return effectedRows;
    }
    public static int insert(SqlSession sqlSession,String s,Object o) {
        return sqlSession.insert(s, o);
    }

    /**
     * 更新数据
     * @param s xml中的名称
     * @param o 参数封装
     * @return
     */
    public static int update(String s,Object o){
        int effectedRows = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = DBAccess.getSqlSession();
            effectedRows = update(sqlSession,s,o);
            sqlSession.commit();
        }catch(Exception e){
            logger.error("sql更新出错,详细信息" , e);
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
        return effectedRows;
    }
    public static int update(SqlSession sqlSession,String s,Object o) {
        return sqlSession.update(s,o);
    }
    /**
     * 删除数据
     * @param s xml中对应sql语句名
     * @param o 参数封装
     * @return
     */
    public static int delete(String s,Object o){
        int effectedRows = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = DBAccess.getSqlSession();
            effectedRows = delete(sqlSession,s,o);
            sqlSession.commit();
        }catch(Exception e){
            logger.error("sql删除出错,详细信息为",e);
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
        return effectedRows;
    }
    public static int delete(SqlSession sqlSession,String s,Object o) {
        return sqlSession.delete(s,o);
    }
    /**
     * 查询信息
     * @param s xml中对应sql语句名
     * @param o 参数封装
     * @return
     */
    public static List selectList(String s,Object o){
        List list = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = DBAccess.getSqlSession();
            list = selectList(sqlSession,s,o);
        }catch(Exception e){
            logger.error("sql执行出错,无法selectList",e);
        }finally {
            sqlSession.close();
        }
        return list;
    }
    public static List selectList(SqlSession sqlSession,String s,Object o) throws Exception{
        return sqlSession.selectList(s,o);
    }

}
