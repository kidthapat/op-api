package com.op.home;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity index() {
        return ResponseEntity.ok("This is an OP APIs. You will see this page if it's working (without database connection.)");
    }
}
