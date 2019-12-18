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