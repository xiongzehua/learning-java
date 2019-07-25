package com.xiongzehua.learning.java.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiongzehua on 2019/2/18.
 */
public class JacksonTest {
    ObjectMapper mapper;

    @Before
    public void initObjectMapper() {
        mapper = new ObjectMapper();

        // 支持java8 LocalDate
        /** https://github.com/FasterXML/jackson-modules-java8 */
        mapper.registerModule(new JavaTimeModule());

        /** 自定义设置 */
        /** https://github.com/FasterXML/jackson-databind/wiki/JacksonFeatures */

        // 关闭 “序列化时，把时间转换成时间戳”  spring默认进行了此设置
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 关闭 “反序列化时，遇见不认识的name时报错”  spring默认进行了此设置
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 全局设置 @JsonInclude(JsonInclude.Include.NON_NULL)
        // mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    }


    @Test
    public void testSerialization() throws JsonProcessingException {

        Person person1 = new Person();
        person1.setName("xiong1");
        person1.setAge(20);
        person1.setMale(true);
        person1.setBirthday(LocalDateTime.now());
        person1.setIgnoreExample("ignore");
        person1.setAliasExample("alias");
        person1.setNullExample(null);

        Person person2 = new Person();
        person2.setName("xiong2");
        person2.setAge(20);
        person2.setMale(true);
        person2.setBirthday(LocalDateTime.now());
        person2.setIgnoreExample("ignore");
        person2.setAliasExample("alias");
        person2.setNullExample(null);

        Person person3 = new Person();
        person3.setName("xiong1");
        person3.setAge(20);
        person3.setMale(true);
        person3.setBirthday(LocalDateTime.now());
        person3.setIgnoreExample("ignore");
        person3.setAliasExample("alias");
        person3.setNullExample(null);

        person1.setSpouse(person2);
        List<Person> friends = new LinkedList<Person>();
        friends.add(person2);
        friends.add(person3);
        person1.setFriends(friends);
        String json = mapper.writeValueAsString(person1);
        System.out.println(json);

    }

    @Test
    public void test() throws JsonProcessingException {
        Integer i = 3;
        String json = mapper.writeValueAsString(i);
        System.out.println(json);
    }

    @Test
    public void testDeserialization() throws IOException {

        String json1 = "{\"name\":\"xiong\",\"age\":20,\"birthday\":\"2019-02-18 20:21:24\",\"male\":true,\"alias_example\":\"alias\"}";
        Person person1 = mapper.readValue(json1, Person.class);
        System.out.println(person1);

        String json2 = "{\"wrong\":\"wrong\",\"name\":\"xiong\",\"alias_example\":\"alias\"}";
        Person person2 = mapper.readValue(json2, Person.class);
        System.out.println(person2);

        String json3 = "{\"aliasExample\":\"alias\",\"ignoreExample\":\"ignore\",\"nullExample\":\"nullExample\"}";
        Person person3 = mapper.readValue(json3, Person.class);
        System.out.println(person3);
    }
}
