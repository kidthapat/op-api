package com.op.applicaton;

import com.op.request.LoginRequest;
import com.op.request.LoginRequestRepository;
import com.op.user.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ApplicationController {
    @Autowired
    LoginRequestRepository loginRequestRepository;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String passwrod = loginRequest.getPassword();

//        boolean isAuthenticated = username.equals("pros@gmail.com") && passwrod.equals("1234");
//        System.out.println(isAuthenticated);
//        if (isAuthenticated) {
//            User user = new User();
//            user.set_id(ObjectId.get());
//            user.setEmail(username);
//
//            return new ResponseEntity(user, HttpStatus.OK);
//        }



        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
    }

//    @PostMapping("/save")
//    public LoginRequest create(@Valid @RequestBody LoginRequest loginRequest){
//        login(loginRequest);
//        return loginRequestRepository.save(loginRequest);
//    }
}
