package com.xiongzehua.learning.java.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by xiongzehua on 2019/2/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    private String name;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    private Boolean male;

    private Person spouse;

    private List<Person> friends;

    @JsonIgnore
    private String ignoreExample;

    @JsonProperty("alias_example")
    private String aliasExample;

    private String nullExample;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public List getFriends() {
        return friends;
    }

    public void setFriends(List friends) {
        this.friends = friends;
    }

    public String getIgnoreExample() {
        return ignoreExample;
    }

    public void setIgnoreExample(String ignoreExample) {
        this.ignoreExample = ignoreExample;
    }

    public String getAliasExample() {
        return aliasExample;
    }

    public void setAliasExample(String aliasExample) {
        this.aliasExample = aliasExample;
    }

    public String getNullExample() {
        return nullExample;
    }

    public void setNullExample(String nullExample) {
        this.nullExample = nullExample;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", male=" + male +
                ", spouse=" + spouse +
                ", friends=" + friends +
                ", ignoreExample='" + ignoreExample + '\'' +
                ", aliasExample='" + aliasExample + '\'' +
                ", nullExample='" + nullExample + '\'' +
                '}';
    }
}
