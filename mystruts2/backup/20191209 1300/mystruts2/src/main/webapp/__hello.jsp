<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>My Struts2 hello.jsp page</title>
    </head>
    <body>
        <h2>これはStruts2</h2>
        <s:property value="hello.message"/><br>
        <s:property value="goodby.report"/><br>
    </body>
</html>