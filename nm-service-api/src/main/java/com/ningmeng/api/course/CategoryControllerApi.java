package com.ningmeng.api.course;

import com.ningmeng.framework.domain.course.ext.CategoryNode;
import com.ningmeng.framework.domain.system.SysDictionary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by 炫龙 on 2020/2/21.
 */
@Api(value = "课程分类管理",description = "课程分类管理",tags = {"课程分类管理CategoryController"})
public interface CategoryControllerApi {

    @ApiOperation("查询分类")
    public CategoryNode findList();
}
