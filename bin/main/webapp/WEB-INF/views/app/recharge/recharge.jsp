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
			function rechargeNow() {
				// 取用户选择支付的金额
				$('#payData span').each(function(i) {
					var cla = $(this).attr('class');
					if (cla.indexOf('active') != -1) {
						var num = $(this).text();
						num = num.replace('元', '');
						$('#payMoney').val(num);
					}
				});
				
				// 判断用户是否选择的金额
				var payMoney = $('#payMoney').val();
				if (isEmpty(payMoney) || '其他金额'==payMoney) {
					showMessage("请选择充值金额!");
					return false;
				}
				
				// 提交充值
				$('#rechargeForm').submit();
			}
			
			function showMessage(o){
				$("#message").html(o).show(100).delay(1500).hide(100); 
			}
		</script>
	</head>
	<body ng-app="myProduct">
		<div ng-controller='PayController'>
		<form id="rechargeForm" action="${ctx }/pay/confirm" method="post">
			<input type="hidden" name="id" value="${id }" />
			<input type="hidden" name="userId" value="${userId }" />
			<input type="hidden" name="phone" value="${phone }" />
			<input type="hidden" name="sourceType" value="${sourceType }" />
			<input type="hidden" name="callbackUri" value="${callbackUri }" />
			<input type="hidden" name="returnUri" value="${returnUri }" />
			<input type="hidden" id="payMoney" name="money" />
			<div class="backBar">账户充值</div>
			<div class="account">
				<span>当前账户：</span><span>${phone }</span>
			</div>
			<div class="recharge">
				<h3>选择充值面额</h3>
				<div id="payData" item-pay payData='payData' payVal='payVal'></div>
			</div> 
			<div class="payBtn bc_fff"><input value="立刻充值" type="button" onclick="javascript:rechargeNow();"/></div>
			<div class="paytips">
				<p>温馨提示:</p>
				<p>发票信息可以通过会员中心-资金管理进行索取</p>
			</div>
		</form>
		</div>
		
		<div id="message"></div>
	</body>
</html>
