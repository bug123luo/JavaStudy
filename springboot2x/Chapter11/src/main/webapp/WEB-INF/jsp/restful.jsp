<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sprint Boot restful</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
/* 
	测试javascript 代码
*/
//post user 测试代码
/* 	function post(){
		var params = {
				'userName':'user_name_new21',
				'sexCode':2,
				'note':"note_new2"
		}
		$.post({
			url:"./user",
			contentType:"application/json",
			data:JSON.stringify(params),
			success:function(result){
				if(result == null || result.id==null){
					alert("插入失败");
					return;
				}
				alert("插入成功");
			}
		});
	}
	post(); */
	
//Get user 测试代码
/* function get(){
		$.get("./1",function(user,status){
			if(user==null){
				alert("结果为空");
			}else{
				alert("用户信息为"+JSON.stringify(user));
			}
		});
	}
	get(); */
//Get user list 根据用户名和note 模糊查询
function get(){
		$.get("./users/userName/note/0/5",function(users,status){
			if(users==null){
				alert("结果为空");
			}else{
				alert("用户信息为"+JSON.stringify(users));
			}
		});
	}
	get();
	
</script>
</head>
<body>
	<h1>测试RESTful下的请求</h1>
</body>
</html>