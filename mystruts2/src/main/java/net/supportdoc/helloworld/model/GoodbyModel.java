package net.supportdoc.helloworld.model;
import java.util.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class GoodbyModel {

    private String report;
    private String name, company, num;

    private String sysDate;
    private ArrayList<String> dbData;
    
    public GoodbyModel(){

        Calendar c = Calendar.getInstance();
        //カレンダーフォーマット
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        sysDate = sdf.format(c.getTime());

        //report="Strutsのない世界よさようなら";
        // dbData = new ArrayList <String>();
        // dbData.add("2019/12/06 09:00:00,退出済,みやぎ");
        // dbData.add("2019/12/06 09:05:00,入室中,おか");
        // dbData.add("2019/12/06 09:11:00,入室中,はたがみ");
        // dbData.add("2019/12/06 09:20:00,退出済,うちだ");
        // dbData.add("2019/12/06 13:00:00,入室中,しらはた");
        // dbData.add("2019/12/06 15:00:00,入室中,しらはた");
        // dbData.add("2019/12/06 18:00:00,入室中,しらはた");

        // Connection conn = null;
        // String url = "jdbc:mysql://localhost:3306/cosHiroshima?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true";
        // String user = "root";
        // String password = "19brnZh77";



        // try {
        //     Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        //     //MySQLへ接続
        //     conn =  DriverManager.getConnection(url, user, password);
        //     System.out.println("MySQLへ接続できました。");
        //     System.out.println("--------------------");

        //     if (name != null && num != null) {
        //         Statement stmt = conn.createStatement();

        //         String query ="insert into houmon set company='" + company +"', name='" + name + "', num=" + num + ";";
        //         System.out.println(query);
        //         stmt.executeUpdate(query);
        //     } else {
        //         System.out.print("Company=" + company + ", Name=" + name + ", Num=" + num);
        //         System.out.println("　のため、登録を中止しました。");
        //     }

        //     conn.close();

        // } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e){
        //     System.out.println("JDBCのドライバロードに失敗しました。");
        // } catch (SQLException e) {
        //     System.out.println("MySQLに接続できませんでした。");
        // } finally {
        //     if (conn != null) {
        //         try {
        //             conn.close();
        //         } catch (SQLException e) {
        //             System.out.println("MySQLのクローズ処理に失敗しました。");
        //         }
        //     }
        // }

    }

    public String getReport() {
        System.out.println("【GoodbyModel:report】" + report);
        return report;
    }

    public ArrayList<String> getDbdata(){
        return dbData;
    }

    public void setDbdata(ArrayList<String> dbData){
        this.dbData = dbData;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getName() {
        System.out.println("【getName】" + name);
        return name;
    }

    public void setName(String name) {
        System.out.println("【setName】" + name);
        this.name = name;
    }

    public String getCompany() {
        System.out.println("【getCompany】" + company);
        return company;
    }

    public void setCompany(String company) {
        System.out.println("【setCompany】" + company);
        this.company = company;
    }

    public String getNum() {
        System.out.println("【getNum】" + num);
        return num;
    }

    public void setNum(String num) {
        System.out.println("【setNum】" + num);
        this.num = num;
    }
}