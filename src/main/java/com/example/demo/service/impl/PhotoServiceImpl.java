package com.example.demo.service.impl;

import com.example.demo.dao.PhotoMapper;
import com.example.demo.pojo.Photo;
import com.example.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public int addPhoto(Photo photo) {
        return photoMapper.insert(photo);
    }
}
