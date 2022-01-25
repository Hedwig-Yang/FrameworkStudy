package com.atguigu.mybatis.test;

import com.atguigu.mybatis.beans.Account;
import com.atguigu.mybatis.beans.Book;
import com.atguigu.mybatis.beans.PublicTestBean;
import com.atguigu.mybatis.dao.AccountDao;
import com.atguigu.mybatis.dao.PublicTestBeanDao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 课程：尚硅谷大数据 尚硅谷-第14阶段《mybatis》
 * @Author:KUN
 * @Data:2021/7/20 10:25
 * @Description: 测试Mybatis
 * @Version:1.0
 */
public class MybatisTest {

    /**
     * MyBatis HelloWorld 小结:
     * 	 两个重要的配置文件
     * 		mybatis-config.xml :全局配置文件 , 数据库连接信息、 引入SQL映射文件等....
     * 		EmployeeMapper.xml :SQL映射文件 , 配置增删改查的SQL语句的映射
     *
     *  两个重要的对象
     *  	SqlSessionFactory: SqlSession的工厂对象， 主要是用于获取SqlSession对象
     *  	SqlSession:  Java程序与数据库的会话对象.可以理解为是对Connection的封装.
     */
    @Test
    public void testSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = (new SqlSessionFactoryBuilder()).build(inputStream);
        System.out.println(sqlSessionFactory);
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println(session);

