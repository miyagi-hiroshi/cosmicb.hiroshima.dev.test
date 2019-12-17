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


        <s:form action="reg" id="reg_form" name="uketsuke"  metyhod="post">
			<s:textfield name="goodby.company" label="所属／会社" />
			<s:textfield name="goodby.name" label="お名前" id="reg_name"/>
			<s:textfield name="goodby.num" type="number" label="人数" max="10" min="1" value="1"/>
            <s:submit value="受 付" onClick="check_submit()"/>
            

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
        
        <!-- //TODO：おまけ機能・・・入力チェック -->
        <script>
            function check_submit(){
                var company = document.getElementById('reg_form_goodby_company').value
                var name = document.getElementById('reg_name').value
                alert('会社名 ' + company + ' 名前　' + name )

                if (name == "") {
                    // alert("名前が入力されていません");
                    try {
                        throw new Error('会社名 ' + company + ' 名前　' + name );
                    } catch (e) {

                    }

                } else {
                    var r = confirm("送信してもよろしいですか？");
                    if (r) {document.uketsuke.submit();}
                }

            }

       
        </script>
		
		会社名：<s:property value ="goodby.company" /><br>
		名前：<s:property value ="goodby.name" /><br>
        人数：<s:property value ="goodby.num" /><br>

        <s:debug />

    </body>


</html>