package com.example.demo.service;

import com.example.demo.pojo.Photo;

import java.util.List;

public interface UploadService {
    int uploadPhoto(Photo photo);

    List<Photo> queryAll();
}
