package net.supportdoc.helloworld.action;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.HashMap;


public abstract class BaseAction extends ActionSupport {

    String hostName = "shirahata.mysql.database.azure.com";
    String port = "3306";
    String dbName = "coshiroshima";
    String user = "ManabuShirahata@shirahata";
    String password = "19brnZh77";

    public Connection conn;
    public String execClassName;
    public PreparedStatement ps;
    public ResultSet rs;
    
    /** 
     * @return String
     */
    public String testMethod(){

        //初期処理
        System.out.println("startMethod:" + execClassName.toString());

        connectDb();
        //testDeail(conn);

        //終了処理
        System.out.println("endMethod:" + execClassName.toString());
        return "success";
    }

    //abstract public void testDeail(Connection conn);
    

    /** MySQL接続
     * @return Boolean true=接続OK, false=接続NG
     */
    public Boolean connectDb() {

        //接続文字列の作成
        String url = String.format("jdbc:mysql://%s:%s/%s?", hostName, port, dbName);
        url += "characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("JDBCのドライバロードに失敗しました。");
            return false;
        }


        try {

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("MySQLの接続に成功しました。");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e + " MySQLへの接続に失敗しました。");
            return false;
        }

    }

    

    
    /** 日付範囲でデータを消去する
     * @param minDate 最小日付
     * @param maxDate 最大日付
     * @return boolean 結果
     */
    public boolean deleteDb(String minDate, String maxDate) {

        String sql = "delete from houmon where in_date between ? AND ?;";

        try {
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, minDate);
            ps.setString(2, maxDate);

            ps.executeUpdate();
            conn.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("delete処理エラー" + e);
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("MySQLのクローズ処理に失敗しました。");
                }
            }
        }

    }

     
    /** 
     * @return String
     */
    public String getExecClassName() {
        return execClassName;
    }

    
    /** 
     * @param execClassName
     */
    public void setExecClassName(String execClassName) {
        this.execClassName = execClassName;
    }

}


