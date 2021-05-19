package com.example.demo.controller;

import com.example.demo.pojo.Photo;
import com.example.demo.response.SpringBootJSONResult;
import com.example.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public SpringBootJSONResult add(@RequestBody Photo photo) {
        SpringBootJSONResult springBootJSONResult = new SpringBootJSONResult();
        photo.setCreateTime(System.currentTimeMillis());
        try {
            int result = photoService.addPhoto(photo);
            if (result != 1) {
                springBootJSONResult.setStatus(500);
                springBootJSONResult.setMsg("添加失败");
                return springBootJSONResult;
            }
            springBootJSONResult.setStatus(200);
            springBootJSONResult.setMsg("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            springBootJSONResult.setStatus(500);
            springBootJSONResult.setMsg(e.getMessage());
            return springBootJSONResult;
        }
        return springBootJSONResult;
    }
}
