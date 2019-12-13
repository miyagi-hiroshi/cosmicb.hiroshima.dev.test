package net.supportdoc.helloworld.model;

public class HelloModel {
    private String message; //プロパティ
    
    public HelloModel() {
        //message = "Strutsよこんにちは";
    }

    public String getMessage() {    //データ読み出しのためのメソッド
        System.out.println("【HelloModel】 "+ message + "をゲットしました。");
        return message;
    }
}