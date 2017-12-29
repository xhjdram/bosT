<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理定区/调度排班</title>
    <!-- 导入jquery核心类库 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <!-- 导入easyui类库 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/default.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
    <script
            src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        function doAdd() {
            $('#addDecidedzoneWindow').window("open");
        }

        function doEdit() {
            alert("修改...");
        }

        function doDelete() {
            alert("删除...");
        }

        function doSearch() {
            $('#searchWindow').window("open");
        }

        function doAssociations() {
//            $('#customerWindow').window('open');
            var rows = $('#grid').datagrid('getSelections');

            if(rows.length!=1){
                $.messager.alert('Warning','请选择关联的定区仅可以选择一行');
            }else {
                var id = rows[0].id;
//                alert(id);
                $('#customerWindow').window('open');
                $('#noassociationSelect').empty();
                $('#associationSelect').empty();
                var url ="decidedzoneAction_findNoassociationSelect";
                var url2="decidedzoneAction_findAssociationSelect";
                $.get(url, function(data){
//                    alert(data.length);
                    for(var i=0;i<data.length;i++){
                        var name=data[i].name;
                        var id=data[i].id;
                        var telephone=data[i].telephone;
                        name=telephone+name;
//                        alert(name);
                        $('#noassociationSelect').append("<option value='"+id+"'>"+name+"</option>");
                    }

                });
                $.post(url2,{"id":id}, function(data){
//                    alert(data.length);
                    for(var i=0;i<data.length;i++){
                        var name=data[i].name;
                        var id=data[i].id;
                        var telephone=data[i].telephone;
                        name=telephone+name;
//                        alert(name);
                        $('#associationSelect').append("<option value='"+id+"'>"+name+"</option>");
                    }

                });

            }

   }

        //工具栏
        var toolbar = [{
            id: 'button-search',
            text: '查询',
            iconCls: 'icon-search',
            handler: doSearch
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-edit',
            text: '修改',
            iconCls: 'icon-edit',
            handler: doEdit
        }, {
            id: 'button-delete',
            text: '删除',
            iconCls: 'icon-cancel',
            handler: doDelete
        }, {
            id: 'button-association',
            text: '关联客户',
            iconCls: 'icon-sum',
            handler: doAssociations
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            title: '定区编号',
            width: 120,
            align: 'center'
        }, {
            field: 'name',
            title: '定区名称',
            width: 120,
            align: 'center'
        }, {
            field: 'bcStaffEntity.name',
            title: '负责人',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.bcStaffEntity.name;
            }
        }, {
            field: 'bcStaffEntity.telephone',
            title: '联系电话',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.bcStaffEntity.telephone;
            }
        }, {
            field: 'bcStaffEntity.station',
            title: '所属公司',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                return row.bcStaffEntity.station;
            }
        }]];

        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 收派标准数据表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: true,
                rownumbers: true,
                striped: true,
                pageList: [30, 50, 100],
                pagination: true,
                toolbar: toolbar,
                url: "decidedzoneAction_pageQuery",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow
            });

            // 添加、修改定区
            $('#addDecidedzoneWindow').window({
                title: '添加修改定区',
                width: 600,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

            // 查询定区
            $('#searchWindow').window({
                title: '查询定区',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });
            $("#btn").click(function () {
                alert("执行查询...");
            });

        });

        function doDblClickRow() {
            var rows = $('#grid').datagrid('getSelections');
            var id=rows[0].id;
            alert(id);
            if(rows.length!=1){
                alert("一次仅可选择一行");
            }else {
                $('#association_subarea').datagrid({
                    fit: true,
                    border: true,
                    rownumbers: true,
                    striped: true,
                    url: "decidedzoneAction_associationSubarea?id="+id,
                    columns: [[{
                        field: 'id',
                        title: '分拣编号',
                        width: 120,
                        align: 'center'
                    }, {
                        field: 'province',
                        title: '省',
                        width: 120,
                        align: 'center',
                        formatter: function (data, row, index) {
                            return row.bcRegionByRegionId.province;
                        }
                    }, {
                        field: 'city',
                        title: '市',
                        width: 120,
                        align: 'center',
                        formatter: function (data, row, index) {
                            return row.bcRegionByRegionId.city;
                        }
                    }, {
                        field: 'district',
                        title: '区',
                        width: 120,
                        align: 'center',
                        formatter: function (data, row, index) {
                            return row.bcRegionByRegionId.district;
                        }
                    }, {
                        field: 'addresskey',
                        title: '关键字',
                        width: 120,
                        align: 'center'
                    }, {
                        field: 'startnum',
                        title: '起始号',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'endnum',
                        title: '终止号',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'single',
                        title: '单双号',
                        width: 100,
                        align: 'center'
                    }, {
                        field: 'position',
                        title: '位置',
                        width: 200,
                        align: 'center'
                    }]]
                });
                $('#association_customer').datagrid({
                    fit: true,
                    border: true,
                    rownumbers: true,
                    striped: true,
                    url: "decidedzoneAction_associationCustomer?id="+id,
                    columns: [[{
                        field: 'id',
                        title: '客户编号',
                        width: 120,
                        align: 'center'
                    }, {
                        field: 'name',
                        title: '客户名称',
                        width: 120,
                        align: 'center'
                    }, {
                        field: 'station',
                        title: '所属单位',
                        width: 120,
                        align: 'center'
                    }]]
                });
            }

        }
    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
