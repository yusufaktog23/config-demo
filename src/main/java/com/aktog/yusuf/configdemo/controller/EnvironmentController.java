package com.aktog.yusuf.configdemo.controller;


import com.aktog.yusuf.configdemo.EnvironmentService;
import com.aktog.yusuf.configdemo.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/env")
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @GetMapping
    public String getEnv(){
        return environmentService.toString();
    }

}
