package com.open.wy.admin.modules.blwxadvermanage.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.wy.admin.modules.blwxadvermanage.entity.BlAdvertManage;
import com.open.wy.admin.modules.blwxadvermanage.service.IBlAdvertManageService;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lcc
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/wxadvertmanage")
public class BlAdvertManageController {

    @Autowired
    IBlAdvertManageService iBlAdvertManageService;

    @GetMapping("/list")
    public List<BlAdvertManage> getAdverList(){

        List<BlAdvertManage> adverList = iBlAdvertManageService.list(null);

        return adverList;
    }
}
