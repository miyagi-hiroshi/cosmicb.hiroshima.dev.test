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
    
    var name = document.getElementById('reg_name').value                    
    if (name == "") {
        toastr["error"]("お名前の入力は必須です。", "エラー");
        return false;
    } else {
        toastr["success"]("受付致しました。しばらくお待ちください。", "SUCCESS");
        return true;
    }
}


function check_dest_submit(){
    //押されたボタンが含まれるフォーム名を取得
	$('.btn').on('click', function(event) {
        
        console.log(event.target.form);
	
    });


    var obj = event.target.form;
    var formName = obj.name;
    var obj_dest = document.forms[formName].elements[2].value;

    if (obj_dest == "") {
        toastr["error"]("訪問先が入力されていません。", "エラー");
        return false;
    } else {
        toastr["success"]("退室処理を行います。", "SUCCESS");
        return true;
    }
    

}