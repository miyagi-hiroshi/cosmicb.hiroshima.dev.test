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
        <fieldset>
            <p><s:checkbox name="inRoom" fieldValue="true" checked="true" label="入室中"/></p>
            <p>
                日付指定 <s:textfield type="date" label="min" min="2019-01-01" />～
                <s:textfield type="date" label="max" min="2019-01-01" /></p>
            <legend>日付フィルター</legend>
        </fieldset>
        
        
        
        <!-- <s:form action="taishitsu" method="post"> -->

            <div style="height:30px; width:1500px;">
                <table class="table" height="50">
                    <thead class="thead-dark">
                        <tr>
                        <th width="0">I D</th>
                        <th width="200">入室日時</th>
                        <th width="220">会社名</th>
                        <th width="310">訪問者名</th>
                        <th width="90">経過時間</th>
                        <th width="240">退室処理</th>
                        </tr>
                    </thead>
                </table>
            </dev>

            <div style="height:315px; overflow-y:scroll;">
                <table class="table" height="300" width="1500">
                    <tbody>
                        <s:iterator value="dtoList" status="row">
                            <s:form action="taishitsu" method="post">
                                <tr>
                                    <td width="100"><s:textfield size="1" name="exitM.idValue" value="%{id}" theme="simple" disabled="true"/></td>
                                    <td width="170"><s:property value="in_date"/></td>
                                    <td width="300" class="twxt-nowrap"><s:property value="company"/></td>
                                    <td width="200" class="twxt-nowrap"><s:property value="name"/></td>
                                    <td width="140"><s:property value="diff"/></td>
                                    <td width="40"></td><s:hidden value="test" name="hidden" theme="simple"/>
                                    <td>
                                        <s:submit value="退室処理" theme="simple"/><br>
                                    </td>
                                </tr>
                            </s:form>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        <!-- </s:form> -->

        
        <s:debug />

    </body>


</html>