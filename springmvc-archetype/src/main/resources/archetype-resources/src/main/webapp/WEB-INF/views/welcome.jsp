#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
  <meta charset="utf-8">
  <title><fmt:message key="welcome.title"/></title>
</head>  
<body>
<div class="container">  
	<h1>
		<fmt:message key="welcome.title"/>
	</h1>
  
  <a href='<c:url value="myController/doSomething"/>'>Show today date</a>
	<hr>	
	<ul>
		<li><a href="?locale=en_gb">gb</a> | <a href="?locale=de_de">de</a></li>
	</ul>  
</div>
</body>
</html>