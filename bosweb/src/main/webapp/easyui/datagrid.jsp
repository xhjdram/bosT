<%--
  Created by IntelliJ IDEA.
  User: dell3020mt-41
  Date: 2017/12/8
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DataGrid</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <link id="easyuiTheme" rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/default.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<body>
<table class="easyui-datagrid">
    <thead>
    <tr>
        <th data-options="field:'name'">姓名</th>
        <th data-options="field:'age'">年龄</th>
        <th data-options="field:'sex'">性别</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>mark</td>
        <td>2</td>
        <td>1</td>
    </tr>
    <tr>
        <td>Uzi</td>
        <td>2</td>
        <td>1</td>
    </tr>
    </tbody>
</table>
</body>
</html>
