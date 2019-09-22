package com.op.file;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.op.constant.Api;
import com.op.response.FileResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/*
ref:
https://attacomsian.com/blog/uploading-files-spring-boot
 */

@RequestMapping(Api.v1)
@RestController
public class FileController {
    private static Log LOG = LogFactory.getLog(FileController.class);

    @Autowired
    private GridFsOperations gridFsOperations;

    @Autowired
    private StorageService storageService;

    String fileId = "";

    @GetMapping("/savefiles")
    public String saveFile() throws FileNotFoundException {
        DBObject metadata = new BasicDBObject();
        metadata.put("organization", "java Techie");
        InputStream inputStream = new FileInputStream("/Users/nuvola/Downloads/pupe.jpg");

        metadata.put("type", "image");
        fileId = gridFsOperations.store(inputStream, "pupe.jpg", "Download/jpg", metadata).toString();
        System.out.println("File in store : " + fileId);

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

    @PreAuthorize("hasAuthority('UPLOAD_FILE')")
    @PostMapping("/file/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        LOG.info("Call upload file: " + file.getOriginalFilename());
        FileResponse response = storageService.store(file);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/file/download/{name:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String name) {
        LOG.info("Call download file: " + name);
        Resource resource = storageService.loadAsResource(name);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
