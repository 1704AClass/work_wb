package com.ningmeng.manage_course.dao;

import com.github.pagehelper.Page;
import com.ningmeng.framework.domain.course.CourseBase;
import com.ningmeng.framework.domain.course.ext.CategoryNode;
import com.ningmeng.framework.domain.course.ext.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator.
 */
@Mapper
public interface CategoryMapper {

   //查询分类
   public CategoryNode selectList();
}
