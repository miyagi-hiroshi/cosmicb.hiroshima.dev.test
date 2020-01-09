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

    final String hostName = "shirahata.mysql.database.azure.com";
    final String port = "3306";
    final String dbName = "coshiroshima";
    final String user = "ManabuShirahata@shirahata";
    final String password = "19brnZh77";

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

        //connectDb();
        //testDeail(conn);

        //終了処理
        System.out.println("endMethod:" + execClassName.toString());
        return "success";
    }

    //abstract public void testDeail(Connection conn);
    
    public void connection() throws InstantiationException,SQLException {

        //接続文字列の作成
        String url = String.format("jdbc:mysql://%s:%s/%s?", hostName, port, dbName);
        url += "characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true&useServerPrepStmts=true";

        conn = DriverManager.getConnection(url, user, password);
    }

    public String conDetail() {        

        try {
            connection();
            return "success";
        } catch (InstantiationException e) {
            System.out.println("JDBCのドライバロードに失敗しました。");
            return "error";
        } catch (SQLException e) {
            System.out.println("MySQLへの接続に失敗しました。");
            return "error";
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


