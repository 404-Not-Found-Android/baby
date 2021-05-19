package com.example.demo.dao;

import com.example.demo.pojo.Photo;

public interface PhotoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Photo photo);

    int insertSelective(Photo photo);

    Photo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Photo photo);

    int updateByPrimaryKeyWithBLOBs(Photo photo);

    int updateByPrimaryKey(Photo photo);
}