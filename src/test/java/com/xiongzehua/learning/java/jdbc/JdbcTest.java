package com.xiongzehua.learning.java.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by xiongzehua on 2019/2/14.
 */
public class JdbcTest {
    @Test
    public void jdbc() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //1 加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2 获取与数据库连接的对象-Connetcion
            connection = DriverManager.getConnection("jdbc:mysql://192.168.145.137:3306/zhifou_test", "root", "123456");

            //3 获取执行sql语句的statement对象
            statement = connection.createStatement();

            //4 执行sql语句,拿到结果集
            resultSet = statement.executeQuery("SELECT * FROM user");
            //5 遍历结果集，得到数据
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            /*
             * 关闭资源，后调用的先关闭
             * 关闭之前，要判断对象是否存在
             * */
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void jdbcWithDataSource() {
        System.out.println(this.getClass().getResource("/druid.properties"));
        System.out.println(this.getClass().getResource("druid.properties"));
        InputStream confile = this.getClass().getResourceAsStream("/druid.properties");//配置文件名称
        Properties properties = new Properties();
        DataSource dataSource = null;
        try {
            properties.load(confile);//加载配置文件
            System.out.println(properties);

            //通过DruidDataSourceFactory获取javax.sql.DataSource
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //1 从连接池中获取连接
            connection = dataSource.getConnection();

            //2 获取执行sql语句的statement对象
            statement = connection.createStatement();

            //3 执行sql语句,拿到结果集
            resultSet = statement.executeQuery("SELECT * FROM user");
            //4 遍历结果集，得到数据
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {

            /*
             * 关闭资源，后调用的先关闭
             * 关闭之前，要判断对象是否存在
             * */
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
