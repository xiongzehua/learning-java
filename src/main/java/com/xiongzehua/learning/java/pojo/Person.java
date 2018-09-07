package com.xiongzehua.learning.java.pojo;

import com.xiongzehua.learning.java.annotation.MarkA;
import com.xiongzehua.learning.java.annotation.MarkB;

/**
 * Created by xiongzehua on 2018/9/1.
 */
@MarkA("A--Person类")
public class Person {
    public String name;
    public String age;

    @MarkB("B--info方法")
    public String info() {
        return "我叫" + name + "，今年" + age + "岁。";
    }

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Person() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public String getAge() {
        return age;
    }


    // 静态工厂
    public static Person getInstance() {
        return new Person();
    }

}
