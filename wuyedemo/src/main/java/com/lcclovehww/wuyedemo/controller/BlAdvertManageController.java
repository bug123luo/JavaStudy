package com.lcclovehww.wuyedemo.controller;


import com.lcclovehww.wuyedemo.entity.BlAdvertManage;
import com.lcclovehww.wuyedemo.service.IBlAdvertManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lcc
 * @since 2019-03-17
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
