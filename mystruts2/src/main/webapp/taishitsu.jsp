<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>管 理 画 面</title>

    </head>
    <body>
        <s:action name="exit">
            <h1>管理画面</h1>

            ステータス：<br>
            ID      会社名      名前        人数        入室時間        経過時間<br>
            <s:iterator value="dtoList">
                <s:property value="id"/>
                <s:property value="company"/>
                <s:property value="name"/>
                <s:property value="num"/>
                <s:property value="in_date"/>
                <s:property value="diff"/>
                <s:submit value="退室処理" theme="simple" /><br>
            </s:iterator>

        </s:action>
        <s:debug />

    </body>


</html>