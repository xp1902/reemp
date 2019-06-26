<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl"  prefix="e"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<%String path=request.getContextPath(); %>	
<title>Insert title here</title>
<style>
tr{
height:25px;
}
</style>
<script type="text/javascript">
	var count = 0;
	function checkClick(vstatus){
		var delb = document.getElementById("del");
		vstatus?count++:count--;
		delb.diseabled(count == 0);
	}

</script>
</head>
<body>
${msg }
<br>
<br>
<form action = "<%=path%>/queryEmp.html" method = "post">

<table>
<caption>
	               员工管理
	    <hr width="160">
	  </caption>
	  <tr>
	    <td colspan="4">查询条件</td>
	  </tr>
	  <tr>
	    <td>姓名</td>
	    <td>
	      <e:text name="qaab102"/>
	    </td>
	    <td>员工编号</td>
	    <td>
	      <e:text name="qaab103"/>
	    </td>
	  </tr>
	  <tr>
	    <td>性别</td>
	    <td>
	      <e:radio name="qaab105" value="男:1,女:2,不确定:3,不限定:''" defval=""/>
	    </td>
	    <td>民族</td>
	    <td>
	      <e:select name="qaab106" value="汉族:01,满族:02,蒙族:03,藏族:04,哈萨克:05" header="true" />
	    </td>
	  </tr>
	  <tr>
	    <td>生日[B]</td>
	    <td>
	      <e:date name="baab104"/>
	    </td>
	    <td>生日[E]</td>
	    <td>
	      <e:date name="eaab104"/>
	    </td>
	  </tr>
</table>
<table>
	  <tr>
	    <td></td>
	    <td>序号</td>
	    <td>姓名</td>
	    <td>编号</td>
	    <td>生日</td>
	    <td>性别</td>
	    <td>民族</td>
	    <td>手机号码</td>
	    <td>电子邮件</td>
	    <td></td>
	  </tr>
	<c:choose>
		<c:when test="${rows != null }">
			<c:forEach items="${rows}" var="ins" varStatus="status" >
				<tr>
					<td>
				      <input type="checkbox" name="idlist" value="${ins.aab101 }"
				             onclick="onSelect(this.checked)" >
					
					</td>
					<td>${ status.count }</td>
					<td>${ ins.aab102 }</td>
					<td>${ ins.aab103 }</td>
					<td>${ ins.aab104 }</td>
					<td>${ ins.aab105 }</td>
					<td>${ ins.aab106 }</td>
					<td>${ ins.aab107 }</td>
					<td>${ ins.aab108 }</td>
					<td>${ ins.aab109 }</td>
				</tr>
			</c:forEach>
			<c:forEach begin="${fn:length(rows) +1 }" step="1" end="15">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:forEach>
		
		</c:when>
		<c:otherwise>
			<c:forEach begin="1" step="1" end="15" >
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<table>
		<tr aligin="center">
			<td align="center">
				<input type="submit" name="next" value="查询"/>
				<input type="submit" name="next" value="添加"
					formaction="<%=request.getContextPath()%>/addEmp.jsp"/>
				<input type="submit" name="next" value="删除" disabled="disabled" id="del" onclick="checkClick"
					formaction="<%= path %>"/deleteEmp.html/>

</table>
</form>


</body>
</html>