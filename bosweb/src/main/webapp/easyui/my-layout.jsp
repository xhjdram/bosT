<%--
  Created by IntelliJ IDEA.
  User: dell3020mt-41
  Date: 2017/12/4
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyLayout</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>

</head>
<body title="bos物流管理系统" class="easyui-layout">
<%--<body class="easyui-layout">--%>
<!-- 使用div元素描述每个区域 -->
<%--<div title="XXX管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>--%>
<%--<div title="系统菜单" style="width: 200px" data-options="region:'west'">西部区域</div>--%>
<%--<div data-options="region:'center'">中心区域</div>--%>
<%--<div style="width: 100px" data-options="region:'east'">东部区域</div>--%>
<%--<div style="height: 50px" data-options="region:'south'">南部区域</div>--%>
<div title="东部" style="width: 100px" data-options="region:'east'">东部</div>
<div title="系统菜单" style="width:200px" data-options="region:'west'">
    <div class="easyui-accordion" data-options="fit:true">
        <div title="面板一" data-options="iconCls:'icon-cut'">
            <a class="easyui-linkbutton" id="button1">添加选项卡</a>
            <script type="text/javascript">
                $(function () {
                    $("#button1").click(function () {
                        var jQuery = $("#mytabs").tabs("exists", "系统管理");
                        if (jQuery) {
                            $("#mytabs").tabs("select", "系统管理");
                        } else {
                            $("#mytabs").tabs("add", {
                                title: '系统管理',
                                iconCls: 'icon-edit',
                                closable: true,
                                content: '<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"></iframe>'

                            });
                        }

                    });
                });
            </script>

        </div>
        <div title="面板二">
            <ul id="ztree1" class="ztree"></ul>
            <script type="text/javascript">
                $(function () {
                    var setting = {};
                    var zNodes = [
                        {
                            "id": 1, "name": "test1",
                            children: [
                                {"id": 3, "name": "test3"},
                                {"id": 4, "name": "test4"},
                                {"id": 5, "name": "test5"}
                            ]
                        },
                        {"id": 2, "name": "test2"}
                    ];
                    $.fn.zTree.init($("#ztree1"), setting, zNodes);
                });

            </script>
        </div>
        <div title="面板三">
            <ul id="ztree2" class="ztree"></ul>
            <script type="text/javascript">
                $(function () {
                    var setting = {
                        data: {
                            simpleData: {
                                enable: true,
                                idKey: "id",
                                pIdKey: "pId",
                                rootPId: 0
                            }
                        }
                    };
                    var treeNodes = [
                        {"id": 1, "pId": 0, "name": "test1"},
                        {"id": 11, "pId": 1, "name": "test11"},
                        {"id": 12, "pId": 1, "name": "test12"},
                        {"id": 111, "pId": 11, "name": "test111"}
                    ];

                    $.fn.zTree.init($("#ztree2"), setting, treeNodes);
                });

            </script>
        </div>
        <div title="面板四">
            <ul id="ztree3" class="ztree"></ul>
            <script type="text/javascript">
                $(function () {
                    var setting = {
                        data: {
                            simpleData: {
                                enable: true,
                                idKey: "id",
                                pIdKey: "pId",
                                rootPId: 0
                            }
                        },
                        callback: {
                            onClick: function (event, treeId, treeNode) {
//                                alert(treeNode.page);
                                <%--var url = "${pageContext.request.contextPath}"+treeNode.page;--%>
                                if (treeNode.page != undefined) {
                                    var jQuery = $("#mytabs").tabs("exists", treeNode.name);
                                    if (jQuery) {
                                        $("#mytabs").tabs("select", treeNode.name);
                                    } else {
                                        $("#mytabs").tabs("add", {
                                            title: treeNode.name,
                                            iconCls: 'icon-edit',
                                            closable: true,
                                            content:
                                            '<iframe frameborder="0" height="100%" width="100%" src="' + treeNode.page +
                                            '"></iframe>'
                                        });
                                    }
                                }
                            }
                        }
                    };
                    $.get("${pageContext.request.contextPath}/json/menu.json", {},
                        function (data) {
                            $.fn.zTree.init($("#ztree3"), setting, data);
                        });
                });
            </script>
        </div>
    </div>

</div>
<div title="南部" data-options="region:'south'">南部</div>
<div title="北部" style="height: 100px" data-options="region:'north'">北部</div>
<div title="中部" data-options="region:'center'">
    <div id="mytabs" class="easyui-tabs" data-options="fit:true">
        <div title="面板一" data-options="iconCls:'icon-cut'"></div>
        <div title="面板二" data-options="closable:true">22</div>
        <div title="面板三">333</div>
    </div>
</div>

</body>
</html>
