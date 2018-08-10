/**  
 * All rights Reserved, Designed By www.tct.com
 * @Title:  PersonController.java   
 * @Package com.lcclovehww.controller   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 泰源云景科技     
 * @date:   2018年8月10日 下午4:47:04   
 * @version V1.0 
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技内部传阅，禁止外泄以及用于其他的商业目
 */
package com.lcclovehww.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lcclovehww.po.Person;
import com.lcclovehww.service.PersonService;



/**   
 * @ClassName:  PersonController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 泰源云景
 * @date:   2018年8月10日 下午4:47:04   
 *     
 * @Copyright: 2018 www.tct.com Inc. All rights reserved. 
 * 注意：本内容仅限于泰源云景科技有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
@Controller
@RequestMapping(value="Person")
public class PersonController {

    @Autowired
    private PersonService personservice;

    @RequestMapping("getPerson")
    public String getPerson(){

        Person person=personservice.getPersonById(1);
        System.out.println(person);
        return "success";
    }
    

    @RequestMapping("getsecondall")
    public void getsecondall(HttpServletResponse resp, HttpServletRequest request) throws IOException {


        //得到客户端传递的页码和每页记录数，并转换成int类型
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        //查询参数
        String pname = request.getParameter("pname");
        String age=request.getParameter("page");

        int offset=(pageNumber-1)*pageSize;  //当前起始页
        //定义map集合：
        Map mp=new HashMap();
        //分页：
        mp.put("offset",offset);
        mp.put("pageSize",pageSize);

        if(age.length()>0){
             int page=Integer.parseInt(age);
            mp.put("page",page);
        }
        if(pname.length()>0){
            mp.put("pname",pname);
        }
      //分页：
        List<Person> la = personservice.getallPersonByPname(mp);
       // System.out.println(mp.get("page")+","+mp.get("pname"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total",la.size());
        map.put("rows",la);
        PrintWriter out=resp.getWriter();
        String s= JSON.toJSONString(map);
       // System.out.println(s);
        out.write(s);
        out.flush();
        out.close();

    }

    //删除：
    @RequestMapping("delPerson")
    public void delPerson(HttpServletResponse resp, HttpServletRequest request) throws IOException {

        //得到传过来的字符串：
        String str=request.getParameter("str");
        //将字符串转化为数组：
        JSONArray jsons=JSONArray.parseArray(str);
        for (Object object : jsons) {
            int id=Integer.parseInt(object.toString());
            System.out.println(id);
            personservice.delperson(id);
        }
        //返回：
        PrintWriter out=resp.getWriter();
        out.write("1");
        out.flush();
        out.close();

    }

    //添加：
    @RequestMapping("addPerson")
    public void addPerson(HttpServletResponse resp, HttpServletRequest request) throws IOException {

        //得到传过来的字符串：
        String str=request.getParameter("str");
        //将字符串转化为数组：
        //将str转化为Area对象：
        Person person=JSON.parseObject(str, Person.class);
        System.out.println(person);
        //添加：
        personservice.addperson(person);
        //返回：
        PrintWriter out=resp.getWriter();
        out.write("1");
        out.flush();
        out.close();

    }

    //修改：
    @RequestMapping("updatePerson")
    public void updatePerson(HttpServletResponse resp, HttpServletRequest request) throws IOException {

        //得到传过来的字符串：
        String str=request.getParameter("str");
        //将字符串转化为数组：
        //将str转化为Area对象：
        Person person=JSON.parseObject(str, Person.class);
        //添加：
        personservice.updateperson(person);
        //返回：
        PrintWriter out=resp.getWriter();
        out.write("1");
        out.flush();
        out.close();

    }

}
