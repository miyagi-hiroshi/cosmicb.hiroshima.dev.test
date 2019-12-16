package net.supportdoc.helloworld.action;

import net.supportdoc.helloworld.model.HelloModel;
import net.supportdoc.helloworld.model.GoodbyModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.supportdoc.helloworld.action.BaseAction;


public class HelloAction extends BaseAction {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private HelloModel hello;
    private GoodbyModel goodby;
    
    public HelloAction() {

        super.execClassName = "HelloAction";

        System.out.println("HelloActionコンストラクタ");
    }

    // public void testDeail(Connection conn){
    //     //初期処理
    //     System.out.println("startMethod:testDeail");

    //     hello = new HelloModel();
    //     goodby = new GoodbyModel();
    //     //終了処理
    //     System.out.println("endMethod:testDeail");
    // }

    /** 
     * @return String
     */
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

        boolean ret;
        String name = goodby.getName();
        String company = goodby.getCompany();
        String num = goodby.getNum();

        //Azure for MySQLへ接続
        ret = connectDb();
        if (ret == false){
            System.exit(0);
        }

        //insertクエリを投げる
        try {
            String query = "insert into houmon SET " + "company = ?, " + "name = ?, " + "num = ?, "
            + "in_date = CURRENT_TIMESTAMP();";

            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, company);
            ps.setString(2, name);
            ps.setString(3, num);

            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            System.out.println("Insert処理エラー：" + e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("MySQLのクローズ処理に失敗しました。");
                }
            }
        }


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


}
