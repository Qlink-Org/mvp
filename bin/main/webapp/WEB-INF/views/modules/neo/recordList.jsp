<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>record管理</title>
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
		<li class="active"><a href="${ctx}/neo/record/">RECORD列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="neoRecordCondition" action="${ctx}/neo/record/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>recordID ：</label><form:input path="recordId" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
		<label>创建时间：</label> 
		<input id="startTime" name="startTime"
				type="text" readonly="readonly" maxlength="20"
				class="input-small Wdate required" value="${neoRecordCondition.startTime}"
				onclick="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'});" />&nbsp;到
		<input id="endTime" name="endTime"
				type="text" readonly="readonly" maxlength="20"
				class="input-small Wdate required" value="${neoRecordCondition.endTime}"
				onclick="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'%y-%M-%d'});" />	
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>recordId</th>
				<th>addressFrom</th>
				<th>formP2pId</th>
				<th>addressTo</th>
				<th>toP2pId</th>
				<th>qlc</th>
				<th>创建时间</th>
		<tbody>
		<c:forEach items="${page.list}" var="record">
			<tr>
				<td>${record.recordKey}</td>
				<td>${record.addressFrom}</td>
				<td>${record.formP2pId}</td>
				<td>${record.addressTo}</td>
				<td>${record.toP2pId}</td>
				<td>${record.qlc}</td>
				<td><fmt:formatDate value="${record.createDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
