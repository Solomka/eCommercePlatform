<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello Spring SSecurity MVC</title>
    <%@ page isELIgnored="false" %>
</head>
<body>
<h2>
    <div>
        Check credit card form
    </div>
</h2>

<div>Errors: ${errors}.</div>

<table border="0" width="90%">
    <form:form action="order/create" method="post" commandName="order">
    <tr>
        <td align="left" width="20%">Number:</td>
        <td align="left" width="40%"><form:input path="customer" size="30"/></td>
        <td align="left"><form:errors path="customer" cssClass="error"/></td>
    </tr>
    <!--<tr>
        <td>cvv2:</td>
        <td><form:input path="cvv" size="30"/></td>
        <td><form:errors path="cvv" cssClass="error"/></td>
    </tr>
    <td>date:</td>
    <td><form:input path="expirationDate" size="30"/></td>
    <td><form:errors path="expirationDate" type="date" cssClass="error"/></td>-->
    <tr>
        <td></td>
        <td align="center"><input type="submit" value="Check"/></td>
        <td></td>
    </tr>
    </form:form>

</body>
</html>