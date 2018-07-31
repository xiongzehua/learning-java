package com.xiongzehua.learning.java.Enum;

/**
 * 枚举实现单例类
 *
 * 枚举类可以增加成员变量和相应的方法
 * 这时声明枚举成员就需要用类似构造函数的形式--枚举成员名(声明的相关参数)
 *
 * Created by xiongzehua on 2018/7/24.
 */
public enum SeasonEnum {
    SPRING("春天"), SUMMER("夏天"), AUTUMN("秋天"), WINTER("冬天");

    private final String seasonName;
    SeasonEnum(String seasonName) {
        this.seasonName = seasonName;
    }
    String getSeasonName() {
        return seasonName;
    }
}
