package com.op.file;

import com.op.response.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();

    FileResponse store(MultipartFile file);

    Resource loadAsResource(String name);
}
