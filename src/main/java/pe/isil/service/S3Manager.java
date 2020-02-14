package pe.isil.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.InputStream;

public class S3Manager {

    AWSCredentials credentials =
            new BasicAWSCredentials("AKIAQKS6V3RVDTEUCXGL", "fnOXla2qXrzd9V4k9Eq9atuEiUCJLZOwpHkmOfft");

    AmazonS3 s3Client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_WEST_1)
            .build();

    public void uploadFileToS3(String name, InputStream is){

        //validar si existe el bucket o crear

        //subir archivo

    }
}
