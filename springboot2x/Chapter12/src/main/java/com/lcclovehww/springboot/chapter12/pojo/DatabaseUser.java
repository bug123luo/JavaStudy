package com.lcclovehww.springboot.chapter12.pojo;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import lombok.Data;

@Alias("dbuser")
@Data
public class DatabaseUser implements Serializable {
	private static final long serialVersionUID = -7999354910231863737L;
	
	private Long id;
	private String userName;
	private String pwd;
	private Boolean available;
	private String note;

}
