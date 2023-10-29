package com.ourbook.shop.service.additionService.fileUploadService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${saveFile.dir}")
    private String saveFileDir;


    /** MultipartFile 타입으로 받은 매개변수를 가공, 저장 디렉토리에 저장 **/
    @Override
    public String uploadFile(MultipartFile uploadImg) throws IOException {
        String saleImg = createSaleImg(uploadImg.getOriginalFilename());
        uploadImg.transferTo(new File(saveFileDir+saleImg));
        return saleImg;
    }


    /** 파일의 이름을 난수화 시키는 메소드 (마지막에는 추출한 확장자를 추가)  **/
    @Override
    public String createSaleImg(String originalFilename) {
        String extension = getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + extension;
    }


    /** 파일의 이름(originalFilename) 에서 확장자명을 가져오는 메소드 **/
    @Override
    public String getExtension(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
