<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<base href="${pageContext.request.contextPath}">
	<title>主题管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style type="text/css">
	    #contentTable {
			word-break:break-all;
			word-wrap:break-word;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/yb/theme/">主题列表</a></li>
		<shiro:hasPermission name="yb:theme:edit"><li><a href="${ctx}/yb/theme/form">主题添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="themeCondition" action="${ctx}/yb/theme/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>文字样式：</label>
		<form:select path="eqWordStyle" class="input-small">
			<form:option value="" label="请选择"/>
			<form:options items="${fns:getDictList('yb_word_style')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
		</form:select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>文字样式</th>
			<th>图片</th>
			<th>排序</th>
			<shiro:hasPermission name="yb:theme:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="theme">
			<tr>
				<td>${fns:getDictLabel(theme.wordStyle,"yb_word_style","未知")}</td>
				<td><a class="fancybox" target="_blank" href="${theme.imgId}"><img style="max-width:60px;max-height:60px;border:0;padding:3px;" src="${theme.imgId}" onerror="javascript:this.src='${ctxStatic}/images/error.png';"/></a>&nbsp;&nbsp;</td>
				<td>${theme.sequence}</td>
				<shiro:hasPermission name="yb:theme:edit"><td>
    				<a href="${ctx}/yb/theme/form?id=${theme.id}">修改</a>
					<a href="${ctx}/yb/theme/delete?id=${theme.id}" onclick="return confirmx('确认要删除该主题吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
