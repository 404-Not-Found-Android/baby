package com.example.demo.controller;

import com.example.demo.pojo.Photo;
import com.example.demo.response.SpringBootJSONResult;
import com.example.demo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UploadController {

    public static String URL = "http://192.168.2.147";
//    @Autowired
//    UploadService service;

    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public SpringBootJSONResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        Map<String, Object> map = new LinkedHashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            map.put(paramName.toUpperCase(), paramValue);
        }
        Photo photo = new Photo();
        for (String key : map.keySet()) {
            switch (key) {
                case "CREATETIME":
                    photo.setCreateTime(Long.valueOf(String.valueOf(map.get(key))));
                    break;
                case "DESCRIBE":
                    photo.setDescribe(String.valueOf(map.get(key)));
                    break;
                case "UPLOADAUTHOR":
                    photo.setUploadAuthor(String.valueOf(map.get(key)));
                    break;
                case "FILENAME":
                    photo.setFileName(String.valueOf(map.get(key)));

                    break;

            }
            System.out.println("key:" + key + ",value:" + map.get(key));
        }

        SpringBootJSONResult jsonResult = new SpringBootJSONResult();
        if (file.isEmpty()) {
            jsonResult.setStatus(500);
            jsonResult.setMsg("上传失败");
            return jsonResult;
        }

        String fileName = file.getOriginalFilename();
        String urlPath = File.separator + fileName;
        String localPath = "/Users/jetbrains/web_file/";
        String filePath = localPath + File.separator + fileName;
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        System.out.println(URL + urlPath);
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
