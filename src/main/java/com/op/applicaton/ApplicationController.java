package com.op.applicaton;

import com.op.request.LoginRequest;
import com.op.request.LoginRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ApplicationController {
    @Autowired
    LoginRequestRepository loginRequestRepository;

    @PostMapping("/login")
    public boolean login(@Valid @RequestBody LoginRequest loginRequest){
        return true;
    }

    @PostMapping("/save")
    public LoginRequest create(@Valid @RequestBody LoginRequest loginRequest){
        login(loginRequest);
        return loginRequestRepository.save(loginRequest);
    }


}
