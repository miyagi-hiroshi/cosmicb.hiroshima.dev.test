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
        <input type="button" value="入室中" style="border:outset 2px;" onmousedown="this.style.border='inset 2px'";>
        <s:checkbox name="checkMe" fieldValue="true" label="入室中"/>
        
        <s:form action="taishitsu" method="post">

            <div style="height:30px; width:1500px;">
                <table class="table" height="50">
                <thead class="thead-dark">
                    <tr>
                    <th width="90">入室日時</th>
                    <th width="220">会社名</th>
                    <th width="170">訪問者名</th>
                    <th width="200">経過時間</th>
                    <th width="100">退室処理</th>
                    </tr>
                </thead>
                </table>
            </dev>

            <div style="height:500px; width:1500px; overflow-y:scroll;">
                <table class="table" height="300" width="1500">
                    <tbody>
                        <s:iterator value="dtoList" status="row">
                            <tr>                        
                            <td width="120"><s:property value="in_date"/></td>
                            <td width="270" class="twxt-nowrap"><s:property value="company"/></td>
                            <td width="170" class="twxt-nowrap"><s:property value="name"/></td>
                            <td width="140"><s:property value="diff"/></td>
                            <td>
                                <s:submit value="退室処理" name="button[%{#row.index}]" theme="simple"/><br>
                            </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        </s:form>
        <s:debug />

    </body>


</html>