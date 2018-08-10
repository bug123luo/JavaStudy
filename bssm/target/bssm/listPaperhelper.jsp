<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <!-- 引入bootstrap样式 -->
	<link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<!-- 引入bootstrap-table样式 -->
	<link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
	<!-- jquery -->
	<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
	<!-- bootstrap-table.min.js -->
	<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
	<!-- 引入中文语言包 -->
	<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
<script type="text/javascript">
    function initTable() {
        //初始化表格,动态从服务器加载数据
        $("#cusTable").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            url: "/bssm/getsecondall", //获取数据的Servlet地址
            toolbar: '#toolbar',                //工具按钮用哪个容器
            pagination: true, //启动分页
            pageSize: 10,  //每页显示的记录数
            pageNumber:1, //当前第几页
            pageList: [5, 10, 15, 20, 25],  //记录数可选列表
            search: false,  //是否启用查询
            showColumns: true,  //显示下拉框勾选要显示的列
            showRefresh: true,  //显示刷新按钮
            sidePagination: "server", //表示服务端请求
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "pid",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType : "undefined",
            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    pageNumber: params.pageNumber,
                    pageSize: params.pageSize,
                    pname : $("#txt_search_departmentname").val(),
                    page:$("#txt_search_statu").val()
                };
                return param;
            },
            columns: [{
                checkbox: true
            }, {
                field: 'pname',
                title: '名称'
            }, {
                field: 'page',
                title: '年龄'
            }, {
                field: 'pid',
                title: 'id'
            }, ]

        });
    }

    $(document).ready(function () {
        //调用函数，初始化表格
        initTable();

        //当点击查询按钮的时候执行
        $("#btn_query").bind("click", initTable);

        //删除
        $('#btn_delete').on("click", function () {
            var row=$.map($("#cusTable").bootstrapTable('getSelections'),function(row){
                return row ;
            });
            var ids=[];
            for (var i = 0; i < row.length; i++) {
                //获取自定义table 的中的checkbox值
                var id=row[i].pid;   //OTRECORDID这个是你要在列表中取的单个id
                ids.push(id); //然后把单个id循环放到ids的数组中
            }
            var str=JSON.stringify(ids);
             //批量删除：
            $.ajax({
                type:"POST",
                url:"/delPerson.action",
                data:"str="+str,
                success:function(msg){
                    // alert("返回的是："+msg);
                    if(msg==1){
                       // alert("删除成功！");
                        $('#cusTable').bootstrapTable('refresh');
                    }else{
                        alert("删除失败！");
                    }
                }
            });
        });

        //添加：
        $('#btn_add').on("click", function () {
            $('#myModal').modal();
        });

        //修改
        $('#btn_edit').on("click", function () {

            var row=$.map($("#cusTable").bootstrapTable('getSelections'),function(row){
                return row ;
            });
            if(row.length==1){
                for (var i = 0; i < row.length; i++) {
                    alert(row[i].pid);
                    //打开弹出框：
                    $('#updatemyModal').modal();
                    $('#updatepname').val(row[i].pname);
                    $('#updatepage').val(row[i].page);
                }
            }
        });


    })

//提交添加：
    function mya(){
        var addpname=$('#addpname').val();
        var addpage=$('#addpage').val();
        //实例化一个对象：
        var person={"pname":addpname,"page":addpage};
        //将对象转换成字符串
        var str=JSON.stringify(person);
        alert(str);
        $.ajax({
            type:"POST" ,
            url:"/addPerson.action",
            data:"str="+str,
            success:function(msg){
                if(msg==1){
                    $('#cusTable').bootstrapTable('refresh');
                    $('#ff_p').form('clear');
                }else{
                    alert('添加失败');
                }

            }
        });
    }

    //修改提交：
    function myb(){
        var addpname=$('#updatepname').val();
        var addpage=$('#updatepage').val();
        //实例化一个对象：
        var person={"pname":addpname,"page":addpage};
        //将对象转换成字符串
        var str=JSON.stringify(person);
        $.ajax({
            type:"POST" ,
            url:"/addPerson.action",
            data:"str="+str,
            success:function(msg){
                if(msg==1){
                    $('#cusTable').bootstrapTable('refresh');
                }else{
                    alert('修改失败');
                }

            }
        });
    }

</script>

<div class="panel-body" style="padding-bottom:0px;">
    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group" style="margin-top:15px">
                    <label class="control-label col-sm-1" for="txt_search_departmentname">姓名</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="txt_search_departmentname">
                    </div>
                    <label class="control-label col-sm-1" for="txt_search_statu">年龄</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="txt_search_statu">
                    </div>
                    <div class="col-sm-4" style="text-align:left;">
                        <button type="button" onclick="initTable()" id="btn_query" class="btn btn-primary">查询</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div id="toolbar" class="btn-group">
        <button id="btn_add" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        </button>
        <button id="btn_edit" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
        </button>
        <button id="btn_delete" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
        </button>
    </div>

    <table class="table table-hover" id="cusTable">
    </table>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form id="ff_p" method="post">
                    <div class="form-group">
                        <label for="addpname">姓名</label>
                        <input type="text" name="namepname" class="form-control" id="addpname" placeholder="姓名">
                    </div>

                    <div class="form-group">
                        <label for="addpage">年龄</label>
                        <input type="text" name="namepid" class="form-control" id="addpage" placeholder="年龄">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                        <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="mya()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="updatemyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="updatemyModalLabel">修改</h4>
            </div>
            <div class="modal-body">
                <form id="updateff_p" method="post">
                    <div class="form-group">
                        <label for="updatepname">姓名</label>
                        <input type="text" name="namepname" class="form-control" id="updatepname" placeholder="姓名">
                    </div>

                    <div class="form-group">
                        <label for="updatepage">年龄</label>
                        <input type="text" name="namepid" class="form-control" id="updatepage" placeholder="年龄">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                        <button type="button" id="btn_update" class="btn btn-primary" data-dismiss="modal" onclick="myb()"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>