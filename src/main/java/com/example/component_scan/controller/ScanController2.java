package com.example.component_scan.controller;

import com.example.component_scan.annotation.ForLoginAndGuest;
import com.example.component_scan.annotation.ForLoginUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/controller2")
@RestController
public class ScanController2 {

    @ForLoginUser
    @GetMapping("/getTestLogin")
    public void getTest() {

    }

    @ForLoginAndGuest
    @PostMapping("/postTestGuest")
    public void postTest() {

    }

    @ForLoginAndGuest
    @DeleteMapping("/deleteTestLogin")
    public void deleteTest() {

    }
}
