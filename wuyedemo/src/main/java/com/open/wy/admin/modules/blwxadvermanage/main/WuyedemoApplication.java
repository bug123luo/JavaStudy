package com.open.wy.admin.modules.blwxadvermanage.main;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.open.wy.admin.modules.blwxadvermanage.*"})
@MapperScan("com.open.wy.admin.modules.blwxadvermanage.mapper")
public class WuyedemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuyedemoApplication.class, args);
    }

}
