<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	
	 <script type="text/javascript">
		 window.onerror=function (){return true;}
        $(function(){
            var option = {
                theme:'default',
                expandLevel : 1,
                beforeExpand : function($treeTable, id) {
                    //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                    if ($('.' + id, $treeTable).length) { return; }
                    //这里的html可以是ajax请求
                    $.ajax({
        				type: 'get',
        				url: '${ctx}/sys/area/lists',
        				data: {'id':id},
        				dataType: 'json',
        				success: function(data){
        					var html = "";
        					$.each(data, function(i, n){
        						var t = n.children;
        						html += '<tr id="'+ n.code + '" pid="'+ n.parentCode + '" haschild="'+t+'" > <td>  '+ n.name + '</td><td>'+ n.code + '</td> <td>'+ n.type + '</td> <td>'+ n.remarks + '</td> <td><a href="${ctx}/sys/area/form?id='+n.id+'"  >修改</a> <a href="${ctx}/sys/area/delete?id='+n.id+'" onclick="return confirmx(\'要删除该区域及所有子区域项吗？\', this.href)" > 删除</a> <a href="${ctx}/sys/area/form?parent.id='+n.id+'">添加下级区域</a> </td></tr>';
        					})
        					$treeTable.addChilds(html);
        				}
        			});
                },
                onSelect: function($treeTable, id) {
                    window.console && console.log('onSelect:' + id);
                }
             }
            $('#treeTable1').treeTable(option);
            
        });
        
        
    </script> 
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/area/">区域列表</a></li>
		<shiro:hasPermission name="sys:area:edit"><li><a href="${ctx}/sys/area/form">区域添加</a></li></shiro:hasPermission>
	</ul>
		<tags:message content="${message}"/>
	<table id="treeTable1" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>区域名称</th>
			<th>区域编码</th>
			<th>区域类型</th>
			<th>备注</th>
			<shiro:hasPermission name="sys:area:edit">
				<th>操作</th>
			</shiro:hasPermission>
		</tr>
		<c:forEach items="${list}" var="area">
			<tr id="${area.code}" pid="${area.parent.code}" haschild="${area.childList.size() gt 0 ? true : '' }"  >
				<td>${area.name}</td>
				<td>${area.code}</td>
				<td>${fns:getDictLabel(area.type, 'sys_area_type', '无')}</td>
				<td>${area.remarks}</td>
				<shiro:hasPermission name="sys:area:edit">
					<td><a href="${ctx}/sys/area/form?id=${area.id}">修改</a> <a
						href="${ctx}/sys/area/delete?id=${area.id}"
						onclick="return confirmx('要删除该区域及所有子区域项吗？', this.href)">删除</a> <a
						href="${ctx}/sys/area/form?parent.id=${area.id}">添加下级区域</a></td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
	</table>
</body>
</html>