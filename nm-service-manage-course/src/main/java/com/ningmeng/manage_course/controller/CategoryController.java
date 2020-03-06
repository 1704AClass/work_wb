package com.ningmeng.manage_course.controller;

import com.ningmeng.api.course.CategoryControllerApi;
import com.ningmeng.framework.domain.course.ext.CategoryNode;
import com.ningmeng.manage_course.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 炫龙 on 2020/2/19.
 */
@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerApi {

    @Autowired
    CategoryService categoryService;

    @Override
    @GetMapping("/list")
    public CategoryNode findList() {
        return categoryService.findList();
    }
}
