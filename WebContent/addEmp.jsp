<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl"  prefix="e"%>
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
  td{
      height:30px;
  }
</style>
</head>
<body>
${msg}
<br>
<br>
<form action="<%=request.getContextPath()%>/addEmp.html" method="post">
<table  border="1" align="center" width="45%">
    <caption>
       Ա�����
      <hr width="160">
    </caption>
   <tr>
     <td colspan="2">Ա������</td>
   </tr>
   <tr>
     <td>����</td>
     <td>
       <e:text name="aab102" required="true" autofocus="true"/> 
     </td>
   </tr>
   <tr>
     <td>Ա�� ���</td>
     <td>
        <e:text name="aab103"  required="true"/>
     </td>
   </tr>
   <tr>
     <td>����</td>
     <td>
       <e:date name="aab104"  required="true"/>
     </td>
   </tr>
   <tr>
     <td>�Ա�</td>
     <td>
       <!--
                         ����name�����Զ�����,���ڵ�ѡ 
        -->
        <e:radio name="aab105" value="��:1,Ů:2,����:3" defval="1"/>
     </td>
   </tr>
   <tr>
     <td>����</td>
     <td>
       <e:select name="aab106" value="����:01,����:02,����:03,����:04,������:05" />
     </td>
   </tr>
   <tr>
     <td>����</td>
     <td>
        <e:text name="aab107"  required="true"/> 
     </td>
   </tr>
   <tr>
     <td>�ֻ�����</td>
     <td>
       <e:text name="aab108"  required="true"/> 
     </td>
   </tr>
   <tr>
     <td>�����ʼ�</td>
     <td>
       <e:email name="aab109" required="true"/>
     </td>
   </tr>
   <tr>
     <td>��������</td>
     <td>
       <e:number step="0.01" name="aab110" required="true"/>
     </td>
   </tr>
   <tr>
     <td>�����س�</td>
     <td>
        <e:select  name="aab111" value="oracle:01,MySQL:02,Java���ļ���:03,�ܹ����:04,��Դ�ܹ�:05"
                   multiple="true" style="width:153px;height:120px"/>
     </td>
   </tr>
   <tr>
     <td>����</td>
     <td>
       <e:groupbox name="aab112" value="C:1,C++:2,C++--:3,C#:4,Java:5,Python:6"
                   newLineNumber="4"/>
     </td>
   </tr>
   <tr>
     <td>��ע</td>
     <td>
       <e:textarea rows="5" cols="45" name="aab113"/>
     </td>
   </tr>
   <tr>
     <td colspan="2" align="center">
       <input type="submit" name="next" value="���">
     </td>
   </tr>
</table>
</form>
</body>
</html>