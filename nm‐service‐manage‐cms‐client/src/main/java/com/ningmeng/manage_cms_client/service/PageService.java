package com.ningmeng.manage_cms_client.service;

import org.springframework.stereotype.Service;

/**
 * Created by 炫龙 on 2020/2/17.
 */
@Service
public class PageService {

    //将页面html保存到页面物理路径
    public void PageTest(String pageId){
        System.out.print(pageId);
        System.out.print("成功了");
    }
}
