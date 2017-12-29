<%--
  Created by IntelliJ IDEA.
  User: dell3020mt-41
  Date: 2017/12/12
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"></script>
    <script type="text/javascript">
        $(function () {
           $('#ocupload').upload(
               {
                   action:"xxxx",
                   name:"ocupload"
               }
           );
        });
    </script>
</head>
<body>
<input type="button" id="ocupload">
</body>
</html>
