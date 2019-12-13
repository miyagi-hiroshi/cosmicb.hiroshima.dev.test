package net.supportdoc.helloworld.action;

import net.supportdoc.helloworld.model.HelloModel;
import net.supportdoc.helloworld.model.GoodbyModel;

import java.sql.Connection;

//import com.opensymphony.xwork2.ActionSupport;
import net.supportdoc.helloworld.action.BaseAction;


public class HelloAction extends BaseAction {

    //private static final long serialVersionUID = -2568853932910163422L;

    private HelloModel hello;
    private GoodbyModel goodby;
    private BaseAction baseA;
    
    public HelloAction() {

        super.execClassName = "HelloAction";
        System.out.println("HelloActionコンストラクタ");
    }

    public void testDeail(Connection conn){
        //初期処理
        System.out.println("startMethod:testDeail");

        hello = new HelloModel();
        goodby = new GoodbyModel();
        //終了処理
        System.out.println("endMethod:testDeail");
    }

    /** 
     * @return String
     */
    // private SqlModel sql;

    public String create() {

        hello = new HelloModel();
        goodby = new GoodbyModel();


        System.out.println("【HelloAction:create success】");

        return "success";
    }

    
    /** 
     * 入室者のデータを登録する
     * @return String "success"
     */
    public String regData() {

        //baseA = new BaseAction();

        boolean ret;
        String name = goodby.getName();
        String company = goodby.getCompany();
        String num = goodby.getNum();

        //Azure for MySQLへ接続
        ret = baseA.connectDb();
        if (ret == false){
            System.exit(0);
        }

        //insertクエリを投げる
        ret = baseA.insertDetail(company, name, num);
        return "success";

    }

    
    /** 
     * @return HelloModel
     */
    public HelloModel getHello() {
        System.out.println("【HelloAction:getHello】" + hello);
        return hello;
    }

    
    /** 
     * @param hello
     */
    public void setHello(HelloModel hello) {
        System.out.println("HelloAction:setHello】" + hello);
        this.hello = hello;
    }

    
    /** 
     * @return GoodbyModel
     */
    public GoodbyModel getGoodby() {
        System.out.println("【HelloAction:getGoodby】" + goodby);
        return goodby;
    }

    
    /** 
     * @param goodby
     */
    public void setGoodby(GoodbyModel goodby) {
        System.out.println("【HelloAction:setGoodby】" + goodby);
        this.goodby = goodby;
    }



    // public SqlModel getSql() {
    // System.out.println("【HelloAction:getSql】" + sql);
    // return sql;
    // }

    // public void setSql(SqlModel sql) {
    // System.out.println("【HelloAction:setSql】" + sql);
    // this.sql = sql;
    // }

}
