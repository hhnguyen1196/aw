package com.aw.service;

import com.aw.request.DownloadFileRequest;
import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

public interface FileService {

    Resource downloadFile(DownloadFileRequest request) throws MalformedURLException;
}
