package com.ningmeng.api.cms;

import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.request.QueryPageRequest;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import com.ningmeng.framework.domain.cms.response.CmsPostPageResult;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.naming.Name;

/**
 * Created by 炫龙 on 2020/2/11.
 */
@Api(value = "cms页面管理接口",description = "cms页面管理接口提供页面的增删改查")
public interface CmsPageControllerApi {

    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "page",value = "页码"
    ,required = true,paramType = "path",defaultValue = "1"),
      @ApiImplicitParam(name = "size",value = "每页的记录数",required = true
       ,paramType = "path",defaultValue = "10"
       )
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    @ApiOperation("页面添加")
    public CmsPageResult add(CmsPage cmsPage);

    @ApiOperation("通过ID查询页面")
    public CmsPage findById(String id);

    @ApiOperation("修改页面")
    public ResponseResult edit(CmsPage cmsPage);

    @ApiOperation("通过ID删除页面")
    public ResponseResult delete(String id);

    @ApiOperation("发布页面")
    public ResponseResult post(String pageId);

    @ApiOperation("一键发布页面")
    public CmsPostPageResult postPageQuick(CmsPage cmsPage);

}
