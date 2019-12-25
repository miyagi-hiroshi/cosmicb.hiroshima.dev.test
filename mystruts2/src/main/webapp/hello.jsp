<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        
        <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
        <title>訪 問 者 受 付 画 面</title>

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <script src="/css/sample.js"></script>
        <style>
            @import url('/css/styles1.css');
        </style>
    </head>

    <body>
		<h1>ようこそ　コスミックビジネスへ</h1>

        <p id="demo1">です。</p>

        <s:form action="./reg" id="reg_form" name="uketsuke"  method="post">

			<s:textfield name="goodby.company" label="所属／会社" id="reg_company" />
			<s:textfield name="goodby.name" label="お名前" id="reg_name"/>
			<s:textfield name="goodby.num" type="number" label="人数" max="10" min="1" value="1" id="reg_num"/>
            <s:submit value="受　付" onclick="return check_uke_submit()"/>
            
        </s:form>

		会社名：<s:property value ="goodby.company" /><br>
		名前：<s:property value ="goodby.name" /><br>
        人数：<s:property value ="goodby.num" /><br>

        <s:debug />

    </body>


</html>