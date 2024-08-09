package com.aw.controller;

import com.aw.request.DownloadFileRequest;
import com.aw.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.MalformedURLException;

@Controller
@AllArgsConstructor
public class FileController {

    private FileService fileService;

    @PostMapping(value = "/download",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<Resource> downloadFile(@RequestBody DownloadFileRequest request) throws MalformedURLException {

        Resource resource = fileService.downloadFile(request);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(resource);
    }
}