        try {
            /**
             *  statement    Unique identifier matching the statement to use.
             *  			 SQL语句的唯一标识
             *   parameter   A parameter object to pass to the statement.
                             执行SQL需要用到的参数
             */
            Book book1 = (Book) session.selectOne("suibian.selectBook", "ISBN-004");
            System.out.println(book1);
        } finally {
            session.close();
        }
    }


    //测试Mapper接口方式下实现Mybatis
    @Test
    public void  testHelloWorldMapper() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //mapper接口： 本质就是dao接口
            /*
             * 两个绑定:
             * 	 1. Mapper接口与SQL映射文件的绑定,映射文件的namesapce的值必须指定成接口的全类名.
             * 	 2. Mapper接口的方法与SQL映射文件的具体SQL语句的绑定,SQL语句的id值,必须指定成接口的方法名.
             *
             * Mapper接口开发的好处:
             * 	 1. 有更明确的类型
             * 	 2. 接口本身: 接口本身就是抽象. 抽出了规范.
             * 			AccountDao:    AccountDaoJdbcImpl / AccountDaoHibernateImpl / MyBatis代理实现类
             */
            //获取MyBatis为Mapper接口生成的代理实现类对象
            AccountDao  dao = session.getMapper(AccountDao.class);
            System.out.println(dao.getClass().getName());
            Account account  = dao.getBalanceByUsername("Jerry");
            System.out.println(account);
        } finally {
            session.close();
        }
    }

    //测试基于MyBatis框架的Mapper接口的实现方式
    @Test
    public void testCRUD() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try{
            //获取MyBatis为Mapper接口生成的代理实现类对象
            AccountDao  dao = sqlSession.getMapper(AccountDao.class);
            //增加
            Account cavenAccount = new Account("Caven", 2500);
            dao.addAcount(cavenAccount);
            //删除
            dao.deleteAccount("Tom");
            //修改
            Account jeanAccount = new Account("Jean", 5000);
            dao.updateAccount(jeanAccount);
            //查询
            Account stevAccount = dao.getBalanceByUsername("stev");
            System.out.println(stevAccount);

            //提交
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    //测试自荐表public_test_table的CRUD
    @Test
    public void testNewTable() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try{
            PublicTestBeanDao dao = sqlSession.getMapper(PublicTestBeanDao.class);
            //增加
            /*PublicTestBean testBean = new PublicTestBean();
            testBean.setId(3);
            testBean.setUserName("Tom");
            testBean.setAge(16);
            testBean.setGender("男");
            testBean.setSchool("上海音乐学院");
            testBean.setBirthday(new Date(1608393600000L));
            testBean.setAddTime(new Timestamp(1626944670708L));
            testBean.setUpdateTime(new Timestamp(1626948420585L));*/
            //dao.addPublicTestBean(testBean);
            //删除
            //dao.deletePublicTestBeanById(4);
            //修改
            //dao.updatePublicTestBean(testBean);
            //查询
            PublicTestBean publicTestBean = dao.getPublicTestBeanById(1);
            System.out.println(publicTestBean);
            //提交
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    //测试：获取自增主键的主键值
    @Test
    public void testPrimaryKey() throws IOException {
        //测试插入数据后马上获取该数据（怎样避免其他线程的干扰）
        SqlSession sqlSession = getSqlSession();
        try{
            PublicTestBeanDao dao = sqlSession.getMapper(PublicTestBeanDao.class);
            //增加
            PublicTestBean testBean = new PublicTestBean();
            testBean.setUserName("Bob");
            testBean.setAge(46);
            testBean.setGender("男");
            testBean.setSchool("莆田学院");
            testBean.setBirthday(new Date(1608393600000L));
            testBean.setAddTime(new Timestamp(1626951713898L));
            testBean.setUpdateTime(new Timestamp(1626951774545L));
            int i = dao.addPublicTestBean(testBean);
            System.out.println(i);
            System.out.println(testBean.getId());
            //提交，MyBatis内做增删改操作时，需要有一步提交操作，才能生效。也可设置自动提交
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }


    //测试JDBC返回主键值,(MyBatis就是对JDBC的封装)
    @Test
    public void testJDBCReturn() throws Exception {
        Connection conn = getJdbcConnection();
        String sql = "insert into public_test_table (user_name,age,gender,school,birthday,add_time,update_time) values (?,?,?,?,?,?,?)";
        //在此次传入，进行预编译,设置返回自动生成的主键值
        PreparedStatement statement = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1,"Dylon");
        statement.setInt(2,33);
        statement.setString(3,"男");
        statement.setString(4,"帝国理工大学");
        statement.setDate(5,new Date(1608323600000L));
        statement.setTimestamp(6,new Timestamp(1626920713898L));
        statement.setTimestamp(7,new Timestamp(1626920713898L));
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        //得到主键
        generatedKeys.next();
        System.out.println(generatedKeys.getInt(1));
        //关闭资源
        generatedKeys.close();
        statement.close();
        conn.close();
    }

    //测试MyBatis传输参数的类型
    @Test
    public void testReveiveParamer() throws Exception {
        SqlSession sqlSession = getSqlSession();
        PublicTestBeanDao dao = sqlSession.getMapper(PublicTestBeanDao.class);

        //1、单个请求参数
            /*见方法：deletePublicTestBeanById(String id);*/

        //2、几个请求参数,(数量不多，但不属于POJO对象)
        PublicTestBean beanByIdAndAge = dao.getBeanByIdAndAge(7, 33);
        System.out.println("beanByIdAndAge: "+beanByIdAndAge.toString());

        //3、多个请求参数，数量较多，存入Map
        Map map = new HashMap<String,Integer>();
        map.put("id",7);
        map.put("age",33);
        PublicTestBean beanByMap = dao.getBeanByMap(map);
        System.out.println("beanByMap: "+beanByMap.toString());

        //4、多个请求参数属于POJP对象
            /*见方法：addPublicTestBean(PublicTestBean testBean);
             *多个POJO的情况下参照多请求参数，被自动封装成Map对象
             *默认使用Key值伟：0，1，param0，param1,可用@Param在Mapper接口中配置key值  */

        //5、请求参数存入集合Collection或者数组(使用foreach对请求参数遍历)
            /*见四率项目批量插入方法
            * Collection对应的key是collection,Array对应的key是array. 如果确定是List集合，key还可以是list. */
    }










    public SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //打开自动提交
        //return sqlSessionFactory.openSession(true)
        return sqlSessionFactory.openSession();
    }

    public Connection getJdbcConnection(){
        String URL = "jdbc:mysql://127.0.0.1:3306/heima_database?characterEncoding=utf-8";
        String USER = "root";
        String PASSWORD = "root";
        try {
            // 1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获得数据库链接
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }



}


