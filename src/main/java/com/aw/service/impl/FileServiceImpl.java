package com.aw.service.impl;

import com.aw.request.DownloadFileRequest;
import com.aw.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;


@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public Resource downloadFile(DownloadFileRequest request) throws MalformedURLException {

        String absolutePath = String.join(File.separator, "D:", "image-collection", "standard");
        File file = new File(String.join(File.separator, absolutePath, request.getFilename()));

        return new UrlResource(file.toURI());
    }
}
