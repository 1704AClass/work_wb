package com.ningmeng.manage_course.service;

import com.ningmeng.framework.domain.course.ext.CategoryNode;
import com.ningmeng.framework.domain.system.SysDictionary;
import com.ningmeng.manage_course.dao.CategoryMapper;
import com.ningmeng.manage_course.dao.SysDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 炫龙 on 2020/2/19.
 */
@Service
public class SysdictionaryService {

    @Autowired
    SysDictionaryRepository sysDictionaryDao;

    //根据字典分类type查询字典信息
    public SysDictionary findDictionaryByType(String type){
        return sysDictionaryDao.findByDType(type);
    }

}
