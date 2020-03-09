package com.ningmeng.learning.client;

import com.ningmeng.framework.domain.course.response.TeachplanMediaPub;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by 炫龙 on 2020/3/9.
 */
@EnableFeignClients(value = "nm-service-search")
public interface CourseSearchClient {

    @GetMapping("/search/course/getmedia/{teachplanId}")
    public TeachplanMediaPub getmedia(@PathVariable("teachplanId") String teachplanId);
}
