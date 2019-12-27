﻿$(function(){
    setInterval(function(){
        var now = new Date();
        var y= now.getFullYear();
        var m = now.getMonth() + 1;
        var d = now.getDate();
        var w = now .getDay();
        var h = now.getHours();
        var hh =  ('0' + h).slice(-2);
        var mi = now.getMinutes();
        var mmi = ('0' + mi).slice(-2);
        var s = now.getSeconds();
        var ss = ('0' + s).slice(-2);

        $('#demo1').text("現在、" + y + "/" + m + "/" + d + " " + hh + ":" + mmi + ":" + ss + "です。");
    }, 1000);

})

function check_uke_submit(){
    
    var name = document.getElementById('reg_name').value;
    var company = document.getElementById('reg_company').value;

    if (name == "") {
        toastr["error"]("お名前の入力は必須です。", "エラー");
        return false;
    } else {
        toastr["success"]("受付致しました。\n" + name + "さん、しばらくお待ちください。", "SUCCESS");
        return true;
    }
}


function check_dest_submit(t){

    var frmId = t.form.id;

    var dest = $(t).parent().parent().find('[name=exitM\\.dest]').val();
    //該当フォームのHTML
    var targetHtml = $("#" + frmId).html();


    if (dest == "") {
        toastr["error"]("訪問先が入力されていません。", "エラー");
        return false;
    } else {

        var formData = $("#" + frmId).serialize();
        //// HTMLでの送信をキャンセル
        event.preventDefault();

        //該当フォームのHTMLを消去する
        $("#" + frmId).slideUp("slow", function() {
            $("#" + frmId).html("");
        })


       //TODO:AJAX GETで投げてJSONのレスポンスを受け取るようにする
       $.ajax({
        url : "taishitsu",
        type : "GET",
        data : formData,
        dataType : "json",
        contentType : "application/json;charset=utf-8",
        timeout : 3000
        }).done(function(data, textStatus, jqXHR){

            var len = data.length;
            var flag = true;

            console.log("done!");
            console.log(data);

            //非同期通信リクエスト成功した時のコード
            //該当行の情報(HTML)を消去する
            //非同期通信のレスポンス(JSON)を見て、DB登録されていればそのまま、
            //DBでエラーが発生していれば消去したHTMLを復元させる。
            if (data.MySQLcon == "ok" && data.MySQLupdate == "ok") {
                //JSON：DB登録成功したときのコード↓
                toastr["success"]("退室処理を行いました。", "SUCCESS");
                return true;

            } else {
                //JSON：DB登録失敗したときのコード↓
                //データを復元する
                $("#" + frmId).slideUp("slow", function() {
                    $("#" + frmId).html(targetHtml);
                })
                toastr["error"]("データベース更新時にエラーが発生しました。", "エラー");
                location.reload(true);
                return false;
            }

            // for (var i=0; i< len; i++){
            //     if (data[i].MySQLconn == "ok" && data[i].MySQLupdate == "ok") {
            //         flag = true;

            //     } else{
            //     //JSON：DB登録失敗したときのコード↓
            //     flag = false;

            //     }
            // }

            // if (flag){
            //     //JSON：DB登録成功したときのコード↓
            //     toastr["success"]("退室処理を行いました。", "SUCCESS");
            //     return true;

            // } else {
            //     //データを復元する
            //     $("#" + frmId).slideUp("slow", function() {
            //         $("#" + frmId).html(targetHtml);
            //     })
            //     toastr["error"]("データベース更新時にエラーが発生しました。", "エラー");
            //     location.reload(true);
            //     return false;
            // }


            
        }).fail(function(jqXHR, textStatus, errorThrown){

            //非同期通信リクエスト失敗した時のコード
            //データを復元する
            $("#" + frmId).slideUp("slow", function() {
                $("#" + frmId).html(targetHtml);
            })
            toastr["error"]("AJAXエラー", "エラー");
            console.log(jqXHR);
            console.log("error!");
            location.reload(true);
        })
    }
}

