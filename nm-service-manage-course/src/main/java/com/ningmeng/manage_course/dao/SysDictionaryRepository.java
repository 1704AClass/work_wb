package com.ningmeng.manage_course.dao;

import com.ningmeng.framework.domain.course.CourseBase;
import com.ningmeng.framework.domain.system.SysDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Administrator.
 */
public interface SysDictionaryRepository extends MongoRepository<SysDictionary,String> {

    //根据字典分类查询字典信息
    SysDictionary findByDType(String type);
}
