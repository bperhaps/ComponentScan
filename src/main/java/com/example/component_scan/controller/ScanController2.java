package com.example.component_scan.controller;

import com.example.component_scan.annotation.ForLoginAndGuest;
import com.example.component_scan.annotation.ForLoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PutMapping("/deleteTestLogin")
    public void deleteTest() {

    }
}
