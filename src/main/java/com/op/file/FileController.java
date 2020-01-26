package com.op.file;

import com.op.constant.Api;
import com.op.response.FileResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
ref:
https://attacomsian.com/blog/uploading-files-spring-boot
 */

@RequestMapping(Api.v1)
@RestController
public class FileController {
    private static Log LOG = LogFactory.getLog(FileController.class);

    @Autowired
    private StorageService storageService;

    @PreAuthorize("hasAuthority('UPLOAD_FILE')")
    @PostMapping("/files")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        LOG.info("Call Upload file: " + file.getOriginalFilename());
        FileResponse response = storageService.store(file);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/files/download/{name:.+}")
    public ResponseEntity<Resource> downloadByName(@PathVariable String name) {
        LOG.info("Call Download File: " + name);
        Resource resource = storageService.loadAsResource(name);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/files/{name:.+}")
    public ResponseEntity findByName(@PathVariable String name) throws IOException {
        LOG.info("Call Get File: " + name);
        Resource resource = storageService.loadAsResource(name);
        Path path = resource.getFile().toPath();
        String mediaType = Files.probeContentType(path);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .body(resource);
    }
}
