<%--
  Created by IntelliJ IDEA.
  User: dell3020mt-41
  Date: 2017/12/12
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"></script>
</head>
<body>
<iframe name="my_frame" style="display: none"></iframe>
<form target="my_frame" action="888" method="post" enctype="multipart/form-data">
    <input type="file" name="1" />
    <input type="submit" name="上传"/>
</form>
</body>
</html>
