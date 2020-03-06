package com.ningmeng.manage_media.service;

import com.ningmeng.framework.domain.media.MediaFile;
import com.ningmeng.framework.domain.media.request.QueryMediaFileRequest;
import com.ningmeng.framework.model.response.CommonCode;
import com.ningmeng.framework.model.response.QueryResponseResult;
import com.ningmeng.framework.model.response.QueryResult;
import com.ningmeng.manage_media.dao.MediaFileRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * Created by 炫龙 on 2020/3/6.
 */
@Service
public class MediaFileService {

    private  static Logger logger= LoggerFactory.getLogger(MediaFileService.class);


    @Autowired
    MediaFileRepository mediaFileRepository;

    //文件列表分页查询
    public QueryResponseResult findList(int page, int size,
                                        QueryMediaFileRequest queryMediaFileRequest){

        MediaFile mediaFile = new MediaFile();
        if(queryMediaFileRequest == null){
            queryMediaFileRequest = new QueryMediaFileRequest();
    }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("tag", ExampleMatcher.GenericPropertyMatchers.contains())//tag字段
                .withMatcher("fileOriginalName", ExampleMatcher.GenericPropertyMatchers.contains()) //文件原始名称模糊匹配
                .withMatcher("processStatus", ExampleMatcher.GenericPropertyMatchers.exact());//处理状态精确匹配（默认）


        //查询条件对象
        if(StringUtils.isNotEmpty(queryMediaFileRequest.getTag())){
            mediaFile.setFileOriginalName(queryMediaFileRequest.getFileOriginalName());
        }

        if(StringUtils.isNotEmpty(queryMediaFileRequest.getFileOriginalName())){
            mediaFile.setFileOriginalName(queryMediaFileRequest.getFileOriginalName());
        }
        if(StringUtils.isNotEmpty(queryMediaFileRequest.getProcessStatus())){
            mediaFile.setProcessStatus(queryMediaFileRequest.getProcessStatus());
        }

        //定义example实例
        Example<MediaFile> ex = Example.of(mediaFile, exampleMatcher);

        page = page-1;
        //分页参数

        PageRequest pageable = new PageRequest(page, size);
        //分页查询
        Page<MediaFile> all = mediaFileRepository.findAll(ex, pageable);
        QueryResult<MediaFile> mediaFileQueryResult = new QueryResult<MediaFile>();
        mediaFileQueryResult.setList(all.getContent());
        mediaFileQueryResult.setTotal(all.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS,mediaFileQueryResult);
    }


}
