package com.lcclovehww.springboot.chapter12.pojo;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import lombok.Data;

@Alias("dbrole")
@Data
public class DatabaseRole implements Serializable {
	private static final long serialVersionUID = -9187344495447048574L;
	
	private Long id;
	private String roleName;
	private String note;
	
}
