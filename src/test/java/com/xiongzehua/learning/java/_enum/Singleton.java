package com.xiongzehua.learning.java._enum;

/**
 * 只需要一行代码便可实现单例，且不会出现懒汉式类似的线程安全问题
 * enum天生构造函数便是 private，无需手动声明
 * enum手动将构造函数声明为 public，反而会编译报错
 *
 * Created by xiongzehua on 2018/7/31.
 */
public enum Singleton {
    INSTANCE;
}
