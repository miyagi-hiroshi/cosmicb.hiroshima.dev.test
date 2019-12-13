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

    abstract public void testDeail(Connection conn);
    

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
    public Boolean insertDb(String company, String name, String num) {

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

    public List<DTO> selectDb() {

        String sql = "select";

        List<DTO> DTOList = new ArrayList<DTO>();

       




        return ResultSet;

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

public class DTO {
    private int id;
    private String company;
    private String name;
    private int num;
    private Date in_date;
    private Date out_date;
    private String dest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    public Date getOut_date() {
        return out_date;
    }

    public void setOut_date(Date out_date) {
        this.out_date = out_date;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

}
