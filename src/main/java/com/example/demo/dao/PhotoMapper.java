package com.example.demo.dao;

import com.example.demo.pojo.Photo;

import java.util.List;

public interface PhotoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);

    List<Photo> selectAll();
}