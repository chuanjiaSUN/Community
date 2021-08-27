package com.maven.community.controller;

import com.maven.community.dto.FileDto;
import com.maven.community.provider.AliYunProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-26 0:20
 */
@Controller
public class FileController {

    @Autowired
    private AliYunProvider aliYunProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDto upLoad(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        String objectName = file.getOriginalFilename();
        System.out.println("本地上传了: " + objectName);
        String upload = aliYunProvider.upload(objectName, file.getInputStream());
        FileDto fileDto = new FileDto();
        fileDto.setSuccess(1);
        fileDto.setUrl(upload);
        return fileDto;
    }
}
