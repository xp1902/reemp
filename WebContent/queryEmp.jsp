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
	               Ա������
	    <hr width="160">
	  </caption>
	  <tr>
	    <td colspan="4">��ѯ����</td>
	  </tr>
	  <tr>
	    <td>����</td>
	    <td>
	      <e:text name="qaab102"/>
	    </td>
	    <td>Ա�����</td>
	    <td>
	      <e:text name="qaab103"/>
	    </td>
	  </tr>
	  <tr>
	    <td>�Ա�</td>
	    <td>
	      <e:radio name="qaab105" value="��:1,Ů:2,��ȷ��:3,���޶�:''" defval=""/>
	    </td>
	    <td>����</td>
	    <td>
	      <e:select name="qaab106" value="����:01,����:02,����:03,����:04,������:05" header="true" />
	    </td>
	  </tr>
	  <tr>
	    <td>����[B]</td>
	    <td>
	      <e:date name="baab104"/>
	    </td>
	    <td>����[E]</td>
	    <td>
	      <e:date name="eaab104"/>
	    </td>
	  </tr>
</table>
<table>
	  <tr>
	    <td></td>
	    <td>���</td>
	    <td>����</td>
	    <td>���</td>
	    <td>����</td>
	    <td>�Ա�</td>
	    <td>����</td>
	    <td>�ֻ�����</td>
	    <td>�����ʼ�</td>
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
				<input type="submit" name="next" value="��ѯ"/>
				<input type="submit" name="next" value="���"
					formaction="<%=request.getContextPath()%>/addEmp.jsp"/>
				<input type="submit" name="next" value="ɾ��" disabled="disabled" id="del" onclick="checkClick"
					formaction="<%= path %>"/deleteEmp.html/>

</table>
</form>


</body>
</html>