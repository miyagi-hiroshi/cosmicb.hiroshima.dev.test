package net.supportdoc.helloworld.action;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;


public abstract class BaseAction extends ActionSupport {

    String hostName = "shirahata.mysql.database.azure.com";
    String port = "3306";
    String dbName = "coshiroshima";
    String user = "ManabuShirahata@shirahata";
    String password = "19brnZh77";
    String dbUrl = null;

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
            System.out.println(e + " MySQLへの接続に失敗しました。");
            return false;
        }

    }

    /** 
     * @param houmon 訪問先
     * @param id 管理用id
     * @return boolean true=処理成功, false=処理失敗
     */
    public boolean updateDb(String houmon, int id) {

        String sql = "update houmon SET " + "dest = ?, " + "out_date = CURRENT_TIMESTAMP() " + "where id = ?;";

        try {
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, houmon);
            ps.setInt(2, id);

            ps.executeUpdate();
            conn.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("update処理エラー" + e);
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
     * @param id 管理用id
     * @return boolean true=処理成功, false=処理失敗
     */
    public boolean deleteDb(int id) {

        String sql = "delete from houmon where id = ?;";

        try {
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

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


