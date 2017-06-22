<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="com.util.Constants" %>

<%
	String authontication=(String)request.getAttribute("authontication");
%>

<html>
	<head>
	
		<title><%=Constants.getResourceStringValue("label.login","en_US")%></title>
		<style type="text/css" title="currentStyle">
			@import "css/style.css";
		</style>
		
	</head>
	<body class="center" style="width:20%;">
	
		 <div class="top70 padding10 width300 height150" style="border:3px solid red;">
		 
		 	<div class="bottom10 center" style="width:20%;"> <b>Login</b></div>
 
		    <div id="logindiv" style="">
		   
		    <%if(authontication!=null && authontication.equals("success")){ %>
		    <span class="">Success. </span>
		    <%}else if(authontication!=null && authontication!="" && !authontication.equals("success")){%>
		    <span class="">User Id or password is wrong</span>
		   
		    <%} %>
			    <form:form action="logon.do" method="POST" >
				    <table>
				   
				    	<tr>
				    		<td><form:label path="emailId">User Id:</form:label> </td>
				    		<td><form:input path="emailId" class=""/></td>
				    	</tr>
				    	<tr><td></td><td></td></tr>
				    	<tr>
				    		<td><form:label path="password">Password:</form:label></td>
				    		<td><form:password path="password" class=""/></td>
				    	</tr>
				    </table>
				    <table class="top15">
				   	 	<tr>
				   	 		<td width="110"></td>
				   	 		<td><input type="submit" value="Login" class=""/></td>
				   	 				   	 	
				   	 	</tr>
				    </table>
				 </form:form>
		    </div>
		 </div>
	</body>
</html>