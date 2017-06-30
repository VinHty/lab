<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<%
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
String date= format.format(new Date());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工编辑</title>
<script type="text/javascript" src="<%=basePath%>/laydate/laydate.js"></script>
<script>
;!function(){
laydate.skin('molv');
}();
</script>
</head>
<body>
	<table border="0" width="600px">
		<tr>
			<td align="center" style="font-size:24px; color:#666"> 员工修改</td>
		</tr>
		<tr>
			<td align="right" > 
				<a href="javascript:document.getElementById('saveForm').submit()">保存</a> &nbsp;&nbsp;
				<a href="javascript:history.go(-1)">退回</a>
			</td>
		</tr>
	</table><br/>
	<hr>
<br/>
<s:form action="employee_update" method="post" namespace="/" id="saveForm" theme="simple">
<s:hidden name="eid" value="%{model.eid}" />
	<table border='0' cellpadding="0"  cellspacing="10">
		<tr>
			<td>姓名：</td>
			<td><s:textfield name="ename" value="%{model.ename}" /></td>
			<td>性别：</td>
			<td><s:radio name="sex" list="{'男','女'}" value="%{model.sex}" /></td>
		</tr>
		<tr>
			<td>用户名：</td>
			<td><s:textfield name="username" value="%{model.username}" /></td>
			<td>密码：</td>
			<td><s:password showPassword="true" name="password" value="%{model.password}"/></td>
		</tr>
		<tr>
			<td>出生日期：</td>
			<td><input type="text" name="birthday" value="<s:date name="model.birthday" format="yyyy-MM-dd"/>" class="laydate-icon" onclick="laydate()" style="width:80%;" /></td>
			<td>入职时间：</td>
			<td><input type="text" name="joinDate" value="<s:date name="model.joinDate" format="yyyy-MM-dd" />" class="laydate-icon" onclick="laydate()" style="width:80%;" /></td>
		</tr>
		<tr>
			<td>所属部门：</td>
			<td><s:select list="list" name="department.did" value="%{model.department.did}" listKey="did" listValue="dname" headerKey="" headerValue="---请选择---"></s:select></td>
			<td>编号：</td>
			<td><s:textfield name="eno" value="%{model.eno}" /></td>
		</tr>
	</table>
</s:form>
</body>
</html>