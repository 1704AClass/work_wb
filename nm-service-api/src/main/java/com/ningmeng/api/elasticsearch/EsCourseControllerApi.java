package com.ningmeng.api.elasticsearch;

import com.ningmeng.framework.domain.course.CoursePub;
import com.ningmeng.framework.domain.course.response.TeachplanMediaPub;
import com.ningmeng.framework.domain.search.CourseSearchParam;
import com.ningmeng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.Map;

/**
 * Created by 炫龙 on 2020/3/4.
 */
@Api(value = "课程搜索",description = "课程搜索",tags = {"课程搜索"})
public interface EsCourseControllerApi {

    @ApiOperation("课程搜索")
    public QueryResponseResult list(int page, int size,
                                    CourseSearchParam courseSearchParam) throws IOException;

    @ApiOperation("根据id查询课程信息")
    public Map<String,CoursePub> getall(String id);

    @ApiOperation("根据课程计划查询媒资信息")
    public TeachplanMediaPub getmedia(String teachplanId);
}
