<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getFrontPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="ctxStaticFront" value="${ctxStatic}/modules/pay/front"/>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
		<title></title>
		<link href="${ctxStaticFront }/css/global.css" type="text/css" rel="stylesheet">
		<link href="${ctxStaticFront }/css/style.css" type="text/css" rel="stylesheet">
		<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
	</head>
	<body ng-app="myProduct">
		<div ng-controller='PayController'>
			<input type="hidden" id="conumeRecordId" value="${id }" />
			<div class="backBar">充值结果</div>
			<div class="paytips">
				<c:if test="${verifyResult }">
					<p>充值成功</p>
				</c:if>
				<c:if test="${!verifyResult }">
					<p>充值失败</p>
				</c:if>
			</div>
		</div>
		
		<div id="message"></div>
	</body>
</html>
