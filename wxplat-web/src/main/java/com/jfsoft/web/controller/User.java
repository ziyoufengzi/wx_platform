package com.jfsoft.web.controller;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/13  9:46
 * @Description TODO(一句话描述类作用)
 */
public class User {
    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
