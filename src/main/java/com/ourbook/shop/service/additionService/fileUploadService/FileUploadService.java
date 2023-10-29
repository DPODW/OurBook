package com.ourbook.shop.service.additionService.fileUploadService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileUploadService {

    String uploadFile(MultipartFile uploadImg) throws IOException;

    String createSaleImg(String originalFilename);

    String getExtension(String originalFilename);
}
