<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html  class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>管理系统</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!--    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />-->
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="<%=basePath%>admin/css/font.css">
    <link rel="stylesheet" href="<%=basePath%>admin/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript"src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.min.js"></script>
    <script src="<%=basePath%>admin/lib/layui/layui.js" charset="utf-8"></script>

    <script type="text/javascript" src="<%=basePath%>admin/js/xadmin.js"></script>
    <script type="text/javascript" src="<%=basePath%>admin/js/cookie.js"></script>
    <script>
        // 是否开启刷新记忆tab功能
        // var is_remember = false;
    </script>
</head>

<body>
<div class="x-body">


        <div class="layui-form-item">
            <label  class="layui-form-label">
                <span class="x-red">*</span>退房时间
            </label>
            <div class="layui-input-block">
                <input type="text" id="time" class="layui-input">
            </div>
        </div>

    <div class="layui-form-item">
        <label  class="layui-form-label">
            <span class="x-red">*</span>备注信息
        </label>
        <div class="layui-input-block">
            <input type="text" id="remark" class="layui-input">
        </div>
    </div>


</div>
<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;
        var id=localStorage.getItem("outId");
        $.ajax({
            url:"<%=basePath%>checkOutServlet?flag=queryId&id="+id,
            type:"post",
            data:{"ids":id},
            success:function (result) {
                debugger
                    //渲染信息
                    $("#time").val(result.time)
                   $("#remark").val(result.remark2);

            }
        });

    });
</script>

</body>

</html>