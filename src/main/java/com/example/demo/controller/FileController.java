package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {
    @RequestMapping(value = "/upload.json", method = {RequestMethod.POST})
    public boolean fileUpload(MultipartFile file) throws IOException {
        if (file.getSize() == 0) {
            return false;
        }

        System.err.println("文件是否为空 ： " + file.isEmpty());
        System.err.println("文件的大小为 ：" + file.getSize());
        System.err.println("文件的媒体类型为 ： " + file.getContentType());
        System.err.println("文件的名字： " + file.getName());
        System.err.println("文件的originName为： " + file.getOriginalFilename());

        File newFile = new File("/Users/jetbrains/web_file" + file.getOriginalFilename());
        file.transferTo(newFile);
        return true;
    }
}
