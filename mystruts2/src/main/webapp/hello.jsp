<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>訪 問 者 受 付 画 面</title>

    </head>
    <body>
		<h1>ようこそ　コスミックビジネスへ</h1>

        現在日時は、<s:property value="goodby.sysDate"/> です。 <br>


        <s:form action="reg"  method="post">
			<s:textfield name="goodby.company" label="所属／会社"/>
			<s:textfield name="goodby.name" label="お名前"/>
			<s:textfield name="goodby.num" type="number" label="人数" max="10" min="1" value="1"/>
			<s:submit value="受 付"/>

                <!-- <div class="textbox">
                        <label class="ef">
                                所属：<br><input type="search" name="company" property="company" placeholder="所属／会社" autocomplete="off"> 
                                名前：<br><input type="search" name="name" property="name" placeholder="お名前(代表者)" autocomplete="off">  
                        </label>				
                </div>

                <div class="numbox">
                    <label for="num">人数：</label><br>
                    <input type="number" name="num" property="num" min="1" max="5" value="1" >                 
                </div>
                <s:submit name="reg" value="受 付"/> -->

		</s:form>
		
		会社名：<s:property value ="goodby.company" /><br>
		名前：<s:property value ="goodby.name" /><br>
        人数：<s:property value ="goodby.num" /><br>

        <!-- ステータス：<br>
        <s:iterator value="goodby.dbData" var="target" status="idx">
            <s:property value="target"/> <br>
        </s:iterator> -->


        <s:debug />

    </body>


</html>