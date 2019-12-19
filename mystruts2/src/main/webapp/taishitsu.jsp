<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>管 理 画 面</title>

        <style>
            @import url('/css/styles1.css');

        
        </style>
        <script type="text/javascript">
            function filterCheck() {

            }
        
        </script>

    </head>
    <body>
        <h1>管理画面</h1>
        <fieldset>
            <legend>日付フィルター</legend>

            <s:form action="filter" name="filter_form" method="post">
                <p><s:checkbox name="exitM.chk_filter" fieldValue="true" value="%{exitM.chk_filter}" label="入室中表示"/></p>

                <p>
                    日付指定：<s:textfield type="date" name="exitM.minDate" label="min" min="2019-01-01" theme="simple"/> ～ 
                    <s:textfield type="date" name="exitM.maxDate" label="max" min="2019-01-01" theme="simple" />
                    <s:submit value="フィルター実行" theme="simple"/>
                </p>

            </s:form>

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
                        <th width="240">訪問先</th>
                        <th width="240">退室処理</th>
                        
                        </tr>
                    </thead>
                </table>
            </dev>

            <div style="height:315px; overflow-y:scroll;">
                <table class="table" height="300">
                    <tbody>
                        <s:iterator value="dtoList" status="row">
                            <s:form action="taishitsu" method="post">
                                <tr>
                                    <td width="100"><s:textfield size="1" name="exitM.id" value="%{id}" readonly="true" theme="simple"/></td>
                                    <td width="170"><s:property value="in_date"/></td>
                                    <td width="300" class="twxt-nowrap"><s:property value="company"/></td>
                                    <td width="210" class="twxt-nowrap"><s:property value="name"/></td>
                                    <td width="160"><s:textfield size="5" name="diff" value="%{diff}" theme="simple" readonly="true" onChange="check()"/></td>
                                    <!-- TODO:経過時間の色を変える -->
                                    <script>
                                        function check(){
                                            var time1 = document.getElementById("taishitsu_diff").value;
                                            alert(time1);
                                            if (time1 == "---"){
                                                document.getElementById("taishitsu_diff").style.backgroundColor = 'silver';
                                            } 
                                        }
                                    
                                    </script>
                                    <!-- <td width="170"><s:property value="diff"/></td> -->
                                    <td width="230" class="twxt-nowrap"><s:textfield name="exitM.dest" value="%{dest}" theme="simple" var="diff"/></td>
                                    <td>
                                        <!-- TODO:入室中はボタン表示、退室済みはボタンを消去または押せない処理が必要 -->
                                        <!-- <s:if test="%{#diff != '---'}">
                                            <s:submit type="button" value="退室処理" theme="simple"/><br>
                                        </s:if> -->
                                    </td>
                                </tr>
                            </s:form>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        <!-- </s:form> -->

        <s:debug />
        <fieldset theme="simple">
            <legend>データの削除</legend>
            <s:submit value="データの削除" disabled="disabled"/>
        </fieldset>


    </body>


</html>