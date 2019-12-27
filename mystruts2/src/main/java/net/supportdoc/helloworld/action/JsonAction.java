package net.supportdoc.helloworld.action;

import net.supportdoc.helloworld.action.BaseAction;
import java.util.HashMap;

public class JsonAction extends BaseAction {

    private HashMap<String, String> jsonMsg;

    public String execute(){

        HashMap<String,String> map = new HashMap<String,String>();

        



        return "SUCCESS";

    }



    public HashMap<String, String> getJsonMsg() {
        return jsonMsg;
    }

    public void setJsonMsg(HashMap<String, String> jsonMsg) {
        this.jsonMsg = jsonMsg;
    }



}

