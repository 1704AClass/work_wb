package com.ningmeng.manage_course.dao;

import com.ningmeng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 炫龙 on 2020/2/19.
 */
@Mapper
public interface TeachplanMapper {
    public TeachplanNode selectList(@Param("courseId") String courseId);
}
