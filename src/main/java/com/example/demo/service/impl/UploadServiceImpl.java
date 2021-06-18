package com.example.demo.service.impl;

import com.example.demo.dao.PhotoMapper;
import com.example.demo.pojo.Photo;
import com.example.demo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;

public class UploadServiceImpl implements UploadService {

    @Autowired
    PhotoMapper mapper;

    @Override
    public int uploadPhoto(Photo photo) {
        return mapper.insert(photo);
    }
}
