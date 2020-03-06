package com.ningmeng.manage_cms_client.dao;

import com.ningmeng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by 炫龙 on 2020/2/17.
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String>{

}
