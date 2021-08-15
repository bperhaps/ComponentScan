package com.example.component_scan.controller;

import com.example.component_scan.annotation.ForLoginAndGuest;
import com.example.component_scan.annotation.ForLoginUser;
import com.example.component_scan.config.TestResolver;
import com.example.component_scan.scanner.data_structure.UriAndMethod;
import java.util.List;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/getUriAndMethods")
    public ResponseEntity<List<UriAndMethod>> getUriAndMethods(
        @TestResolver List<UriAndMethod> uriAndMethodList
    ) {
        return ResponseEntity.ok(uriAndMethodList);
    }
}
