<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>削除確認画面</title>

    </head>
    <body>
        <h1>削除確認画面</h1>
        <p>削除する場合は、「削除」ボタンを押下してください。</p>
        <s:submit type="button" value="削除"/>

        <s:iterator value="dtoList" status="row">
            ●<s:property value="id"/> , <s:property value="in_date"/> ～ <s:property value="out_date"/> , <s:property value="company"/> , 
            <s:property value="name"/> , <s:property value="dest"/>
        </s:iterator>

    </body>
</html>