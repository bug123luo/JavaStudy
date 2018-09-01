package com.lcclovehww.boy;/**
 * All rights Reserved, Designed By www.lcclovehww.com
 *
 * @Title: boy
 * @Package com.lcclovehww.boy
 * @Description: 用一句话描述该文件做什么
 * @author: luochengcong
 * @date: 2018 08 30 上午 10:52
 * @version V1.0
 * @Copyright: 2018 www.lcclovehww.com Inc. All rights reserved.
 * 注意：本内容仅限于小聪工作室内部传阅，禁止外泄以及用于其他的商业目
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: BoyProperties
 * @Description:(这里用一句话描述这个类的作用)
 * @author: luochengcong
 * @date: 2018 08 30 上午 10:52
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳天源迪科信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目  */
@Component
@ConfigurationProperties(prefix = "boy")
public class BoyProperties {
    private  String cupsize;

    private Integer age;

    public String getCupsize() {
        return cupsize;
    }

    public void setCupsize(String cupsize) {
        this.cupsize = cupsize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
