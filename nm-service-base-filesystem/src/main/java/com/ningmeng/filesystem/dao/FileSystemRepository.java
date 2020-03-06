package com.ningmeng.filesystem.dao;

import com.ningmeng.framework.domain.filesystem.FileSystem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by 炫龙 on 2020/2/23.
 */
public interface FileSystemRepository extends MongoRepository<FileSystem,String> {
}
