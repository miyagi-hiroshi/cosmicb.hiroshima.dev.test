package net.supportdoc.helloworld.action;

import java.util.HashMap;
import com.opensymphony.xwork2.ActionSupport;

public class GuiHelloAction extends ActionSupport{

    private HashMap<String,String> msg;


    public String execute(){

  	    HashMap<String,String> map = new HashMap<String,String>();

  	    map.put("english","Hello World");
  	    map.put("japanese","ようこそ世界");

  	    setMsg(map);
        return SUCCESS;

    }

    public HashMap<String, String> getMsg() {
        return msg;
    }

    public void setMsg(HashMap<String, String> msg) {
        this.msg = msg;
    }

}