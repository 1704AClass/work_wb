package com.ningmeng.manage_course.dao;

import com.ningmeng.framework.domain.course.response.TeachplanMediaPub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 炫龙 on 2020/3/6.
 */
@Repository
public interface TeachplanMediaPubRepository extends JpaRepository<TeachplanMediaPub, String> {

    //根据课程id删除课程计划媒资信息
    long deleteByCourseId(String courseId);

}
