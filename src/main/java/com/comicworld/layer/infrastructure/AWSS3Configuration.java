package com.comicworld.layer.infrastructure;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.comicworld.utils.CodecUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Configuration {

    @Value("${cloud.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.region.static}")
    private String region;

    @Bean
    public AmazonS3 generateS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(CodecUtils.decodeBase64EncodedString(this.accessKey),
                CodecUtils.decodeBase64EncodedString(this.secretKey));

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

}
