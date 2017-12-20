<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>封面管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var currenttype;
		$(document).ready(function() {
			$(document).ready(function() {
				var clickedtype = $("#type").val();
				if(clickedtype==1){
					document.getElementById("rank").style.display = "";
				}
			});
			
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var locationVal = $("#location").val();
					if (!!locationVal){
						locationVal = decodeURI(locationVal);
						$("#location").val(locationVal);
					}
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
			
		});
		

	</script>
	<style type="text/css">
	    input{
            width: auto;
        }
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/yb/cover/">封面列表</a></li>
		<li class="active"><a href="${ctx}/yb/cover/form?id=${cover.id}">封面<shiro:hasPermission name="yb:cover:edit">${not empty cover.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="yb:cover:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="cover" action="${ctx}/yb/cover/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:select path="type" class="input-medium required">
		        	<form:option value="" label="请选择"/>
		        	<form:options items="${fns:getDictList('yb_cover_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
		        </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文字样式:</label>
			<div class="controls">
				<form:select path="wordStyle" class="input-medium required">
		        	<form:option value="" label="请选择"/>
		        	<form:options items="${fns:getDictList('yb_word_style')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
		        </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片:</label>
			<div class="controls">
				<form:hidden path="imgId" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<tags:ckfinder input="imgId" type="images" uploadPath="/yb/cover" selectMultiple="false" />
				<span class="help-inline">图片文件，jpg、png等支持的图片格式</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="sequence" htmlEscape="false" maxlength="163" class="required digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="yb:cover:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
