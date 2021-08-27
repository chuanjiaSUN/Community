package com.maven.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-27 16:49
 */
@Service
public class AliYunProvider {

    @Value("${oss.keyID}")
    private String accessId;
    @Value("${oss.KeySecret}")
    private String accessSecret;
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.expirationTime}")
    private Long expirationTime;

    public String upload(String objectName, InputStream inputStream)
    {

        //生成Client
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessSecret);
        //封装请求参数
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
        //设置上传后图片类型
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(getContentType(objectName.substring(objectName.lastIndexOf("."))));
        putObjectRequest.setMetadata(objectMetadata);
        //上传图片
        ossClient.putObject(putObjectRequest);
        //设置url有效时长,并返回
        Date expiration = new Date(System.currentTimeMillis() + expirationTime);
        String url = ossClient.generatePresignedUrl(bucketName, objectName, expiration).toString();
        return url;
    }

    public static String getContentType(String fileNameExtension)
    {
        if (".bmp".equalsIgnoreCase(fileNameExtension))
        {
            return "image/bmp";
        }
        if ((".gif").equalsIgnoreCase(fileNameExtension))
        {
            return "image/gif";
        }
        return "image/jpg";
    }

}
