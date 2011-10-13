<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
  <meta charset="utf-8">
  <title>Response</title>
</head> 
<body>
<div class="container">  
  Today: <strong>${requestScope.today}</strong><br />
  I am: <span id="me">${requestScope.me}</span> 
</div>
</body>
</html>