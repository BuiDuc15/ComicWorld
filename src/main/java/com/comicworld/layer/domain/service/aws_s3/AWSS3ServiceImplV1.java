package com.comicworld.layer.domain.service.aws_s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.comicworld.utils.NumberUtils;
import com.comicworld.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("awsS3ServiceImplV1")
public class AWSS3ServiceImplV1 implements AWSS3Service {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${cloud.region.static}")
    private String region;

    @Value("${cloud.bucket.name}")
    private String bucketName;

    @Override
    public void deleteFile(String path) {
        this.s3Client.deleteObject(this.bucketName, path);
    }

    @Override
    public String uploadFileToPath(MultipartFile multipartFile, String path) {
        ObjectMetadata metadata = new ObjectMetadata();

        metadata.setContentDisposition("attachment");
        metadata.setContentLength(multipartFile.getSize());

        try {
            this.s3Client.putObject(this.bucketName, path, multipartFile.getInputStream(), metadata);
        }
        catch (AmazonServiceException e) {
            e.printStackTrace();
        }
        catch (SdkClientException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String objectLink = "https://" + this.bucketName + ".s3." + this.region + ".amazonaws.com/" + path;

        return objectLink;
    }

    @Override
    public List<String> uploadFilesToPath(List<MultipartFile> multipartFiles, String path) {
        List<String> links = new ArrayList<>();

        for(MultipartFile multipartFile : multipartFiles) {
            ObjectMetadata metadata = new ObjectMetadata();

            metadata.setContentDisposition("attachment");
            metadata.setContentLength(multipartFile.getSize());

            String realPath = path + NumberUtils.generateRandomNumber() + "." +
                    StringUtils.getExtensionFromImageName(multipartFile.getOriginalFilename());

            try {
                this.s3Client.putObject(this.bucketName, realPath, multipartFile.getInputStream(), metadata);
            }
            catch (AmazonServiceException e) {
                e.printStackTrace();
            }
            catch (SdkClientException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            links.add("https://" + this.bucketName + ".s3." + this.region + ".amazonaws.com/" + realPath);
        }

        return links;
    }

    @Override
    public String uploadFileToStaticFolder(MultipartFile multipartFile) {
        ObjectMetadata metadata = new ObjectMetadata();

        metadata.setContentDisposition("attachment");
        metadata.setContentLength(multipartFile.getSize());

        String path = "static/" + NumberUtils.generateRandomNumber() + "." +
                StringUtils.getExtensionFromImageName(multipartFile.getOriginalFilename());

        try {
            this.s3Client.putObject(this.bucketName, path, multipartFile.getInputStream(), metadata);
        }
        catch (AmazonServiceException e) {
            e.printStackTrace();
        }
        catch (SdkClientException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String objectLink = "https://" + this.bucketName + ".s3." + this.region + ".amazonaws.com/" + path;

        return objectLink;
    }
}




































