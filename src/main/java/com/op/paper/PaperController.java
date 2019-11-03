package com.op.paper;

import com.op.merchant.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PaperController {

    @Autowired
    private PaperService PaperService;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Paper paper){
        Paper createPaper = PaperService.create(paper);
        return new ResponseEntity(createPaper, HttpStatus.CREATED);
    }
}
