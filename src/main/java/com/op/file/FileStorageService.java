package com.op.file;

import com.op.response.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService implements StorageService {
    private final Path ROOT_LOCATION;

    public FileStorageService(StorageProperties properties) {
        ROOT_LOCATION = Paths.get(properties.getLocation());
    }

    @PostConstruct
    @Override
    public void init() {
        try {
            Files.createDirectories(ROOT_LOCATION);
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @Override
    public FileResponse store(MultipartFile file) {
        String filenmae = StringUtils.cleanPath(file.getOriginalFilename());

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path("/" + filenmae)
                .toUriString();

        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, ROOT_LOCATION.resolve(filenmae), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }

        return new FileResponse(filenmae, uri, file.getContentType(), file.getSize());
    }

    @Override
    public Resource loadAsResource(String name) {
        Path path = ROOT_LOCATION.resolve(name);
        try {
            Resource resource = new UrlResource((path.toUri()));
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not read file: " + name);
        } catch (MalformedURLException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
