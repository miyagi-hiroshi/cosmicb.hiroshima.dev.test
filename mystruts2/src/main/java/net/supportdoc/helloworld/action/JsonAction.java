package net.supportdoc.helloworld.action;

import net.supportdoc.helloworld.action.BaseAction;
import java.util.HashMap;

public class JsonAction extends BaseAction {

    private HashMap<String, String> jsonMsg;

    
    /** 
     * @return String
     */
    public String execute(){

        HashMap<String,String> map = new HashMap<String,String>();

        
        return "SUCCESS";

    }



    
    /** 
     * @return HashMap<String, String>
     */
    public HashMap<String, String> getJsonMsg() {
        return jsonMsg;
    }

    
    /** 
     * @param jsonMsg
     */
    public void setJsonMsg(HashMap<String, String> jsonMsg) {
        this.jsonMsg = jsonMsg;
    }



}

