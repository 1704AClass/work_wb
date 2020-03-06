package com.ningmeng.manage_course.client;

import com.ningmeng.framework.client.NmServiceList1;
import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import com.ningmeng.framework.domain.cms.response.CmsPostPageResult;
import com.ningmeng.framework.exception.ExceptionCast;
import com.ningmeng.framework.model.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by 炫龙 on 2020/2/25.
 */
@FeignClient(value = NmServiceList1.nm_SERVICE_MANAGE_CMS)
public interface CmsPageClient {

    @GetMapping("/cms/findById/{id}")
    public CmsPage findById(@PathVariable("id") String id);

    @GetMapping("/cms/aa")
    public String aa();
    //保存页面
    @PostMapping("/cms/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage);
    //一键发布页面
    @PostMapping("/cms/postPageQuick")
    public CmsPostPageResult postPageQuick(@RequestBody CmsPage cmsPage);

}
