package com.ningmeng.manage_cms.service;

import com.alibaba.fastjson.JSON;
import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.framework.domain.cms.CmsSite;
import com.ningmeng.framework.domain.cms.request.QueryPageRequest;
import com.ningmeng.framework.domain.cms.response.CmsCode;
import com.ningmeng.framework.domain.cms.response.CmsPageResult;
import com.ningmeng.framework.domain.cms.response.CmsPostPageResult;
import com.ningmeng.framework.exception.ExceptionCast;
import com.ningmeng.framework.model.response.CommonCode;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.QueryResult;
import com.ningmeng.framework.model.response.ResponseResult;
import com.ningmeng.manage_cms.config.RabbitmqConfig;
import com.ningmeng.manage_cms.dao.CmsPageRepository;
import com.ningmeng.manage_cms.dao.CmsSiteRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by 炫龙 on 2020/2/11.
 */
@Service
public class PageService {
    @Autowired
    CmsPageRepository cmsPageRepository;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    CmsSiteRepository cmsSiteRepository;


    /**
     * 页面列表分页查询
     * @param page 当前页面
     * @param size  页面显示多少个
     * @param queryPageRequest 查询条件
     * @return
     */
    public QueryResponseResult findList(int page,int size,QueryPageRequest queryPageRequest){

        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());

        //条件值
        CmsPage cmsPage = new CmsPage();
        //站点ID
        if(StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //页面别名
        if(StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        Example<CmsPage> of = Example.of(cmsPage, exampleMatcher);

        //如果前台没有数据，创建一个后面进行填充
        if (queryPageRequest == null){
            queryPageRequest =new QueryPageRequest();
        }

        //最小不能小于等于0
        if (page<=0){
            page=1;
        }
        page=page-1;  //为了适应接口分页查询
        if (size<=0){
            size=20;
        }
        //分页对象
        Pageable pageable=PageRequest.of(page,size);
        Page<CmsPage> all=cmsPageRepository.findAll(of,pageable);
        QueryResult cmsQueryResult=new QueryResult<CmsPage>();
        cmsQueryResult.setList(all.getContent());
        cmsQueryResult.setTotal(all.getTotalElements());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS,cmsQueryResult);
    }


    /**
     * 根据对象添加方法
     * @param cmsPage 添加的对象
     * @return  ResponseResult
     */
    public CmsPageResult add(CmsPage cmsPage){
        if (cmsPage == null){
            //抛出异常，非法请求
             ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //校验页面是否存在，根据页面名称、站点Id、页面webpath查询
        CmsPage cmsPage1= cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),cmsPage.getSiteId(),cmsPage.getPageWebPath());
        if (cmsPage1!=null){
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        cmsPage.setPageId(null);
        cmsPage.setPageCreateTime(new Date());
        CmsPage save = cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS,save);

    }

    //一键发布页面
    public CmsPostPageResult postPageQuick(CmsPage cmsPage){
        //添加页面
        CmsPageResult save = this.add(cmsPage);
        if(!save.isSuccess()){
            return new CmsPostPageResult(CommonCode.FAIL,null);
        }
        CmsPage cmsPage1 = save.getCmsPage();
        //要布的页面id
        String pageId = cmsPage1.getPageId();
        //发布页面
        ResponseResult responseResult = this.postPage(pageId);
        if(!responseResult.isSuccess()){
            return new CmsPostPageResult(CommonCode.FAIL,null);
        }
        //得到页面的url
        // 页面url=站点域名+站点webpath+页面webpath+页面名称
        // 站点id
        String siteId = cmsPage1.getSiteId();
        //查询站点信息
        CmsSite cmsSite = findCmsSiteById(siteId);
        //站点域名
        String siteDomain = cmsSite.getSiteDomain();
        //站点web路径
        String siteWebPath = cmsSite.getSiteWebPath();
        //页面名称
        String pageName = cmsPage1.getPageName();
        //页面web路径
        String pageWebPath = cmsPage1.getPageWebPath();
        //页面web访问路径
        String pageUrl=siteDomain+siteWebPath+pageWebPath+pageName;
        return new CmsPostPageResult(CommonCode.SUCCESS,pageUrl);


    }

    //根据id查询站点信息
    public CmsSite findCmsSiteById(String siteId){
    Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
    if(optional.isPresent()){
        return optional.get();
    }
    return null;
    }

    /**
     * 根据id查询一个对象
     * @param id
     * @return cmspage对象
     */
    public CmsPage findById(String id){
        Optional<CmsPage> byId = cmsPageRepository.findById(id);
        if (byId.isPresent()){
            return  byId.get();
        }
        return null;
    }

    /**
     * 修改
     * @param cmsPage  修改的数据
     * @return
     */
    public ResponseResult edit(CmsPage cmsPage){
        CmsPage byId = this.findById(cmsPage.getPageId());
        if (byId!=null){
            cmsPageRepository.save(cmsPage);
            return new ResponseResult(CommonCode.SUCCESS);
        }

        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 删除
     * @param id 根据id进行删除
     * @return
     */
    public ResponseResult delete(String id){
        CmsPage byId = this.findById(id);
        if (byId!=null){
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);

    }


    //页面发布
    public  ResponseResult postPage(String pageId){
        //执行静态化页面

        //保存静态化文件
         System.out.print("准备发送信息");
        //发送信息
        sendPostPage(pageId);
        return new ResponseResult(CommonCode.SUCCESS);
    }


    //发送页面发布消息
    public void sendPostPage(String pageId){
        CmsPage cmsPage = this.findById(pageId);
        if (cmsPage == null){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }

        HashMap<String, String> msgMap = new HashMap<>();

        msgMap.put("pageId",pageId);
        //消息内容
        String msg = JSON.toJSONString(msgMap);
        //获取站点id作为routingkey
        String siteId = cmsPage.getSiteId();
        System.out.print("正在发送信息");
        //发布消息
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.EX_ROUTING_CMS_POSTPAGE,siteId,msg);



        }


        }
