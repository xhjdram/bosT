<%--
  Created by IntelliJ IDEA.
  User: dell3020mt-41
  Date: 2017/12/25
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit</title>
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
<table id="mytable"></table>
<script type="text/javascript">
    //   http://localhost:8080/bost/easyui/json/datagrid.json
    //    http://localhost:8080/json/datagrid.json
    $('#mytable').datagrid({
        url: '/bost/json/datagrid.json',
        columns: [[
            {
                field: 'name', tittle: 'name', editor: {type: 'validatebox', options: {}}
            },
            {
                field: 'sex', tittle: '性别', editor: {type: 'validatebox', options: {}}
            },
            {
                field: 'age', tittle: 'age', editor: {type: 'numberbox', options: {}}
            }
        ]
        ],
        toolbar: [{
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                var jQuery = $('#mytable').datagrid('getSelected');
                if (jQuery != null) {
                    var datagrid = $('#mytable').datagrid('getRowIndex', jQuery);
                    alert(datagrid);
                    $('#mytable').datagrid('beginEdit', datagrid);
                } else {
                    $('#mytable').datagrid('beginEdit', 0);
                }
            }
        },
            {
                text:'保存',
                iconCls:'icon-save',
                handler: function () {
                    var jQuery = $('#mytable').datagrid('getSelected');
                    if (jQuery != null) {
                        var datagrid = $('#mytable').datagrid('getRowIndex', jQuery);
                        alert(datagrid);
                        $('#mytable').datagrid('endEdit', datagrid);
                    } else {
                        $('#mytable').datagrid('endEdit', 0);
                    }
                }

            },
            {
                text:'取消编辑',
                iconCls:'icon-undo',
                handler: function () {
                    var jQuery = $('#mytable').datagrid('getSelected');
                    if (jQuery != null) {
                        var datagrid = $('#mytable').datagrid('getRowIndex', jQuery);
                        alert(datagrid);
                        $('#mytable').datagrid('cancelEdit', datagrid);
                    } else {
                        $('#mytable').datagrid('cancelEdit', 0);
                    }
                }
            }
        ],
        onAfterEdit:function(index,data,changes){
            console.info(data);
            $.post();
        }
    });
</script>
</body>
</html>
