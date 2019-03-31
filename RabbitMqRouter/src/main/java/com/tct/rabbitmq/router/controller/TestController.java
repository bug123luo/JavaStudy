/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  TestController.java   
 * @Package com.tct.rabbitmq.router.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2019年3月29日 上午11:15:54   
 * @version V1.0 
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.tct.rabbitmq.router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tct.rabbitmq.router.config.RabbitMqConfig.MsgWriter;

/**   
 * @ClassName:  TestController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2019年3月29日 上午11:15:54   
 *     
 * @Copyright: 2019 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MsgWriter mqttGateway;
    
//    @RequestMapping("/sendMqtt")
    @PostMapping("/sendMqtt")
    public String sendMqtt(String sendData){
    	mqttGateway.sendToMqtt(sendData,"spring-boot-queue-msg");
        return "OK";
    }
}
