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
       员工添加
      <hr width="160">
    </caption>
   <tr>
     <td colspan="2">员工数据</td>
   </tr>
   <tr>
     <td>姓名</td>
     <td>
       <e:text name="aab102" required="true" autofocus="true"/> 
     </td>
   </tr>
   <tr>
     <td>员工 编号</td>
     <td>
        <e:text name="aab103"  required="true"/>
     </td>
   </tr>
   <tr>
     <td>生日</td>
     <td>
       <e:date name="aab104"  required="true"/>
     </td>
   </tr>
   <tr>
     <td>性别</td>
     <td>
       <!--
                         按照name属性自动分组,组内单选 
        -->
        <e:radio name="aab105" value="男:1,女:2,不限:3" defval="1"/>
     </td>
   </tr>
   <tr>
     <td>民族</td>
     <td>
       <e:select name="aab106" value="汉族:01,满族:02,蒙族:03,藏族:04,哈萨克:05" />
     </td>
   </tr>
   <tr>
     <td>籍贯</td>
     <td>
        <e:text name="aab107"  required="true"/> 
     </td>
   </tr>
   <tr>
     <td>手机号码</td>
     <td>
       <e:text name="aab108"  required="true"/> 
     </td>
   </tr>
   <tr>
     <td>电子邮件</td>
     <td>
       <e:email name="aab109" required="true"/>
     </td>
   </tr>
   <tr>
     <td>档案工资</td>
     <td>
       <e:number step="0.01" name="aab110" required="true"/>
     </td>
   </tr>
   <tr>
     <td>技术特长</td>
     <td>
        <e:select  name="aab111" value="oracle:01,MySQL:02,Java核心技术:03,架构设计:04,开源架构:05"
                   multiple="true" style="width:153px;height:120px"/>
     </td>
   </tr>
   <tr>
     <td>爱好</td>
     <td>
       <e:groupbox name="aab112" value="C:1,C++:2,C++--:3,C#:4,Java:5,Python:6"
                   newLineNumber="4"/>
     </td>
   </tr>
   <tr>
     <td>备注</td>
     <td>
       <e:textarea rows="5" cols="45" name="aab113"/>
     </td>
   </tr>
   <tr>
     <td colspan="2" align="center">
       <input type="submit" name="next" value="添加">
     </td>
   </tr>
</table>
</form>
</body>
</html>