package com.ningmeng.manage_cms;

import com.ningmeng.framework.domain.cms.CmsPage;
import com.ningmeng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by 炫龙 on 2020/2/11.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    //分页测试
    @Test
    public void testFindPage(){
        int page=0;
        int size=10;
        Pageable pageable=PageRequest.of(page,size);
        Page<CmsPage> cmsPagePage= cmsPageRepository.findAll(pageable);
        System.out.print(cmsPagePage);
    }
    @Test
    public void  findAll(){
        List<CmsPage> list=cmsPageRepository.findAll();
      //  cmsPageRepository.findById()
        for (CmsPage cmsPage:list) {

            System.out.print(cmsPage);
        }
    }
}
