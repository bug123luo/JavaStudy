package com.lcclovehww.boy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoyService {

    @Autowired
    private BoyRepository boyRepository;

    @Transactional
    public void insertTwo(){
        Boy boy1 = new Boy();
        boy1.setAge(18);
        boy1.setCupsize("B");
        boyRepository.save(boy1);

        int i=1;
        int j=i/0;

        Boy boy2 = new Boy();
        boy2.setAge(19);
        boy2.setCupsize("A");
        boyRepository.save(boy2);
    }
}
