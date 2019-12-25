$(function(){
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


function check_dest_submit(){
    //押されたボタンが含まれるフォーム名を取得
	$('.btn').on('click', function(event) {
        

    });


    var obj = event.target.form;
    var formName = obj.name;
    var formId = obj.id;
    var dest = document.forms[formName].elements[2].value;

    if (dest == "") {
        toastr["error"]("訪問先が入力されていません。", "エラー");
        return false;
    } else {
        toastr["success"]("退室処理を行います。", "SUCCESS");

       //TODO:AJAX
       $.ajax({
        url : "http://localhost:8080/mystruts2/exit",
        type : "POST",
        data : {
            // "id" : document.forms[formName].elements[0].value,
            // "dest" : document.forms[formName].elements[2].value
            "id" : $('#' + formName + '[name=exitM.id]').val(),
            "dest" :  $('#' + formName + '[name=exitM.dest]').val()
        },
        timeout : 3000,
        }).done(function(result) {
            console.log(result);
            console.log("done!");
            
        }).fail(function(result){
            console.log(result);
            console.log("error!");
        })
    }
}


function test() {

    //TODO:AJAX
    $.ajax({
        url : "http://weather.livedoor.com/forecast/webservice/json/v1",
        type : "GET",
        dataType : "json",
        data : {
            city : "400040"
        },
        timeout : 1000
    }).done(function(responseData) {
        console.log("done");
        
    }).fail(function(responseData){
        console.log(responseData);

    }).always(function(responseData){
        console.log("Always!");
    })

}
