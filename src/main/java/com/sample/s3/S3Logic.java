package com.sample.s3;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.File;
import java.io.FileInputStream;

public class S3Logic {
    static String S3_ACCESS_KEY = "AKIA5U34ZZ7TCTGNUNOD";
    static String S3_SECRET_KEY = "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";
    static String S3_BUCKET_NAME = "mobile-tsunoda-demo";

    // 認証処理
    static private AmazonS3 auth() {
        System.out.println("auth start");

        // AWSの認証情報
        AWSCredentials credentials = new BasicAWSCredentials(S3_ACCESS_KEY, S3_SECRET_KEY);

        // S3アクセスクライアントの生成
        AmazonS3 client = AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_1)
                .build();

        System.out.println("auth end");
        return client;
    }

    // ｱｯﾌﾟﾛｰﾄﾞ処理
    static public void upload(String fileName) throws Exception {
        System.out.println("upload start");

        // 認証処理
        AmazonS3 client = auth();

        File file = new File("test.txt");
        file.createNewFile();

        // アップロード
        client.putObject(S3_BUCKET_NAME, "file/" + file.getName(),file);

        file.delete();

        System.out.println("upload end");
    }
}
