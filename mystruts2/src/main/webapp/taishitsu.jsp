<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>管 理 画 面</title>

    </head>
    <body>
        <h1>管理画面</h1>

        ステータス：<br>
        <s:iterator value="goodby.dbData" var="target" status="idx">
            <s:property value="target"/> <br>
        </s:iterator>

    </body>


</html>