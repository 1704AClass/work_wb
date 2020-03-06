package com.ningmeng.api.fileSystem;

import com.ningmeng.framework.domain.filesystem.response.UploadFileResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 炫龙 on 2020/2/23.
 */
@Api(value = "文件系统服务接口",description = "提供文件系统服务接口的常规操作")
public interface FileSystemControllerApi {

    /*** 上传文件
     * @param multipartFile 文件
     * @param filetag 文件标签
     * @param businesskey 业务key
     * @param metedata 元信息,json格式
     * @return
     * */
    @ApiOperation("上传课程图片")
    public UploadFileResult upload(MultipartFile multipartFile, String filetag, String businesskey, String metadata);
}
