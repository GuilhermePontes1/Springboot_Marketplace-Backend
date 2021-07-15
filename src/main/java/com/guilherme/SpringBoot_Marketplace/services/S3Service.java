package com.guilherme.SpringBoot_Marketplace.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public void uploadFile(String localFilePath) {

        try {
            LOG.info("INICIANDO UPLOAD");
            File file = new File(localFilePath);
            s3client.putObject(bucketName, "teste.png", file);
            LOG.info("UPLOAD FINALIZADO ");

        }
        catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status info: " + e.getErrorCode());
        }
         catch (AmazonClientException e) {
            LOG.info("AmazonClientException" + e.getMessage());
        }

    }
}