<div region="south" border="false" style="height:150px">
    <div id="tabs" fit="true" class="easyui-tabs">
        <div title="关联分区" id="subArea"
             style="width:100%;height:100%;overflow:hidden">
            <table id="association_subarea"></table>
        </div>
        <div title="关联客户" id="customers"
             style="width:100%;height:100%;overflow:hidden">
            <table id="association_customer"></table>
        </div>
    </div>
</div>

<!-- 添加 修改分区 -->
<div class="easyui-window" title="定区添加修改" id="addDecidedzoneWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
        </div>
        <script type="text/javascript">
            $(function () {

                $('#save').click(
                        function () {
                            $('#saveDecidezone').submit();
                        }
                );
            });

        </script>
    </div>

    <div style="overflow:auto;padding:5px;" border="false">
        <form id="saveDecidezone" action="decidedzoneAction_save" method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">定区信息</td>
                </tr>
                <tr>
                    <td>定区编码</td>
                    <td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>定区名称</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>选择负责人</td>
                    <td>
                        <input class="easyui-combobox" name="bcStaffEntity.id"
                               data-options="valueField:'id',textField:'name',url:'staffAction_findStaff'"/>
                    </td>
                </tr>
                <tr height="300">
                    <td valign="top">关联分区</td>
                    <td>
                        <table id="subareaGrid" class="easyui-datagrid" border="false" style="width:300px;height:300px"
                               data-options="url:'subareaAction_findSubarea',fitColumns:true,singleSelect:false">
                            <thead>
                            <tr>
                                <th data-options="field:'subarea_id',width:30,checkbox:true">编号</th>
                                <th data-options="field:'addresskey',width:150">关键字</th>
                                <th data-options="field:'position',width:200,align:'right'">位置</th>
                            </tr>
                            </thead>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<!-- 查询定区 -->
<div class="easyui-window" title="查询定区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false"
     style="top:20px;left:200px">
    <div style="overflow:auto;padding:5px;" border="false">
        <form>
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询条件</td>
                </tr>
                <tr>
                    <td>定区编码</td>
                    <td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>所属单位</td>
                    <td><input type="text" name="staff.station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<!-- 关联客户窗口 -->
<div modal=true class="easyui-window" title="关联客户窗口" id="customerWindow" collapsible="false" closed="true"
     minimizable="false"
     maximizable="false" style="top:20px;left:200px;width: 400px;height: 300px;">
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="customerForm"
              action="${pageContext.request.contextPath }/decidedzoneAction_assigncustomerstodecidedzone.action"
              method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="3">关联客户</td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="id" id="customerDecidedZoneId"/>
                        <select id="noassociationSelect" multiple="multiple" size="10"></select>
                    </td>
                    <td>
                        <input type="button" value="》》" id="toRight"><br/>
                        <input type="button" value="《《" id="toLeft">
                        <script type="text/javascript">
                            $(function () {
                               $('#toRight').click(function () {
                                 $('#associationSelect').append($('#noassociationSelect option:selected'));
                               }) ;

                            });
                            $(function () {
                                $('#toLeft').click(function () {
                                    $('#noassociationSelect').append($('#associationSelect option:selected'));
                                }) ;

                            });
                        </script>
                    </td>
                    <td>
                        <select id="associationSelect" name="customerIds" multiple="multiple" size="10">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="3"><a id="associationBtn" href="#" class="easyui-linkbutton"
                                       data-options="iconCls:'icon-save'">关联客户</a></td>
                </tr>
                <
                <script type="text/javascript">
                    $(function () {
                       $('#associationBtn').click(function () {
                           var rows = $('#grid').datagrid('getSelections');
                           var id = rows[0].id;
                           $('input[name=id]').val(id);
                           $('#associationSelect option').attr("selected","selected");
                           $('#customerForm').submit();
                       });
                    });
                </script>
            </table>
        </form>
    </div>
</div>
</body>
</html>