package com.bee.sample.ch1.model;

/**
 * 用户
 *
 * @author jiangcan on 2020/1/12
 */
public class User {

    private String name;

    private String userId;

    public User() {
    }

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
