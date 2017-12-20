<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ssid管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/neo/ssid/">SSID列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="neoSsidCondition" action="${ctx}/neo/ssid/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>SSID ：</label><form:input path="ssid" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
		<label>创建时间：</label> 
		<input id="startTime" name="startTime"
				type="text" readonly="readonly" maxlength="20"
				class="input-small Wdate required" value="${neoSsidCondition.startTime}"
				onclick="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'});" />&nbsp;到
		<input id="endTime" name="endTime"
				type="text" readonly="readonly" maxlength="20"
				class="input-small Wdate required" value="${neoSsidCondition.endTime}"
				onclick="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'%y-%M-%d'});" />	
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ssid</th>
				<th>mac</th>
				<th>p2pId</th>
				<th>创建时间</th>
		<tbody>
		<c:forEach items="${page.list}" var="ssid">
			<tr>
				<td>${ssid.ssid}</td>
				<td>${ssid.mac}</td>
				<td>${ssid.p2pId}</td>
				<td><fmt:formatDate value="${ssid.createDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
