package com.xiongzehua.learning.java.pojo;

import com.xiongzehua.learning.java.annotation.MarkA;
import com.xiongzehua.learning.java.annotation.MarkB;

/**
 * Created by xiongzehua on 2018/9/1.
 */
@MarkA("A--Student类")
public class Student extends Person {
    public String school;

    public Student(String name, String age, String school) {
        super(name, age);
        this.school = school;
    }

    public Student() {
    }

    @Override
    @MarkB("B--info方法")
    public String info() {
        return super.info() + "我是一名学生，在" + school + "上学。";
    }

    @MarkB("B--study方法")
    public String study(String course) {
        return name + "正在学习" + course + "。";
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    // 重写父类静态工厂方法
    // 若补充些，则Student.getInstance()调用的是父类方法，返回Person对象
    public static Student getInstance() {
        return new Student();
    }
}
