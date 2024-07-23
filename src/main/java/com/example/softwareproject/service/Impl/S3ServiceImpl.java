package com.example.softwareproject.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class S3ServiceImpl implements S3Service {
    @Autowired
    private AmazonS3 s3Client;
    private String bucketName = "ihweibopicture";

    @Override
    public Result uploadImg(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String fileName =new DateTime().toString("yyyy/MM/dd")
                    + "_" + UUID.randomUUID().toString().replaceAll("-", "")
                    + "_" + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream,metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
//            GeneratePresignedUrlRequest generatePresignedUrlRequest =
//                    new GeneratePresignedUrlRequest(bucketName, fileName);
//            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
//            //s3Client.shutdown();
//            return Result.success(url.toString());
            // 获取公共URL
            String fileUrl = s3Client.getUrl(bucketName, fileName).toString();
            return Result.success(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("服务端错误");
        }

    }

}
