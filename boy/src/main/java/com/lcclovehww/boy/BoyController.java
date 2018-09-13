package com.lcclovehww.boy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoyController {

    @Autowired
    private BoyRepository boyRepository;

    @Autowired
    BoyService boyService;
    /**
     * 查询所有男生列表
     * @return
     */
    @GetMapping(value = "/boys")
    public List<Boy> boyList(){
        return boyRepository.findAll();
    }

    /**
     * 添加一个男生
     * @param cupsize
     * @param age
     * @return
     */
    @PostMapping(value = "/boys")
    public Boy boyAdd(@RequestParam("cupSize") String cupsize,
                            @RequestParam("age") Integer age){
        Boy boy =  new Boy();
        boy.setCupsize(cupsize);
        boy.setAge(age);

        return boyRepository.save(boy);
    }

    @GetMapping(value = "/boys/{id}")
    public Boy boyFindOne(@PathVariable("id") Integer id){
        return boyRepository.findById(id).get();
    }

    @PutMapping(value = "/boys/{id}")
    public Boy boyUpdate(@PathVariable("id") Integer id,
                         @RequestParam("cupSize") String cupsize,
                         @RequestParam("age") Integer age){

        Boy boy = new Boy();
        boy.setAge(id);
        boy.setCupsize(cupsize);
        boy.setAge(age);
        return boyRepository.save(boy);
    }

    @DeleteMapping(value = "/boys/{id}")
    public void boyDelete(@PathVariable("id") Integer id){
        boyRepository.deleteById(id);
    }

    //通过年龄查询女生列表
    @GetMapping(value = "/boys/age/{age}")
    public List<Boy> boyListByAge(@PathVariable("age") Integer age){
        return boyRepository.findByAge(age);
    }

    @PostMapping(value = "/boys/two")
    public void boyTwo(){
        boyService.insertTwo();
    }
}
