package net.supportdoc.helloworld.action;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;


public abstract class BaseAction extends ActionSupport {

    String hostName = "shirahata.mysql.database.azure.com";
    String port = "3306";
    String dbName = "coshiroshima";
    String user = "ManabuShirahata@shirahata";
    String password = "19brnZh77";
    String dbUrl = null;

    private Connection conn;
    public String execClassName;

    
    /** 
     * @return String
     */
    public String testMethod(){

        //初期処理
        System.out.println("startMethod:" + execClassName.toString());

        connectDb();
        testDeail(conn);

        //終了処理
        System.out.println("endMethod:" + execClassName.toString());
        return "success";
    }

    
    /** 
     * @param display_Uketsuke(
     */
    abstract public void testDeail(Connection conn);

    public void display_Uketsuke() {
        

        //MySQLへ接続する
        connectDb();

        //INSERTで名前、会社名、人数、受付時間を登録


        //登録済みを示すトースト(ポップアップ)表示する

    }
    


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
            return true;

        } catch (SQLException e) {
            System.out.println(e + " MySQLへの接続に失敗しました。");
            return false;
        }

    }

    
    /** MySQLインサート処理
     * @param company　会社名
     * @param name　名前
     * @param num　人数
     * @return Boolean true=処理成功, false=処理失敗
     */
    public Boolean insertDetail(String company, String name, String num) {

        String sql = "insert into houmon SET " + "company = ?, " + "name = ?, " + "num = ?, "
                + "in_date = CURRENT_TIMESTAMP();";

        try {
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, company);
            ps.setString(2, name);
            ps.setString(3, num);

            ps.executeUpdate();
            conn.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Insert処理エラー：" + e);
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
     * @param houmon 訪問先
     * @param id 管理用id
     * @return boolean true=処理成功, false=処理失敗
     */
    public boolean updateDetail(String houmon, int id) {

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
    public boolean deleteDetail(int id) {

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
