package com.xiongzehua.learning.java.reflect;

import com.xiongzehua.learning.java.annotation.MarkA;
import com.xiongzehua.learning.java.pojo.Person;
import com.xiongzehua.learning.java.pojo.Student;
import org.junit.Test;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 *
 * Created by xiongzehua on 2018/8/1.
 */
public class ReflectTest {
    /**
     * 1. 反射对象--Class<T>对象
     * javac 编译 Person.java文件 生成 Person.class文件
     * java 加载 Person.class文件 会在虚拟机缓存区 生成 Person类对应的Class<Person>对象
     *
     * 无论多少个Person对象都只会有一个Class<Person>对象
     * 且每个Person对象中都有存有一个Class<Person>对象的引用，所以可以使用getClass()获得该对象
     *
     * 总结：对于任一类型T以及运行时真实类型为T的所以的对象，都持有一个Class<T>对象的引用
     * 这个Class<T>对象会保存T.java中编写的属性方法这些原始信息
     */
    @Test
    public void test1() throws ClassNotFoundException{
        Person p1 = new Person("zhang3", 21);
        Person p2 = new Student("li4", 22, "NJU");
        Student s3 = new Student("wang5", 23, "SCU");

        Class clazz1 = p1.getClass();
        Class clazz2 = p2.getClass();
        Class clazz3 = s3.getClass();
        Class clazzOfPerson = Person.class;
        Class clazzOfStudent = Student.class;

        System.out.println("p1 class: " + clazz1);
        System.out.println("p2 class: " + clazz2);
        System.out.println("s3 class: " + clazz3);
        System.out.println("clazzOfPerson: " + clazzOfPerson);
        System.out.println("clazzOfStudent: " + clazzOfStudent);

        System.out.println(clazz1 == clazzOfPerson);
        System.out.println(clazz2 == clazz3 && clazz2 == clazzOfStudent);
        System.out.println(clazz1 == clazz2);
    }

    /**
     * 2 获取反射对象的三种方法
     *
     * 其中第三种获取方式是反射的灵活所在，也是java实现动态性的关键
     * 传统制造对象的方式需要硬编码到.java文件中，相当于常量
     * 而使用反射则可以通过一个字符串来制造一个对象，相当于一个变量
     * 因为字符串可以通过后期输入获取，从文本中读取
     */
    @Test
    public void test2() {
        // 1.任何数据类型（包括基本数据类型）都有一个“静态”的class属性
        Class clazz1 = Student.class;

        // 2.任何对象都持有其运行时类型所对应的Class<T>对象
        Class clazz2 = new Student("li4", 22, "NJU").getClass();

        // 3.通过静态方法Class.forName("类名的全限定形式")返回对应的Class<T>对象    --核心
        Class clazz3 = null;
        try {
           clazz3  = Class.forName("com.xiongzehua.learning.java.pojo.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(clazz1 == clazz2 && clazz1 == clazz3);
    }

    /**
     * 3 通过Class<T>对象获取类的原始信息
     */
    @Test
    public void test3() {
        // 0.前提条件先获取类对应的Class<T>对象
        Class clazz = null;
        try {
            clazz  = Class.forName("com.xiongzehua.learning.java.pojo.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 1.获取Student类的成员变量
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType().getName() + " " + field.getName());
        }

        // 2.获取Student类的方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String modifier = Modifier.toString(method.getModifiers());
            String name = method.getName();

            String parameters = "(";
            int cnt = 0;
            Class[] classes = method.getParameterTypes();
            for (Class para : classes) {
                parameters = parameters + para.getName() + " arg-" + (cnt++) + ", ";
            }
            parameters = parameters + ")";

            System.out.println(modifier + " " + name + parameters);
        }

        // 3.获取Student类的注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
            System.out.println("是否是MarkA：" + (annotation.annotationType() == MarkA.class));
        }
    }

    /**
     * 4 调用指定方法
     * 完全使用反射来创造并使用一个对象
     */
    @Test
    public void test4() {
        // 1.前提条件先获取类对应的Class<T>对象
        Class clazz = null;
        try {
            clazz  = Class.forName("com.xiongzehua.learning.java.pojo.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2.使用Class<T>对象生成Student对象
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 3.使用Class<T>对象生成方法对象
        Method method1 = null, method2 = null, method3 = null, method4 = null;
        try {
            method1 = clazz.getMethod("setName", String.class);
            method2 = clazz.getMethod("setAge", int.class);
            method3 = clazz.getMethod("setSchool", String.class);
            method4 = clazz.getMethod("info");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // 4.执行方法
        try {
            method1.invoke(obj, "zhang3");
            method2.invoke(obj, 20);
            method3.invoke(obj, "NJU");
            System.out.println(method4.invoke(obj));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
