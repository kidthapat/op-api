package com.op.upload;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class UploadController {

    @Autowired
    private GridFsOperations gridFsOperations;

    String fileId = "";

    @GetMapping("/savefiles")
    public String saveFile() throws FileNotFoundException {
        DBObject metadata = new BasicDBObject();
        metadata.put("organization", "java Techie");
        InputStream inputStream=new FileInputStream("/Users/nuvola/Downloads/pupe.jpg");

        metadata.put("type", "image");
        fileId = gridFsOperations.store(inputStream, "pupe.jpg", "Download/jpg", metadata).toString();
        System.out.println("File in store : "+ fileId);

//        metadata.put("type", "data");
//        gridFsOperations.store(new FileInputStream(""), "mytext.text", "text/pain", metadata);

        return "File store Success";
    }

//    public String retriveImage() throws IOException {
//        GridFSDBFile dbFile = gridFsOperations.findOne(new Query(Criteria.where("_id").is(fileId)));
//        dbFile.writeTo("C:/Users/bahota/Desktop/Local-Disk/reactive-logo.png");
//        System.out.println("File name : " + dbFile.getFilename());
//        return "Image File retrived with name : " + dbFile.getFilename();
//
//    }
}
