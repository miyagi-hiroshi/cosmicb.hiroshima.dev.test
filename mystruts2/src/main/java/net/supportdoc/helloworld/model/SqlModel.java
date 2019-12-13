package net.supportdoc.helloworld.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlModel {
    private String name, company, num;

    public SqlModel(){
        
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/cosHiroshima?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";
        String user = "root";
        String password = "19brnZh77";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //MySQLへ接続
            conn =  DriverManager.getConnection(url, user, password);
            System.out.println("MySQLへ接続できました。");

            if (name != null && num != null) {
                Statement stmt = conn.createStatement();

                String query ="insert into houmon set company='" + company +"', name='" + name + "', num=" + num + ";";
                System.out.println(query);
                stmt.executeUpdate(query);
            } else {
                System.out.println("Company=" + company + ", Name=" + name + ", Num=" + num);
                System.out.println("登録を中止しました。");
            }

            conn.close();

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e){
            System.out.println("JDBCのドライバロードに失敗しました。");
        } catch (SQLException e) {
            System.out.println("MySQLに接続できませんでした。");
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【setName】" + name);
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        System.out.println("【setCompany】" + company);
        this.company = company;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        System.out.println("【setNum】" + num);
        this.num = num;
    }

} 