package com.lcclovehww.boy;/**
 * All rights Reserved, Designed By www.lcclovehww.com
 *
 * @Title: boy
 * @Package com.lcclovehww.boy
 * @Description: 用一句话描述该文件做什么
 * @author: luochengcong
 * @date: 2018 08 30 上午 10:15
 * @version V1.0
 * @Copyright: 2018 www.lcclovehww.com Inc. All rights reserved.
 * 注意：本内容仅限于小聪工作室内部传阅，禁止外泄以及用于其他的商业目
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: HelloController
 * @Description:(这里用一句话描述这个类的作用)
 * @author: luochengcong
 * @date: 2018 08 30 上午 10:15
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳天源迪科信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目  */

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private BoyProperties boyProperties;

    @RequestMapping(value = "/{id}/say",method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id){
        return "id: "+id;
    }

    @RequestMapping(value = "/say/{id}",method = RequestMethod.GET)
    public String say2(@PathVariable("id")Integer myId){
        return "id: " + myId;
    }

    @RequestMapping(value = "/say",method = RequestMethod.GET)
    public String say3(@RequestParam(value="id") Integer myId){
        return "id: "+myId;
    }
}


