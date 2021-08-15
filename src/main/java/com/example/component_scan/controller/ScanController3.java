package com.example.component_scan.controller;

import com.example.component_scan.annotation.ForLoginAndGuest;
import com.example.component_scan.annotation.ForLoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class ScanController3 {

    @ForLoginAndGuest
    @GetMapping("/getTestGuest")
    public void getTest() {

    }

    @ForLoginUser
    @PostMapping("/postTstLogin")
    public void postTest() {

    }

    @ForLoginAndGuest
    @PutMapping("/putTestGuest")
    public void putTest() {

    }
}
