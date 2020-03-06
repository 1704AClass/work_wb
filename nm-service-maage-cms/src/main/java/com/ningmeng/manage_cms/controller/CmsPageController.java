package com.ningmeng.manage_cms.controller;

import com.ningmeng.api.cms.CmsPageControllerApi;
import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.request.QueryPageRequest;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import com.ningmeng.framework.domain.cms.response.CmsPostPageResult;
import com.ningmeng.framework.model.response.CommonCode;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.QueryResult;
import com.ningmeng.framework.model.response.ResponseResult;
import com.ningmeng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 炫龙 on 2020/2/11.
 */
@RestController
@RequestMapping("/cms")
public class CmsPageController implements CmsPageControllerApi{

    @Autowired
    PageService pageService;
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size,
      QueryPageRequest queryPageRequest){
        return  pageService.findList(page,size,queryPageRequest);
    }

    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage){
        System.out.println(cmsPage.toString());
        return pageService.add(cmsPage);
    }

    @Override
    @GetMapping("/findById/{id}")
    public CmsPage findById(@PathVariable("id") String id){
        return pageService.findById(id);
    }

    @Override
    @PutMapping("/edit")
    public ResponseResult edit(@RequestBody CmsPage cmsPage){
        return  pageService.edit(cmsPage);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id")String id){
        return pageService.delete(id);
    }
    @GetMapping("aa")
    public String aa(){
        String a="sss";
        return  "东家是假的";
    }

    @Override
   @PostMapping("/postPage/{pageId}")
    public ResponseResult post(@PathVariable("pageId") String pageId) {
        System.out.print(pageId);
        System.out.print("controller进来了");
        return pageService.postPage(pageId);
    }

    @Override
    @PostMapping("/postPageQuick")
    public CmsPostPageResult postPageQuick(@RequestBody CmsPage cmsPage){
        return pageService.postPageQuick(cmsPage);
    }

}
