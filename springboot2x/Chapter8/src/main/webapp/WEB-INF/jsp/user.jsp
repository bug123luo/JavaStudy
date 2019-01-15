<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Spring Boot</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function post(user) {
		var url = "./save";
		$.post({
			url:url,
			contentType:"application/json",
			data:JSON.stringify(user),
			success:function(result, status){
				if(result ==null || result.id ==null){
					alert("插入失败");
					return;
				}
			}
		});
	
	}
	
	for (var i = 1; i < 10; i++) {
		var user = {
				'id':i,
				'userName':'user_name_'+i,
				'note':"note_"+i,
				'roles':[{
					'id':i,
					'roleName':'role_'+i,
					'note':'note_'+i
				},{
					'id':i+1,
					'roleName':'role_'+(i+1),
					'note':'note_'+(i+1)
					
				}]
		};
		post(user);
	}
</script>
</head>
<body>
	<h1>操作MongoDB文档</h1>
</body>
</html>