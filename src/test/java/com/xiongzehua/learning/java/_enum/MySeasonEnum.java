package com.xiongzehua.learning.java._enum;

/**
 * Created by xiongzehua on 2018/7/31.
 */
public class MySeasonEnum {
    public static MySeasonEnum SPRING = new MySeasonEnum("春天");
    public static MySeasonEnum SUMMER = new MySeasonEnum("夏天");
    public static MySeasonEnum AUTUMN = new MySeasonEnum("秋天");
    public static MySeasonEnum WINTER = new MySeasonEnum("冬天");

    private final String seasonName;
    private MySeasonEnum(String seasonName) {
        this.seasonName = seasonName;
    }
    public String getSeasonName() {
        return seasonName;
    }
}
