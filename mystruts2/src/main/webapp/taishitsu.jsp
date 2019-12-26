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

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <script src="/css/sample.js"></script>


    </head>
    <body>
        <h1>管理画面</h1>
        <!-- <input type="button" value="weather" id="tenki" onclick="test()"/> -->

        <fieldset>
            <legend>日付フィルター</legend>

            <s:form action="./filter" name="filter_form" method="post">
                <p><s:checkbox name="exitM.chk_filter" fieldValue="true" value="%{exitM.chk_filter}" label="入室中表示"/></p>

                <p>
                    日付指定：<s:textfield type="date" name="exitM.minDate" label="min" min="2019-01-01" id="fromDate" theme="simple"/> ～ 
                    <s:textfield type="date" name="exitM.maxDate" label="max" min="2019-01-01" id="toDate" theme="simple" />
                    <s:submit value="フィルター実行" theme="simple"/>
                    
                </p>

            </s:form>

        </fieldset>       
        
        
        <div style="height:30px; width:1500px;">
            <table class="table" height="50">
                <thead class="thead-dark">
                    <tr>
                    <!-- <th width="0">I D</th> -->
                    <th width="200">入室日時</th>
                    <th width="220">会社名</th>
                    <th width="310">訪問者名</th>
                    <th width="160">経過時間</th>
                    <th width="240">訪問先</th>
                    <th width="230">退室処理</th>
                    
                    </tr>
                </thead>
            </table>
        </dev>

        <div style="height:315px; overflow-y:scroll;">
            <div id="ajax">
                
                    
                <s:iterator value="dtoList" status="row">
                    <s:form action="./taishitsu" method="post" theme="simple">
                        <table class="table" height="50">
                            <tr>
                                <td width="30">
                                    <s:hidden size="1" name="exitM.id" value="%{id}" readonly="true" theme="simple"/>
                                </td>
                                <td width="150">
                                    <s:property value="in_date"/>
                                </td>
                                <td width="350" class="twxt-wrap">
                                    <s:property value="company"/>
                                </td>
                                <td width="250" class="twxt-nowrap">
                                    <s:property value="name"/>
                                </td>
                                <td width="150">
                                    <s:textfield size="5" name="diff" value="%{diff}" id="diff%{#row.index}" readonly="true" theme="simple"/>
                                </td>
                                <td width="270" class="twxt-nowrap">
                                    <s:textfield name="exitM.dest" id="dest%{#row.index}" value="%{dest}" theme="simple"/>
                                </td>
                                <td>
                                    <s:submit name="btn" value="退室処理" theme="simple" onclick="return check_dest_submit()"/>

                                </td>
                                <!-- 入室中はボタン表示、退室済みはボタンを消去または押せない処理 -->
                                <script type="text/javascript" charset="UTF-8">
                                    var obj_id = document.getElementsByName("exitM.id");
                                    var obj_diff = document.getElementsByName("diff");
                                    var obj_dest = document.getElementsByName("exitM.dest");
                                    var obj_button = document.getElementsByName("btn");

                                    //$(obj_id).css("text-align" , "center");
                                    for (var i = 0; i < obj_diff.length; i++) {
                                        obj_diff[i].style.textAlign = "center";
                                        obj_id[i].style.textAlign = "center";

                                        if (obj_diff[i].value == "---") {
                                            obj_diff[i].style.backgroundColor = "silver";
                                            obj_dest[i].style.backgroundColor = "silver";
                                            obj_diff[i].disabled = true;
                                            obj_dest[i].disabled = true;
                                            obj_button[i].disabled = true;
                                        } else {
                                            //経過時間が表示されている場合、経過時間の色を変える
                                            var str_time = obj_diff[i].value.split(":");
                                            var hour = parseInt(str_time[0], 10);

                                            if (hour > 10) {
                                                obj_diff[i].style.color = "white";
                                                obj_diff[i].style.backgroundColor = "purple";

                                            } else if (hour > 5) {
                                                obj_diff[i].style.color = "white";
                                                obj_diff[i].style.backgroundColor = "red";

                                            } else if (hour >= 3) {
                                                obj_diff[i].style.color = "black";
                                                obj_diff[i].style.backgroundColor = "yellow";
                                            }
                                        }
                                    }

                                </script>
                            </tr>
                        </table>
                    </s:form>
                </s:iterator>
                   
                
            </div>
        </div>

        <fieldset theme="simple">
            <legend>データの削除</legend>

            <s:form action="./del" method="post">
                <s:submit value="データの削除"/> 
            </s:form>

        </fieldset>

        <s:debug />
    </body>


</html>