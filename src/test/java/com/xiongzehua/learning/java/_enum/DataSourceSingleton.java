package com.xiongzehua.learning.java._enum;

/**
 * 枚举实现单例数据源
 *
 * Created by xiongzehua on 2018/7/31.
 */
public enum DataSourceSingleton {
    INSTANCE;

    private DBConnection con = null;
    private DataSourceSingleton() {
        con = new DBConnection();
    }
    public DBConnection getConnection() {
        return con;
    }
}

/**
 * 模拟数据库连接类
 */
class DBConnection {

}


