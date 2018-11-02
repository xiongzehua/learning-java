package com.xiongzehua.learning.java.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 *
 * Created by xiongzehua on 2018/11/1.
 */
public class TimeTest {
    /**
     * 时间对象
     * LocalDateTime, LocalDate, LocalTime均和 String一样，为 final不可变对象
     */
    @Test
    public void test1() {
        // 1.创建 DateTime对象的两种方式
        LocalDateTime dateTime1 = LocalDateTime.of(2018,7,1,8,13,49);
        LocalDateTime dateTime2 = LocalDateTime.now();
        System.out.println(dateTime1); // "2018-07-01T08:13:49"

        // 2.创建 Date, Time对象的两种方式
        LocalDate date1 = LocalDate.now();
        LocalTime time1 = LocalTime.of(9,22, 37);

        // 3.互相转化
        LocalDateTime dataTime3 = LocalDateTime.of(date1, time1);
        LocalDate date3 = dateTime1.toLocalDate(); // "2018-07-01"
        LocalTime time3 = dateTime1.toLocalTime(); // "08:13:49"
    }

    @Test
    /**
     * 时间基本操作
     */
    public void test2() {
        LocalDateTime dateTime = LocalDateTime.of(2018, 8, 15, 12, 0, 0);

        // 1.获取操作
        int year = dateTime.getYear(); // 2018
        int month = dateTime.getMonth().getValue(); // 8 getMonth()返回的是 Month枚举类型
        int day = dateTime.getDayOfMonth(); // 15
        int dayOfWeek = dateTime.getDayOfWeek().getValue();// 3 getDayOfWeek()返回的是 DayOfWeek枚举类型
        int dayOfYear = dateTime.getDayOfYear(); // 227

        String mouthStr = dateTime.getMonth().toString(); // "AUGUST"
        String dayOfWeekStr = dateTime.getDayOfWeek().toString(); // "WEDNESDAY"

        // 2. final只是代表创建后不能修改，不代表不能创建一样的
        LocalDateTime dateTime1 = LocalDateTime.of(2018, 8, 15, 12, 0, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2018, 9, 23, 10, 10, 10);
        boolean b1 = dateTime == dateTime1; // false
        boolean b2 =  dateTime.equals(dateTime1); // true

        // 3.判断操作
        boolean b3 =  dateTime1.isBefore(dateTime2); // true
        boolean b4 = dateTime1.isAfter(dateTime2); // false
        boolean b5 = dateTime.isEqual(dateTime1); // true

        // 4.时间差  Duration-time上的差距  Period-date上的差距
        // Duration会进行向下取整，一共 23小时也算 0D
        Period period = Period.between(dateTime1.toLocalDate(), dateTime2.toLocalDate()); // "P1M8D"
        Duration duration = Duration.between(dateTime1, dateTime2); // "PT934H10M10S"
        long days = duration.toDays(); // 38
        long hours = duration.toHours(); // 934
        long minutes = duration.toMinutes(); // 56050
        long seconds = duration.getSeconds(); // 3363010
    }

    /**
     * 时间格式化
     */
    @Test
    public void test3() {
        LocalDateTime dateTime = LocalDateTime.of(2018, 8, 15, 13, 13, 13);

        // 1.创建 formatter对象
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("北京时间 yyyy年MM月dd日 下午hh点mm分ss秒");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("北京时间 yyyy年MM月dd日 HH点mm分ss秒");
        // 2.处理时间对象
        String timeStr1 = formatter1.format(dateTime); // "北京时间 2018年08月15日 下午01点13分13秒"
        String timeStr2 = formatter2.format(dateTime); // "北京时间 2018年08月15日 13点13分13秒"
    }

    /**
     * 时间调整
     * 由于时间类均为 final修饰，所以不能使用set方法，所有时间调整均是返回一个新的时间对象。
     * 所有时间类均继承 Temporal接口
     * Temporal接口提供时间调整相关的 plus, minus, with方法
     */
    @Test
    public void test4() {
        // "2018-08-15T13:13:13"
        LocalDateTime dateTime = LocalDateTime.of(2018, 8, 15, 13, 13, 13);

        // 1. 增减调整
        LocalDateTime dateTime1 = dateTime.minusWeeks(1); // "2018-08-08T13:13:13"
        LocalDateTime dateTime2 = dateTime.plusMinutes(2); // "2018-08-15T13:15:13"
        LocalDateTime dateTime3 = dateTime.plusMonths(3); // "2018-11-15T13:13:13"

        // 2. 使用默认时间调整器（TemporalAdjuster）
        // TemporalAdjusters是一个静态工厂类，能生成不同的时间调整器
        LocalDateTime dateTime4 = dateTime.with(TemporalAdjusters.lastDayOfMonth()); // "2018-08-31T13:13:13"
        LocalDateTime dateTime5 = dateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); // "2018-08-20T13:13:13"

        // 3. 使用自定义的时间调整器（TemporalAdjuster），实现调整为下周一
        LocalDateTime dateTime6 = dateTime.with(temporal -> {
            LocalDateTime dt = (LocalDateTime)temporal;
            int duration = 8 - dt.getDayOfWeek().getValue();
            return dt.plusDays(duration);
        }); // "2018-08-20T13:13:13"
    }

    /**
     * 时间戳
     */
    @Test
    public void test5() throws InterruptedException {
        // 1.获取时间戳对象
        Instant instant = Instant.ofEpochSecond(1_000_000, 1111); // "1970-01-12T13:46:40.000001111Z"
        Instant instantNow = Instant.now(); // "2018-11-02T14:00:26.330Z"

        // 2.获取时间戳数值
        // get方式是获取时间对应单位的那部分，比如 1小时 36分用 getMinute为 36
        // to方法是把整个时间换算为那个单位，比如 1小时 36分用 toMinute为 96
        long seconds = instant.getEpochSecond(); // 1000000
        long millis = instant.toEpochMilli();    // 1000000000
        long nanos = instant.getNano(); // 1111

        // 3.计算程序耗时常用操作
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println(end - start); // 1001
    }
}
