package com.aktog.yusuf.configdemo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EnvironmentService {

  /*  @Value("${spring.profiles.active}")
    private String activeProfile;*/

    @Value("${spring.application.name}")
    private String appName;

/*    @Value("${spring.datasource.username}")
    private String username;*/

    @Value("${spring.datasource.password}")
    private String password;
/*
    public String getActiveProfile() {
        return activeProfile;
    }

    public String getAppName() {
        return appName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "EnvironmentService{" +
                "activeProfile='" + activeProfile + '\'' +
                ", appName='" + appName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }*/
}
