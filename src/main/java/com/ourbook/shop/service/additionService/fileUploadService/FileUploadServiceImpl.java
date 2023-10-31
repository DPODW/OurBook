package com.ourbook.shop.service.additionService.fileUploadService;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public FileUploadServiceImpl(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public String uploadImgFile(MultipartFile uploadImg) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + uploadImg.getOriginalFilename();
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(uploadImg.getInputStream().available());
        amazonS3Client.putObject(bucket, fileName, uploadImg.getInputStream(), objMeta);

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }
}
