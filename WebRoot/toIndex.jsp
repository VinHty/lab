<%@ taglib uri="/struts-tags" prefix="s" %>>
<script> 
  top.window.location.href="index.jsp"; 
</script>
<input type="hidden" name="actionerror" value="<s:actionerror/>" />
<!-- 使用top.window.location.href="/index.jsp"跳出frame框架，避免登入界面显示在frame框架中 -->