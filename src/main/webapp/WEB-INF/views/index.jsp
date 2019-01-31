<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="/css/main.css" />
</head>
<body>

	<h1>ASX JSP APP</h1>
	<h2>Welcome, Enter The Trading Calendar</h2>

	<form:form method="POST" action="/addTradingDate" modelAttribute="tradingCalendarDto">
		<table>
			<tr>
				<td><form:label path="">Record Date</form:label></td>
				<td><form:input path="recordDate" /></td>
				<td><form:errors path="recordDate" cssClass="error" element="div" /></td>
			</tr>                 
			<tr>
				<td><form:label path="">Payment Date</form:label></td>
				<td><form:input path="paymentDate" /></td>
				<td><form:errors path="paymentDate" cssClass="error" element="div" /></td>
            </tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>