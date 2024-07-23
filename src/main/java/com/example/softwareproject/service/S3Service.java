package com.example.softwareproject.service;

import com.example.softwareproject.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



public interface S3Service {
    Result uploadImg(MultipartFile file);
}
