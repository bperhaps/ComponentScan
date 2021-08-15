package com.example.component_scan.controller;

import com.example.component_scan.annotation.ForLoginAndGuest;
import com.example.component_scan.annotation.ForLoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ScanController1 {

    @ForLoginAndGuest
    @GetMapping("/getTestGuest")
    public void getTest() {

    }

    @ForLoginUser
    @PostMapping("/postTstLogin")
    public void postTest() {

    }

    @PutMapping("/putTestNop")
    public void putTest() {

    }
}
