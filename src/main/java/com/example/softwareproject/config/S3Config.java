package com.example.softwareproject.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Bean
    public AmazonS3 s3Client(){
        String accessKey = "aLZ9efRdU5nZBRb4oJIIbdS7";
        String secretKey = "XalqjrEgnOnuPcYJLJpqk1O7rdE8nMZ";
        AWSCredentials credentials=new BasicAWSCredentials(accessKey,secretKey);

        return AmazonS3ClientBuilder.standard()
                //.withRegion(Regions.DEFAULT_REGION)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("s3.bitiful.net","cn-east-1"))
                .build();
    }
}
