package com.ourbook.shop.service.additionService.fileUploadService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileUploadService {

    String uploadImgFile(MultipartFile uploadImg) throws IOException;

    void deleteImgFile(int marketNumber) throws IOException;

}
