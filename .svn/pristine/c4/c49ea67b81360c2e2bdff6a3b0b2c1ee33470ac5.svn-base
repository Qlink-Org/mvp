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
		<script src="${ctxStaticFront }/plugins/zepto.min.js"></script>
		<script src="${ctxStaticFront }/plugins/angular.min.js"></script>
		<script src="${ctxStaticFront }/plugins/angular-ui-router.min.js"></script>
		<script src="${ctxStatic }/common/utils.js"></script>
		<script src="${ctxStaticFront }/scripts/app.js"></script>
		<script src="${ctxStaticFront }/scripts/controller/appcontroller.js"></script>
		<script src="${ctxStaticFront }/scripts/directive/payDirective.js"></script>
		<script type="text/javascript">
			$(function() {
				$('#butSubmit').click(function() {
					$(this).attr('disabled', 'disabled');
					// 在线支付
					var id = $('#conumeRecordId').val();
					window.open('${pageContext.request.contextPath}/alipay/recharge/alipay.jsp?id=' + id, '_blank');
					interval = setInterval(function(){
						queryStatu(id);
					}, 5000);
				});
			});
			
			function queryStatu(id){
				var url='${pageContext.request.contextPath}/f/pay/record.json';
				$.post(url, {recordId:id}, function(data){
					if(data.code == 1){
						if(data.status == 'COMPLETED'){
							clearInterval(interval);
							showMessage("充值成功!");
						}
					}
				});
			} 
			
			function showMessage(o){
				$("#message").html(o).show(100).delay(1500).hide(100); 
			}
		</script>
	</head>
	<body ng-app="myProduct">
		<div ng-controller='PayController'>
			<input type="hidden" id="conumeRecordId" value="${id }" />
			<div class="backBar">充值确认</div>
			<div class="account">
				<span>充值账户：</span><span>${phone }</span>
			</div>
			<div class="account">
				<span>充值金额：</span><span>${money }</span>
			</div>
			<div class="recharge">
			<div class="payBtn bc_fff"><input value="确认充值" type="button" id="butSubmit"/></div>
			<div class="paytips">
				<p>温馨提示:</p>
				<p>发票信息可以通过会员中心-资金管理进行索取</p>
			</div>
		</div>
		
		<div id="message"></div>
	</body>
</html>
