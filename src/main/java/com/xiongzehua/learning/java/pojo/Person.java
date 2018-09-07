package com.xiongzehua.learning.java.pojo;

import com.xiongzehua.learning.java.annotation.MarkA;
import com.xiongzehua.learning.java.annotation.MarkB;

/**
 * Created by xiongzehua on 2018/9/1.
 */
@MarkA("A--Person类")
public class Person {
    public String name;
    public int age;

    @MarkB("B--info方法")
    public String info() {
        return "我叫" + name + "，今年" + age + "岁。";
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
        System.out.println("如果输出了这句话，说明反射newInstance底层调用了new。");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public int getAge() {
        return age;
    }


    // 静态工厂
    public static Person getInstance() {
        return new Person();
    }

}
