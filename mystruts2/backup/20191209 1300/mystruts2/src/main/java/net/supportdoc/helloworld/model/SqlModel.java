package net.supportdoc.helloworld.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class SqlModel {
    private String company,name,num;

    public SqlModel() {

        Class.forName("org.sqlite.JDBC");
        
        Calendar c = Calendar.getInstance();
        //カレンダーフォーマット
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //sysDate = sdf.format(c.getTime());

        final String URL = "jdbc:sqlite:C:\\pg\\sqlite3\\houmon.db";
        final String SQL = "insert into houmon(name, company, num, in_date) VALUES(?,?,?,?);";
        try {
            Connection conn = DriverManager.getConnection(URL);
            conn.setAutoCommit(false);

            try {
                PreparedStatement ps = conn.prepareStatement(SQL);
                ps.setString(1, name);
                ps.setString(2, company);
                ps.setString(3, num);
                //現在日時取得
                sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                ps.setString(4, sdf.format(c.getTime()));

                ps.executeUpdate();
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                System.out.println("rollback");
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //conn.close();
            System.out.println("処理が完了しました。");
        }

    }



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        System.out.println("【SqlModel:getName:" + name + "】");
        return name;
    }

    public void setName(String name) {
        System.out.println("【SqlModel:setName:" + name + "】");
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }



}