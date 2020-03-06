package com.ningmeng.manage_course.dao;

import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.manage_course.client.CmsPageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 炫龙 on 2020/2/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignTest {

    @Autowired
    CmsPageClient cmsPageClient;
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testFeign() {
        //通过服务id调用cms的查询页面接口
        CmsPage cmsPage = cmsPageClient.findById("5a754adf6abb500ad05688d9");
        System.out.println(cmsPage);
    }
    @Test
    public void testFeignaa() {
        //通过服务id调用cms的查询页面接口
        String aa = cmsPageClient.aa();
        System.out.println(aa);
    }

    //负载均衡调用
    @Test
    public void testRibbon() {
//服务id
        String serviceId = "nm-service-manage-cms";
       // for(int i=0;i<10;i++){
//通过服务id调用
            ResponseEntity<CmsPage> forEntity = restTemplate.getForEntity("http://" + serviceId
                    + "/cms/findById/5a754adf6abb500ad05688d9", CmsPage.class);
            CmsPage cmsPage = forEntity.getBody();
            System.out.println(cmsPage);

    }

}
