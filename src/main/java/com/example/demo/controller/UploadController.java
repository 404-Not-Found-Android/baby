package com.example.demo.controller;

import com.example.demo.bean.PhotoBean;
import com.example.demo.pojo.Photo;
import com.example.demo.response.SpringBootJSONResult;
import com.example.demo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
public class UploadController {

    public static String URL = "http://192.168.2.147:8080";
    @Autowired()
    UploadService service;

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/images", method = {RequestMethod.GET})
    public SpringBootJSONResult queryImages() {
        SpringBootJSONResult jsonResult = new SpringBootJSONResult();
        List<Photo> photos = null;
        try {
            photos = service.queryAll();
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMsg(e.getMessage());
            jsonResult.setStatus(500);
            return jsonResult;
        }
        PhotoBean bean = new PhotoBean();
        List<PhotoBean.Photo> photosBean = new ArrayList<>();
        PhotoBean.Photo photoBean = new PhotoBean.Photo();
        List<String> imagesBean = new ArrayList<>();
        for (Photo photo : photos) {
            String url = photo.getUrl();
            String[] split = url.split(",");
            imagesBean.addAll(Arrays.asList(split));
            photoBean.setUserName(photo.getUploadAuthor());
            photoBean.setImages(imagesBean);
            photosBean.add(photoBean);
            photoBean.setIcon("http://192.168.2.147:8080/image/image10.jpg");
            bean.setPhotos(photosBean);
        }
        jsonResult.setMsg("success");
        jsonResult.setStatus(200);
        jsonResult.setData(bean);
        return jsonResult;
    }

    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public SpringBootJSONResult upload(@RequestBody Photo photo) {
        SpringBootJSONResult jsonResult = new SpringBootJSONResult();
        String[] split = photo.getFileName().split(",");
        StringBuffer sbUrl = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            String fileName = split[i];
            sbUrl.append(URL).append("/image/").append(fileName);
            if (i < split.length - 1) {
                sbUrl.append(",");
            }
        }
        String url = sbUrl.toString();
        photo.setUrl(url);
        photo.setCreateTime(System.currentTimeMillis());

        String fileUuid = UUID.randomUUID().toString();
        photo.setFileUuid(fileUuid);
        System.out.println(photo);
        int result = service.uploadPhoto(photo);
        if (result == 1) {
            jsonResult.setStatus(200);
            jsonResult.setMsg("上传成功");
        } else {
            jsonResult.setStatus(500);
            jsonResult.setMsg("上传失败");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/upload_file", method = {RequestMethod.POST})
    public SpringBootJSONResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        SpringBootJSONResult jsonResult = new SpringBootJSONResult();
        if (file.isEmpty()) {
            jsonResult.setStatus(500);
            jsonResult.setMsg("上传失败");
            return jsonResult;
        }

        String fileName = file.getOriginalFilename();
        String localPath = "/Users/jetbrains/web_file/";
        String filePath = localPath + fileName;
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);
            jsonResult.setMsg("上传成功");
            jsonResult.setStatus(200);
            return jsonResult;
        } catch (IOException e) {
            e.printStackTrace();
            jsonResult.setStatus(500);
            jsonResult.setMsg(e.getMessage());
            return jsonResult;
        }

    }
}
