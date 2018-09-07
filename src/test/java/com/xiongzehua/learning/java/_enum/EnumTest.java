package com.xiongzehua.learning.java._enum;

import org.junit.Test;

/**
 * enum讲解
 *
 * Enum关键字本质是 Class关键字的语法糖，也是用来声明一个类型
 * 但是 Enum声明的类型不能实例化，所有他已经持有几个实例化好的静态成员对象以供使用
 *
 * 例如在 SeasonEnum中，SeasonEnum是类型，SPRING，SUMMER，AUTUMN，WINTER是这个类型的对象
 * 并且 SeasonEnum类型只能有这些对象，不能再自定义 new对象
 * 同时 SPRING这些对象能够和普通的对象一样拥有自己的成员变量和方法
 *
 * Created by xiongzehua on 2018/7/24.
 */
public class EnumTest {

    /**
     * 1.用法一：作为方法参数
     * 方法参数类型的作用是限定，用以告知调用者此方法只能处理某类对象
     * 枚举本质是在这个基础上做了更加严格的限定，用以告知调用者此方法只能处理某几个对象，而不仅仅是某类对象
     */
    @Test
    public void test1() {
        //枚举对象能像正常对象赋值给一个引用，引用的类型是枚举 Enum关键字声明的类型
        SeasonEnum season1 = SeasonEnum.SPRING;
        showSeason(season1);

        //也能通过 类名.静态变量的形式直接使用对象
        showSeason(SeasonEnum.AUTUMN);

        //枚举类的 values()方法可以以数组的形式返回所有枚举成员
        SeasonEnum[] seasonArray = SeasonEnum.values();
        for (int i = 0; i < seasonArray.length; i++) {
            System.out.print(seasonArray[i].getSeasonName() + "，");
        }
    }
    public void showSeason(SeasonEnum season) {
        switch (season) {
            case SPRING: //这里不是season.SPRING
                System.out.println(season.getSeasonName() + "，春暖花开。");
                break;
            case SUMMER:
                System.out.println(season.getSeasonName() + "，骄阳似火。");
                break;
            case AUTUMN:
                System.out.println(season.getSeasonName() + "，秋高气爽。");
                break;
            case WINTER:
                System.out.println(season.getSeasonName() + "，白雪皑皑。");
                break;
        }
    }


    /**
     * 2.用法二：用作 switch语句的参数
     * 枚举比 int和 String更适合作为 switch的输入类型
     * 因为枚举天生便是对象数量有限的，只要 case能涵盖所以枚举对象，便可以不加 default
     */
    @Test
    public void test2() {
        //来代替标志位 然后 if else-if的控制流
        StatusEnum status = StatusEnum.ERROR;


        switch (status) {
            case SUCCESS:
                //成功后的相应逻辑
                break;
            case ERROR:
                //失败后的相应逻辑
                break;
            case UNKNOWN_REASON:
                //未知情况的相应逻辑
                break;
        }
    }


    /**
     * 3.应用三：利用枚举来实现单例
     * 优点：代码量少，不会出现线程安全问题
     */
    @Test
    public void test3() {
        //获取单例对象
        Singleton s1 = Singleton.INSTANCE;
        Singleton s2 = Singleton.INSTANCE;
        //验证是否为单例
        System.out.println(s1 == s2);

        //获取数据库连接
        DBConnection con1 = DataSourceSingleton.INSTANCE.getConnection();
        DBConnection con2 = DataSourceSingleton.INSTANCE.getConnection();
        //验证是否为单例
        System.out.println(con1 == con2);
    }

    /**
     * 4.enum的底层实现：使用class来实现一个 SeasonEnum
     */
    @Test
    public void test4() {

    }
}
